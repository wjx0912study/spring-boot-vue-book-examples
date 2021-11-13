package com.alan.hrsys.controller;

import com.alan.hrsys.entity.Employee;
import com.alan.hrsys.service.DepartmentService;
import com.alan.hrsys.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("emp")
public class EmployeeController {

    @Autowired
    EmployeeService empService;
    @Autowired
    DepartmentService depService;


    @GetMapping
    public List<Employee> search(Employee condition) {
        List<Employee> list = empService.search(condition);
        return list;
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
