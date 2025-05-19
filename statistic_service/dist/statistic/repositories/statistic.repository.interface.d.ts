export interface StatisticRepository<T> {
    findById(id: string): Promise<T | null>;
    save(entity: Partial<T>): Promise<T>;
    update(id: string, data: Partial<T>): Promise<T | null>;
    delete(id: string): Promise<void>;
}
