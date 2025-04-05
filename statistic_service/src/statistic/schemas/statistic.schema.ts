import { Prop} from "@nestjs/mongoose";
import { YearStatistic, YearStatisticSchema } from './year-statistic.schema';
import { Type } from 'class-transformer';


export abstract class Statistic{

    @Prop({required:true})
    name:string;

    @Prop({type: YearStatisticSchema})
    @Type(()=> YearStatistic)
    currentYear: YearStatistic;

    @Prop({type: YearStatisticSchema, default:[]})
    @Type(()=> YearStatistic)
    previousYears: YearStatistic[];
}
