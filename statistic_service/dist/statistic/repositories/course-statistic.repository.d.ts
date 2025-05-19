import { StatisticRepository } from "./statistic.repository.interface";
import { CourseStatistic, CourseStatisticDocument } from "../schemas/course-statistic.schema";
import { Model } from "mongoose";
export declare class CourseStatisticRepository implements StatisticRepository<CourseStatistic> {
    private readonly model;
    constructor(model: Model<CourseStatisticDocument>);
    findById(id: string): Promise<CourseStatistic | null>;
    save(entity: Partial<CourseStatistic>): Promise<CourseStatistic>;
    update(id: string, data: Partial<CourseStatistic>): Promise<CourseStatistic | null>;
    delete(id: string): Promise<void>;
}
