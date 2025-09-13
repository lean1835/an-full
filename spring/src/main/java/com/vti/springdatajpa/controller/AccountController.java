package com.vti.springdatajpa.controller;

import com.vti.springdatajpa.dto.AccountDto;
import com.vti.springdatajpa.entity.Account;
import com.vti.springdatajpa.service.AccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    // get all
    @GetMapping
    public List<AccountDto> getAllAccount() {
        List<Account> entities = accountService.getAllAccounts();

        // convert entities  -> dto
        List<AccountDto> dtos = new ArrayList<>();
//        for(Account entity: entities) {
//            AccountDto dto = new AccountDto(entity.getUsername(), entity.getDepartment().getName());
//            dtos.add(dto);
//        }
        // sử dunngj model mapper
        dtos = modelMapper.map(entities, new TypeToken<List<AccountDto>>()
                {}.getType()
        );

        return dtos;
    }

    // find id
    @GetMapping(value = "/{id}")
    public AccountDto getAccountById(@PathVariable(name = "id") int id) {
        Account entity = accountService.getAccountById(id);
        // return new AccountDto(entity.getUsername(), entity.getDepartment().getName());

        // sưử dụng mapper
        return modelMapper.map(entity, AccountDto.class);
    }


    // TODO
    // Tiếp tục xử lý 2:  Specification + convert sang DTO
    @GetMapping(value = "/query")
    public Page<AccountDto> getAllAccounts(Pageable pageable,
                                           @RequestParam(value = "search", required = false) String search) {
        Page<Account> entityPages = accountService.getAllAccounts(pageable, search);
        // convert page -> dto

        List<AccountDto> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<AccountDto>>()
                {}.getType()
        );

        Page<AccountDto> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());

        return dtoPages;

    }
}
