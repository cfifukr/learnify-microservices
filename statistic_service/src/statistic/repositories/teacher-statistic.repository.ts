import { Injectable } from "@nestjs/common";
import { TeacherStatistic, TeacherStatisticDocument } from "../schemas/teacher-statistic.schema";
import { Model } from "mongoose";
import { InjectModel } from "@nestjs/mongoose";
import { StatisticRepository } from "./statistic.repository.interface";

@Injectable()
export class CourseStatisticRepository implements StatisticRepository<TeacherStatistic>{

    constructor(
        @InjectModel(TeacherStatistic.name)
        private readonly model: Model<TeacherStatisticDocument>,  
    ){}

    async findById(id: string): Promise<TeacherStatistic | null> {
        return this.model.findById(id).exec();
    }

    save(entity: Partial<TeacherStatistic>): Promise<TeacherStatistic>{
        const newModel = new this.model(entity);
        return newModel.save();
    };
    async update(id: string, data: Partial<TeacherStatistic>): Promise<TeacherStatistic | null> {
        return this.model.findByIdAndUpdate(id, data, { new: true }).exec();
      }

    async delete(id: string): Promise<void>{
        await this.model.findByIdAndDelete();
    }
    
    
 

}
