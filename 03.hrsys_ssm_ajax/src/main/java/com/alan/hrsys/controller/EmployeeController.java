package com.alan.hrsys.controller;

import com.alan.hrsys.entity.Department;
import com.alan.hrsys.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alan.hrsys.service.DepartmentService;
import com.alan.hrsys.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("emp")
public class EmployeeController {
	@Autowired
	EmployeeService empService;
	@Autowired
	DepartmentService depService;
	@RequestMapping(value="search")
	public ModelAndView search() {
		ModelAndView mv = new ModelAndView("emp/show");
		List<Employee> list = empService.search(null);
		List<Department> depList=depService.search();
		mv.addObject("list", list);
        mv.addObject("depList",depList);
		return mv;
	}
    @RequestMapping(value="searchByCondition")
    @ResponseBody
    public List<Employee>   search(Employee condition) {
        List<Employee> list = empService.search(condition);
        return list;
    }
	@RequestMapping("showAdd")
	public ModelAndView showAdd() {
		ModelAndView mv = new ModelAndView("emp/add");
		List<Department> depList = depService.search();
		mv.addObject("depList", depList);
		return mv;
	}

	@RequestMapping("add")
	public String add(Employee emp) {
		boolean flag = empService.add(emp);
		return "redirect:search";
	}

	@RequestMapping("showUpdate")
	public ModelAndView showUpdate(Integer id) {
		Employee emp = empService.searchById(id);
		List<Department> depList = depService.search();
		ModelAndView mv = new ModelAndView("emp/update");
		mv.addObject("emp", emp);
		mv.addObject("depList", depList);
		return mv;
	}

	@RequestMapping(value="update")
	public String update(Employee emp) {
		boolean flag = empService.update(emp);
		return "redirect:search";
	}

	@RequestMapping("delete")
	public String delete(Integer id) {
		boolean flag = empService.delete(id);
		return "redirect:search";
	}
}
