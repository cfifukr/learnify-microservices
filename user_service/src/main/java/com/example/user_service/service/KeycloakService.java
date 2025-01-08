package com.example.user_service.service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RoleMappingResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collections;


@Service
public class KeycloakService {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.admin-username}")
    private String adminUsername;

    @Value("${keycloak.admin-password}")
    private String adminPassword;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @Value("${keycloak.default-role}")
    private String defaultRole;


    public String registerUser(String username, String email, String firstName, String lastName, String password) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakServerUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .username(adminUsername)
                .password(adminPassword)
                .grantType("password")
                .build();

        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEnabled(true);


        CredentialRepresentation passwordCredential = new CredentialRepresentation();
        passwordCredential.setType(CredentialRepresentation.PASSWORD);
        passwordCredential.setValue(password);
        passwordCredential.setTemporary(false);

        user.setCredentials(Collections.singletonList(passwordCredential));

        String userId = keycloak.realm(realm).users().create(user).getLocation().getPath().split("/")[5];


        RoleRepresentation role = keycloak.realm(realm).roles().get(defaultRole).toRepresentation();
        RoleMappingResource roleMappingResource = keycloak.realm(realm).users().get(userId).roles();
        roleMappingResource.realmLevel().add(Collections.singletonList(role));

        return userId;
    }
}

