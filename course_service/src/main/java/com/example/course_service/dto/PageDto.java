package com.example.course_service.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageDto {
    private Integer page;
    private  Integer size;
    private Long total;
    private List<?> list;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public PageDto(Integer page, Integer size, Long total, List<?> list) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.list = list;
    }

    public PageDto() {
    }

    public static PageDto getDto(Page<?> page, List<?> dto){

        return new PageDto(page.getNumber(), page.getSize(), page.getTotalElements(), dto);

    }
}
