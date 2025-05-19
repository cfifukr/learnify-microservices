"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.StatisticModule = void 0;
const common_1 = require("@nestjs/common");
const mongoose_1 = require("@nestjs/mongoose");
const course_statistic_schema_1 = require("./schemas/course-statistic.schema");
const teacher_statistic_schema_1 = require("./schemas/teacher-statistic.schema");
let StatisticModule = class StatisticModule {
};
exports.StatisticModule = StatisticModule;
exports.StatisticModule = StatisticModule = __decorate([
    (0, common_1.Module)({
        imports: [
            mongoose_1.MongooseModule.forRoot('mongodb://admin:secret@localhost:27017', {
                authSource: 'admin',
            }),
            mongoose_1.MongooseModule.forFeature([
                { name: course_statistic_schema_1.CourseStatistic.name, schema: course_statistic_schema_1.CourseStatisticSchema },
                { name: teacher_statistic_schema_1.TeacherStatistic.name, schema: teacher_statistic_schema_1.TeacherStatisticSchema },
            ]),
            StatisticModule
        ],
        controllers: [],
    })
], StatisticModule);
//# sourceMappingURL=statistic.module.js.map