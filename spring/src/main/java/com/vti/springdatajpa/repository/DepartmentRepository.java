package com.vti.springdatajpa.repository;

import com.vti.springdatajpa.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository  extends JpaRepository<Department, Integer>, JpaSpecificationExecutor<Department> {

    // Paging = method name
    // Paging = @query
    @Query("select d from Department d where name like %?1%")
    Page<Department> findByName(String name, Pageable pageable);

    // Sử dụng query cho create, update, delete, find by ID
    // check insert có cần @Modifying?
    @Modifying
    @Query(value = "insert into Department(DepartmentName) values (?1)", nativeQuery = true)
    void createDepartment(String departmentName);

    // modify data thì cần thêm @Modifying
    @Modifying
    @Query("Update Department set name = ?1 where id = ?2")
    void updateDepartment(String departmentName, int departmentId);

    // modify data thì cần thêm @Modifying
    @Modifying
    @Query("delete from Department where id = ?1")
    void deleteDepartment(int id);

    @Query("SELECT d FROM Department d WHERE d.id = ?1")
    // @Query("Select * from Department where id =  ?1")
    Department findByDepartmentId(int id);

}
