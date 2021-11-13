package com.alan.hrsys.controller;

import com.alan.hrsys.entity.SysPermission;
import com.alan.hrsys.entity.SysRole;
import com.alan.hrsys.service.SysPermissionService;
import com.alan.hrsys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("sysRole")
public class SysRoleController {

	@Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysPermissionService sysPermissionService;
	@RequestMapping("search")
	public ModelAndView search() {
		ModelAndView mv = new ModelAndView("sysRole/show");
		List<SysRole> list = sysRoleService.search();
		mv.addObject("list", list);

		return mv;

	}
    @RequestMapping("showAdd")
    public ModelAndView showAdd() {
        ModelAndView mv=new ModelAndView("sysRole/add");
        List<SysPermission> permissions = sysPermissionService.search();
        mv.addObject("permissions", permissions);
        return mv;

    }

	@RequestMapping("add")
	public String add(SysRole sysRole) {
		boolean flag = sysRoleService.add(sysRole);
		return "redirect:search";

	}

	@RequestMapping("showUpdate")
	public ModelAndView showUpdat(Integer id) {
		ModelAndView mv = new ModelAndView("sysRole/update");
		SysRole sysRole = sysRoleService.searchById(id);
        List<SysPermission> permissions = sysPermissionService.search();
		mv.addObject("sysRole", sysRole);
        mv.addObject("permissions", permissions);
        return mv;

	}

	@RequestMapping("update")
	public String update(SysRole sysRole) {
		boolean flag = sysRoleService.update(sysRole);
		return "redirect:search";
	}

	@RequestMapping("delete")
	public String delete(Integer id) {
		boolean flag = sysRoleService.delete(id);
		return "redirect:search";
	}
}
