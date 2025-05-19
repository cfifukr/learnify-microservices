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
exports.Statistic = void 0;
const mongoose_1 = require("@nestjs/mongoose");
const year_statistic_schema_1 = require("./year-statistic.schema");
const class_transformer_1 = require("class-transformer");
class Statistic {
    name;
    currentYear;
    previousYears;
}
exports.Statistic = Statistic;
__decorate([
    (0, mongoose_1.Prop)({ required: true }),
    __metadata("design:type", String)
], Statistic.prototype, "name", void 0);
__decorate([
    (0, mongoose_1.Prop)({ type: year_statistic_schema_1.YearStatisticSchema }),
    (0, class_transformer_1.Type)(() => year_statistic_schema_1.YearStatistic),
    __metadata("design:type", year_statistic_schema_1.YearStatistic)
], Statistic.prototype, "currentYear", void 0);
__decorate([
    (0, mongoose_1.Prop)({ type: year_statistic_schema_1.YearStatisticSchema, default: [] }),
    (0, class_transformer_1.Type)(() => year_statistic_schema_1.YearStatistic),
    __metadata("design:type", Array)
], Statistic.prototype, "previousYears", void 0);
//# sourceMappingURL=statistic.schema.js.map