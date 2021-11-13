package com.alan.hrsys.controller;

import com.alan.hrsys.entity.Department;
import com.alan.hrsys.entity.Employee;
import com.alan.hrsys.service.DepartmentService;
import com.alan.hrsys.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("emp")
public class EmployeeController {

    @Autowired
    EmployeeService empService;
    @Autowired
    DepartmentService depService;

    @RequestMapping(value = "show")
    public String show() {
        return "emp/show";
    }

    @RequestMapping("showAdd")
    public String showAdd() {
        return "emp/add";
    }

    @RequestMapping("showUpdate")
    public ModelAndView showUpdate(Integer id) {
        ModelAndView mv = new ModelAndView("emp/update");
        mv.addObject("id", id);
        return mv;
    }


    @RequestMapping(value = "search")
    @ResponseBody
    public List<Employee> search(Employee condition) {
        List<Employee> list = empService.search(condition);
        return list;
    }

    @RequestMapping(value = "searchById")
    @ResponseBody
    public Employee search(Integer id) {
        Employee emp = empService.searchById(id);
        return emp;
    }

    @RequestMapping("add")
    @ResponseBody
    public boolean add(@RequestBody Employee emp) {
        boolean flag = empService.add(emp);
        return flag;
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public boolean update(@RequestBody Employee emp) {
        boolean flag = empService.update(emp);
        return flag;
    }

    @RequestMapping("delete")
    public String delete(Integer id) {
        boolean flag = empService.delete(id);
        return "redirect:show";
    }
}
