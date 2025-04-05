import { Injectable } from "@nestjs/common";
import {StatisticRepository} from "./statistic.repository.interface";
import { CourseStatistic, CourseStatisticDocument } from "../schemas/course-statistic.schema";
import { InjectModel } from "@nestjs/mongoose";
import { Model } from "mongoose";



@Injectable()
export class CourseStatisticRepository implements StatisticRepository<CourseStatistic>{

    constructor(
        @InjectModel(CourseStatistic.name)
        private readonly model: Model<CourseStatisticDocument>,  
    ){}

    async findById(id: string): Promise<CourseStatistic | null> {
        return this.model.findById(id).exec();
    }

    save(entity: Partial<CourseStatistic>): Promise<CourseStatistic>{
        const newModel = new this.model(entity);
        return newModel.save();
    };
    async update(id: string, data: Partial<CourseStatistic>): Promise<CourseStatistic | null> {
        return this.model.findByIdAndUpdate(id, data, { new: true }).exec();
      }

    async delete(id: string): Promise<void>{
        await this.model.findByIdAndDelete();
    }
    
    
 

}
