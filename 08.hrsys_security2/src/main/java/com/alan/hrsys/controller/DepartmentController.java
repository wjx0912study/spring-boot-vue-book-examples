package com.alan.hrsys.controller;

import com.alan.hrsys.entity.Department;
import com.alan.hrsys.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("dep")
@PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
public class DepartmentController {

	@Autowired
    DepartmentService depService;

	@RequestMapping("search")
	public ModelAndView search() {
		ModelAndView mv = new ModelAndView("dep/show");
		List<Department> list = depService.search();
		mv.addObject("list", list);

		return mv;

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
