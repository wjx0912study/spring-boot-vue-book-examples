package com.alan.hrsys.controller;

import com.alan.hrsys.entity.Department;
import com.alan.hrsys.entity.Employee;
import com.alan.hrsys.service.DepartmentService;
import com.alan.hrsys.service.EmployeeService;
import com.alan.hrsys.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("emp")
@PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER','ADMIN')")
public class EmployeeController {

    @Autowired
    EmployeeService empService;
    @Autowired
    DepartmentService depService;


    @GetMapping
    public ServerResponse search(Employee condition, Integer page, Integer size) {
        Page<Employee> emps = empService.search(condition, page, size);
        ServerResponse<List<Employee>> sr = new ServerResponse<>(emps.getTotalElements(), emps.toList());
        return sr;
    }

    @GetMapping("{id}")
    public Employee search(@PathVariable Integer id) {
        Employee emp = empService.searchById(id);
        return emp;
    }

    @PostMapping
    public boolean add(@RequestBody Employee emp) {
        boolean flag = empService.add(emp);
        return flag;
    }


    @PutMapping
    public boolean update(@RequestBody Employee emp) {
        boolean flag = empService.update(emp);
        return flag;
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Integer id) {
        boolean flag = empService.delete(id);
        return flag;
    }
}
