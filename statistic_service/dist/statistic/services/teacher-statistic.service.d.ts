import { TeacherStatistic } from "../schemas/teacher-statistic.schema";
import { StatisticRepository } from "../repositories/statistic.repository.interface";
export declare class TeacherStatisticService {
    private readonly repo;
    constructor(repo: StatisticRepository<TeacherStatistic>);
    getById(id: string): Promise<TeacherStatistic | null>;
    create(stat: Partial<TeacherStatistic>): Promise<TeacherStatistic>;
    update(id: string, stat: Partial<TeacherStatistic>): Promise<TeacherStatistic | null>;
    remove(id: string): Promise<void>;
}
