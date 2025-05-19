import { Types } from 'mongoose';
export type YearStatisticDocument = YearStatistic & Document;
export declare class YearStatistic {
    year: number;
    courseId?: Types.ObjectId;
    teacherId?: Types.ObjectId;
    January: number;
    February: number;
    March: number;
    April: number;
    May: number;
    June: number;
    July: number;
    August: number;
    September: number;
    October: number;
    November: number;
    December: number;
}
export declare const YearStatisticSchema: import("mongoose").Schema<YearStatistic, import("mongoose").Model<YearStatistic, any, any, any, import("mongoose").Document<unknown, any, YearStatistic> & YearStatistic & {
    _id: Types.ObjectId;
} & {
    __v: number;
}, any>, {}, {}, {}, {}, import("mongoose").DefaultSchemaOptions, YearStatistic, import("mongoose").Document<unknown, {}, import("mongoose").FlatRecord<YearStatistic>> & import("mongoose").FlatRecord<YearStatistic> & {
    _id: Types.ObjectId;
} & {
    __v: number;
}>;
