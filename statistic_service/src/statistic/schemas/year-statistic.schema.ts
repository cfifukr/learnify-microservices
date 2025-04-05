import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { HydratedDocument, Types } from 'mongoose';

export type YearStatisticDocument = YearStatistic & Document;

@Schema()
export class YearStatistic {
  @Prop({
    default: () => new Date().getFullYear(),
    min: 2020,
    max: 2100,
  })
  year: number;

  @Prop({ type: Types.ObjectId, ref: 'Course', required: false })
  courseId?: Types.ObjectId;

  @Prop({ type: Types.ObjectId, ref: 'Teacher', required: false })
  teacherId?: Types.ObjectId;

  @Prop({ default: 0 }) January: number;
  @Prop({ default: 0 }) February: number;
  @Prop({ default: 0 }) March: number;
  @Prop({ default: 0 }) April: number;
  @Prop({ default: 0 }) May: number;
  @Prop({ default: 0 }) June: number;
  @Prop({ default: 0 }) July: number;
  @Prop({ default: 0 }) August: number;
  @Prop({ default: 0 }) September: number;
  @Prop({ default: 0 }) October: number;
  @Prop({ default: 0 }) November: number;
  @Prop({ default: 0 }) December: number;
}

export const YearStatisticSchema = SchemaFactory.createForClass(YearStatistic);
