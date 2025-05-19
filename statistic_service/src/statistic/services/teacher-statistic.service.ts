import { Inject, Injectable } from "@nestjs/common";
import { TeacherStatistic } from "../schemas/teacher-statistic.schema";
import { StatisticRepository } from "../repositories/statistic.repository.interface";
import { generateDefaultYearStatistic } from "../utils/statistic-utils";


@Injectable()
export class TeacherStatisticService{
    constructor(
        @Inject('TeacherStatisticRepository')
        private readonly repo: StatisticRepository<TeacherStatistic>,
    ){}

    async getById(id: string): Promise<TeacherStatistic | null> {
        return this.repo.findById(id);
    }

    async create(stat: Partial<TeacherStatistic>): Promise<TeacherStatistic> {
        return this.repo.save(stat);
    }

    async update(id: string, stat: Partial<TeacherStatistic>): Promise<TeacherStatistic | null> {
        return this.repo.update(id, stat);
    }

    async remove(id: string): Promise<void> {
        return this.repo.delete(id);
    }

}