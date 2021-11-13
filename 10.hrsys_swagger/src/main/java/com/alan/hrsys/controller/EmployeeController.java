package com.alan.hrsys.controller;

import com.alan.hrsys.entity.Department;
import com.alan.hrsys.entity.Employee;
import com.alan.hrsys.service.DepartmentService;
import com.alan.hrsys.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("emp")
@Api(value="Employee Controller",tags = "员工接口")
public class EmployeeController {

    @Autowired
    EmployeeService empService;
    @Autowired
    DepartmentService depService;

    @ApiOperation(value="search all employee",notes = "查询所有员工")
    @GetMapping
    public List<Employee> search(@RequestBody(required = false) Employee condition) {
        List<Employee> list = empService.search(condition);
        return list;
    }

    @ApiOperation(value="search employee by id",notes = "根据id查询单个员工")
    @GetMapping("{id}")
    public Employee search(@ApiParam(name="员工id",value="id",required=true) @PathVariable Integer id) {
        Employee emp = empService.searchById(id);
        return emp;
    }
    @ApiOperation(value="add employee",notes = "添加员工")
    @PostMapping
    @ApiImplicitParam(dataType="com.alan.hrsys.entity.Employee")
    public boolean add(@RequestBody Employee emp) {
        boolean flag = empService.add(emp);
        return flag;
    }

    @ApiOperation(value="update employee",notes = "修改员工")
    @PutMapping
    @ApiImplicitParam(dataType="com.alan.hrsys.entity.Employee")
    public boolean update(@RequestBody Employee emp) {
        boolean flag = empService.update(emp);
        return flag;
    }

    @ApiOperation(value="delete employee",notes = "删除员工")
    @DeleteMapping("{id}")
    public boolean delete(@ApiParam(name="员工id",value="id",required=true) @PathVariable Integer id) {
        boolean flag = empService.delete(id);
        return flag;
    }
}
