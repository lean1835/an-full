package com.vti.springdatajpa.specification;

import com.vti.springdatajpa.dto.DepartmentFilterDto;
import com.vti.springdatajpa.entity.Account;
import com.vti.springdatajpa.entity.Department;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class DepartmentSpecification {

    public static Specification<Department> buildSpec(DepartmentFilterDto filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getSearch() != null) {
                predicates.add(cb.like(root.get("name"), "%" + filter.getSearch() + "%"));
            }

            if (filter.getMinId() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("id"), filter.getMinId()));
            }
            if (filter.getMaxId() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("id"), filter.getMaxId()));
            }

            if (filter.getMinTotalMember() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("totalMember"), filter.getMinTotalMember()));
            }
            if (filter.getMaxTotalMember() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("totalMember"), filter.getMaxTotalMember()));
            }

            if (filter.getMinCreatedDate() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createdDate"), filter.getMinCreatedDate()));
            }
            if (filter.getMaxCreatedDate() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("createdDate"), filter.getMaxCreatedDate()));
            }

            if (filter.getMinYear() != null) {
                predicates.add(cb.greaterThanOrEqualTo(cb.function("year", Integer.class, root.get("createdDate")), filter.getMinYear()));
            }
            if (filter.getMaxYear() != null) {
                predicates.add(cb.lessThanOrEqualTo(cb.function("year", Integer.class, root.get("createdDate")), filter.getMaxYear()));
            }

            if (filter.getType() != null) {
                predicates.add(cb.equal(root.get("type"), filter.getType()));
            }

            if (filter.getMinAccount() != null || filter.getMaxAccount() != null) {
                Join<Department, Account> accountJoin = root.join("accounts", JoinType.LEFT);
                query.groupBy(root.get("id"));
                if (filter.getMinAccount() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(cb.count(accountJoin), filter.getMinAccount().longValue()));
                }
                if (filter.getMaxAccount() != null) {
                    predicates.add(cb.lessThanOrEqualTo(cb.count(accountJoin), filter.getMaxAccount().longValue()));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

