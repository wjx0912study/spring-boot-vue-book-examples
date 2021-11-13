package com.alan.hrsys.controller;

import com.alan.hrsys.entity.Department;
import com.alan.hrsys.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("dep")
public class DepartmentController {

    @Autowired
    DepartmentService depService;

    @RequestMapping("show")
    public String show() {
        List<Department> list = depService.search();
        return "dep/show";

    }

    @RequestMapping("search")
    @ResponseBody
    public List<Department> search() {
        List<Department> list = depService.search();
        return list;

    }

    @RequestMapping("showAdd")
    public String showAdd() {
        return "dep/add";

    }

    @RequestMapping("add")
    public String add(Department dep) {
        boolean flag = depService.add(dep);
        return "redirect:search";

    }

    @RequestMapping("showUpdate")
    public ModelAndView showUpdat(Integer id) {
        ModelAndView mv = new ModelAndView("dep/update");
        Department dep = depService.searchById(id);
        mv.addObject("dep", dep);
        return mv;

    }

    @RequestMapping("update")
    public String update(Department dep) {
        boolean flag = depService.update(dep);
        return "redirect:search";
    }

    @RequestMapping("delete")
    public String delete(Integer id) {
        boolean flag = depService.delete(id);
        return "redirect:search";
    }
}
