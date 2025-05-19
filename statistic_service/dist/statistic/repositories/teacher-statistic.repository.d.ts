import { TeacherStatistic, TeacherStatisticDocument } from "../schemas/teacher-statistic.schema";
import { Model } from "mongoose";
import { StatisticRepository } from "./statistic.repository.interface";
export declare class CourseStatisticRepository implements StatisticRepository<TeacherStatistic> {
    private readonly model;
    constructor(model: Model<TeacherStatisticDocument>);
    findById(id: string): Promise<TeacherStatistic | null>;
    save(entity: Partial<TeacherStatistic>): Promise<TeacherStatistic>;
    update(id: string, data: Partial<TeacherStatistic>): Promise<TeacherStatistic | null>;
    delete(id: string): Promise<void>;
}
