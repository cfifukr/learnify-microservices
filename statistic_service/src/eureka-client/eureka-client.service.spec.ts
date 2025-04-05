import { Test, TestingModule } from '@nestjs/testing';
import { EurekaClientService } from './eureka-client.service';

describe('EurekaClientService', () => {
  let service: EurekaClientService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [EurekaClientService],
    }).compile();

    service = module.get<EurekaClientService>(EurekaClientService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
