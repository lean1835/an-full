package com.vti.springdatajpa.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DepartmentFilterDto {
    private String search;
    private Integer minId;
    private Integer maxId;
    private Integer minTotalMember;
    private Integer maxTotalMember;
    private LocalDate minCreatedDate;
    private LocalDate maxCreatedDate;
    private Integer minYear;
    private Integer maxYear;
    private String type;
    private Integer minAccount;
    private Integer maxAccount;
}

