import { YearStatistic } from "../schemas/year-statistic.schema";

export function generateDefaultYearStatistic(): Partial<YearStatistic> {
    const currentYear = new Date().getFullYear();
  
    return {
      year: currentYear,
      January: 0,
      February: 0,
      March: 0,
      April: 0,
      May: 0,
      June: 0,
      July: 0,
      August: 0,
      September: 0,
      October: 0,
      November: 0,
      December: 0,
    };
}
  