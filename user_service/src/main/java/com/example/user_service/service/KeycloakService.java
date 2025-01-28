package com.example.user_service.service;

import com.example.user_service.dto.response.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RoleMappingResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;



@Service
@Slf4j
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

    private final RestTemplate restTemplate;

    public KeycloakService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


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

        log.info("User created: {}", username);

        return userId;
    }

    public TokenResponse getToken(String username, String password) {

        String url = keycloakServerUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("username", username);
        body.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();
                return new TokenResponse(
                        (String) responseBody.get("access_token"),
                        (String) responseBody.get("refresh_token"),
                        ((Number) responseBody.get("expires_in")).longValue(),
                        ((Number) responseBody.get("refresh_expires_in")).longValue()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("User not found with username {} and password {}", username, password);

        return null;
    }
}

