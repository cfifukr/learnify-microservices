import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { CourseStatistic, CourseStatisticSchema } from './schemas/course-statistic.schema';
import { TeacherStatistic, TeacherStatisticSchema } from './schemas/teacher-statistic.schema';


@Module({
  imports: [
    MongooseModule.forRoot('mongodb://admin:secret@localhost:27017', {
      authSource: 'admin',
    }),
    MongooseModule.forFeature([
        { name: CourseStatistic.name, schema: CourseStatisticSchema },
        { name: TeacherStatistic.name, schema: TeacherStatisticSchema },
      ]),
    StatisticModule  ],
    
  controllers: [],
})

export class StatisticModule {}
