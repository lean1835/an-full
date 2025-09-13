package com.vti.springdatajpa.specification;

import com.vti.springdatajpa.entity.Account;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {

    public static Specification<Account> buildWhere(String search) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("username"), "%" + search + "%");
    }
}
