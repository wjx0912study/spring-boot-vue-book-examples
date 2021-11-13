package com.alan.hrsys.controller;

import com.alan.hrsys.entity.Department;
import com.alan.hrsys.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("dep")
public class DepartmentController {

    @Autowired
    DepartmentService depService;

    @GetMapping
    public List<Department> search() {
        List<Department> list = depService.search();
        return list;
    }
    @GetMapping("{id}")
    public Department search(@PathVariable Integer id) {
        Department  dep = depService.searchById(id);
        return dep;
    }

    @PostMapping
    public String add(@RequestBody Department dep) {
        boolean flag = depService.add(dep);
        return "redirect:search";

    }

    @PutMapping
    public String update(@RequestBody Department dep) {
        boolean flag = depService.update(dep);
        return "redirect:search";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id) {
        boolean flag = depService.delete(id);
        return "redirect:search";
    }
}
