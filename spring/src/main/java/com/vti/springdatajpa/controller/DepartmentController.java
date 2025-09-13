package com.vti.springdatajpa.controller;

import com.vti.springdatajpa.dto.DepartmentFilterDto;
import com.vti.springdatajpa.entity.Department;
import com.vti.springdatajpa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/departments")
public class DepartmentController {

    // Call service
    @Autowired
    private DepartmentService departmentService;

    // get all department
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping
    public void createDepartment(@RequestBody Department department) {
        departmentService.createDepartment(department);
    }

    @PutMapping(value = "/{id}")
    public void updateDepartment(@PathVariable(name = "id") int id,
                          @RequestBody Department department) {
        department.setId(id);
        departmentService.updateDepartment(department);

    }

    @DeleteMapping(value = "/{id}")
    public void deleteDepartment(@PathVariable(name = "id") int id) {
        departmentService.deleteDepartment(id);

    }

    @GetMapping(value = "/{id}")
    public Department findById(@PathVariable(name = "id") int id) {
        return departmentService.getDepartmentById(id);
    }

    // Paging
    // url: localhost:8080/api/v1/category/search?name=test&sortField=id&sortDir=desc&page=0&size=5
    // type get
    @GetMapping("/search")
    public Page<Department> getAllDepartmentsByName(
            @RequestParam(name = "name", required = false) String queryName,
            Pageable pageable) {
        return departmentService.findByName(queryName, pageable);

        // Cách 1, check nếu name = null thì call getAll
        // nguược lại thif call findByName

        // Cacách 2, sử dụng tất cả trong 1 hàm
        // xử l1:  nếu có name thì sẻach, k có thì get all
        // xử lý 2:  Specification
    }

    @GetMapping("/filter")
    public List<Department> getAllDepartments(DepartmentFilterDto filter) {
        return departmentService.getAllDepartments(filter);
    }
}
