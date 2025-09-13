package com.vti.springdatajpa.form;

import com.vti.springdatajpa.entity.Account;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateDepartmentForm {

    @NotNull
    private String name;

    @NotNull
    private int totalMember;

    private Type type;

    private Date createDate;

    private List<Account> accounts;

    // taọ enum type
    public enum Type {
        DEV("Dev"), TEST("Test"), SCRUM_MASTER("Scrum master"), PM ("Pm");

        private String value;

        private Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


}
