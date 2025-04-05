import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';

import { EurekaClientService } from './eureka-client/eureka-client.service';
import { StatisticModule } from './statistic/statistic.module';


@Module({
  imports: [ StatisticModule  ],
  controllers: [],
  providers: [EurekaClientService],
})
export class AppModule {}
