import { Injectable, OnModuleInit, OnModuleDestroy } from '@nestjs/common';
import { Eureka } from 'eureka-js-client';


@Injectable()
export class EurekaClientService implements OnModuleInit, OnModuleDestroy {
  private eureka: Eureka;

  onModuleInit() {
    this.eureka = new Eureka({
      instance: {
        app: 'statistic-service', 
        instanceId: 'statistic-service:3000',
        hostName: 'localhost', 
        ipAddr: '127.0.0.1',
        statusPageUrl: 'http://localhost:3000/actuator/info',
        port: {
          '$': 3000,
          '@enabled': true,
        },
        vipAddress: 'nestjs-service',
        dataCenterInfo: {
          '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
          name: 'MyOwn',
        },
      },
      eureka: {
        host: 'localhost', 
        port: 8761,
        servicePath: '/eureka/apps/',
        maxRetries: 5,
        requestRetryDelay: 1000,
      },
    });

    this.eureka.start((err) => {
      if (err) {
        console.error('Eureka registration failed:', err);
      } else {
        console.log('✅ Eureka registration successful!');
      }
    });
  }

  onModuleDestroy() {
    this.eureka.stop();
  }
}
