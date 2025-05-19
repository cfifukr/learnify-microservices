import { Statistic } from "./statistic.schema";
export type CourseStatisticDocument = CourseStatistic & Document;
export declare class CourseStatistic extends Statistic {
    creatorKeyclockId: string;
    courseId: String;
}
export declare const CourseStatisticSchema: import("mongoose").Schema<CourseStatistic, import("mongoose").Model<CourseStatistic, any, any, any, import("mongoose").Document<unknown, any, CourseStatistic> & CourseStatistic & {
    _id: import("mongoose").Types.ObjectId;
} & {
    __v: number;
}, any>, {}, {}, {}, {}, import("mongoose").DefaultSchemaOptions, CourseStatistic, import("mongoose").Document<unknown, {}, import("mongoose").FlatRecord<CourseStatistic>> & import("mongoose").FlatRecord<CourseStatistic> & {
    _id: import("mongoose").Types.ObjectId;
} & {
    __v: number;
}>;
