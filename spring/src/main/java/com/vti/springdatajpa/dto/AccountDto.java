package com.vti.springdatajpa.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccountDto {

    // CRUD trả về thêm id để update, check insert, delete

    private String username;

    private String departmentName;

    public AccountDto(String username, String departmentName) {
        this.username = username;
        this.departmentName = departmentName;
    }
}
