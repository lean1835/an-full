package com.vti.springdatajpa.service;

import com.vti.springdatajpa.dto.DepartmentFilterDto;
import com.vti.springdatajpa.entity.Account;
import com.vti.springdatajpa.entity.Department;
import com.vti.springdatajpa.form.CreateDepartmentForm;
import com.vti.springdatajpa.repository.AccountRepository;
import com.vti.springdatajpa.repository.DepartmentRepository;
import com.vti.springdatajpa.specification.DepartmentSpecification;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// Dùng @Query thì cần dùng thêm transition ở service thì mới có tác dụng
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    // Call repository
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(int id) {
//        return departmentRepository.findById(id).get();
        return departmentRepository.findByDepartmentId(id);
    }

    @Override
    public void createDepartment(Department department) {
//        departmentRepository.save(department);
        departmentRepository.createDepartment(department.getName());

    }

    @Override
    public void updateDepartment(Department department) {

//        departmentRepository.save(department);
        departmentRepository.updateDepartment(department.getName(), department.getId());
    }

    @Override
    public void deleteDepartment(int id) {
//        departmentRepository.deleteById(id);
        departmentRepository.deleteDepartment(id);

    }

    @Override
    public Page<Department> findByName(String name, Pageable pageable) {
        return departmentRepository.findByName(name, pageable);
    }

    @Override
    public List<Department> getAllDepartments(DepartmentFilterDto filter) {
        return departmentRepository.findAll(DepartmentSpecification.buildSpec(filter));
    }

    // Khi cần roll back dữ liệu của cả method đoó khi có lỗi thì dùng Transactional
    @Transactional
    @Override
    public void createDepartmentIncludeAccount(CreateDepartmentForm form) {
        // convert form to entity
        Department department = modelMapper.map(form, Department.class);

        // create department
        departmentRepository.save(department);

        // create list account
        List<Account> accountList = department.getAccounts();
        for(Account account: accountList) {
            account.setDepartment(department);
        }
        accountRepository.saveAll(accountList);
    }
}
