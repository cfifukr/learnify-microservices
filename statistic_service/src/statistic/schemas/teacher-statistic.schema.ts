import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Statistic } from "./statistic.schema";

export type TeacherStatisticDocument = TeacherStatistic & Document;


@Schema()
export class TeacherStatistic extends Statistic{

    @Prop({required: true})
    creatorKeyclockId: string;

}

export const TeacherStatisticSchema = SchemaFactory.createForClass(TeacherStatistic);