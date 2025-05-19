import { YearStatistic } from './year-statistic.schema';
export declare abstract class Statistic {
    name: string;
    currentYear: YearStatistic;
    previousYears: YearStatistic[];
}
