"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.CourseStatisticSchema = exports.CourseStatistic = void 0;
const mongoose_1 = require("@nestjs/mongoose");
const statistic_schema_1 = require("./statistic.schema");
let CourseStatistic = class CourseStatistic extends statistic_schema_1.Statistic {
    creatorKeyclockId;
    courseId;
};
exports.CourseStatistic = CourseStatistic;
__decorate([
    (0, mongoose_1.Prop)({ required: true }),
    __metadata("design:type", String)
], CourseStatistic.prototype, "creatorKeyclockId", void 0);
__decorate([
    (0, mongoose_1.Prop)({ required: true }),
    __metadata("design:type", String)
], CourseStatistic.prototype, "courseId", void 0);
exports.CourseStatistic = CourseStatistic = __decorate([
    (0, mongoose_1.Schema)()
], CourseStatistic);
exports.CourseStatisticSchema = mongoose_1.SchemaFactory.createForClass(CourseStatistic);
//# sourceMappingURL=course-statistic.schema.js.map