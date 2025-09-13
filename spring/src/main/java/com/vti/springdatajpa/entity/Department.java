package com.vti.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Department")
@NoArgsConstructor
//@RequiredArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartmentID")
    private  int id;

    @Column(name = "DepartmentName")
    private String name;

    @Column(name = "TotalMember")
    private int totalMember;

    @Column(name = "Type")
    private Type type;

    @Column(name = "CreateDate")
    private Date createDate;

    @OneToMany(mappedBy = "department")
    private List<Account> accounts;

    // get, setter => change by lombok

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
