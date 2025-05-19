import { OnModuleInit, OnModuleDestroy } from '@nestjs/common';
export declare class EurekaClientService implements OnModuleInit, OnModuleDestroy {
    private eureka;
    onModuleInit(): void;
    onModuleDestroy(): void;
}
