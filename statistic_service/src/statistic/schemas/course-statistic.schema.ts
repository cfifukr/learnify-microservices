import { Prop, Schema, SchemaFactory } from "@nestjs/mongoose";
import { Statistic } from "./statistic.schema";


export type CourseStatisticDocument = CourseStatistic & Document;


@Schema()
export class CourseStatistic extends Statistic{

    @Prop({required: true})
    creatorKeyclockId: string;

    @Prop({required: true})
    courseId: String;

}

export const CourseStatisticSchema = SchemaFactory.createForClass(CourseStatistic);