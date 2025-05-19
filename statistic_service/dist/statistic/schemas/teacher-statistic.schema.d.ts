import { Statistic } from "./statistic.schema";
export type TeacherStatisticDocument = TeacherStatistic & Document;
export declare class TeacherStatistic extends Statistic {
    creatorKeyclockId: string;
}
export declare const TeacherStatisticSchema: import("mongoose").Schema<TeacherStatistic, import("mongoose").Model<TeacherStatistic, any, any, any, import("mongoose").Document<unknown, any, TeacherStatistic> & TeacherStatistic & {
    _id: import("mongoose").Types.ObjectId;
} & {
    __v: number;
}, any>, {}, {}, {}, {}, import("mongoose").DefaultSchemaOptions, TeacherStatistic, import("mongoose").Document<unknown, {}, import("mongoose").FlatRecord<TeacherStatistic>> & import("mongoose").FlatRecord<TeacherStatistic> & {
    _id: import("mongoose").Types.ObjectId;
} & {
    __v: number;
}>;
