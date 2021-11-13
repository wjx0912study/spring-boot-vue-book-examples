package com.alan.hrsys.controller;

import com.alan.hrsys.entity.SysRole;
import com.alan.hrsys.entity.SysUser;
import com.alan.hrsys.service.SysRoleService;
import com.alan.hrsys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("sysUser")
public class SysUserController {

	@Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService sysRoleService;
	@RequestMapping("search")
	public ModelAndView search() {
		ModelAndView mv = new ModelAndView("sysUser/show");
		List<SysUser> list = sysUserService.search();
		mv.addObject("list", list);

		return mv;

	}

	@RequestMapping("showAdd")
	public ModelAndView showAdd() {
        ModelAndView mv=new ModelAndView("sysUser/add");
        List<SysRole> roles = sysRoleService.search();
        mv.addObject("roles", roles);
		return mv;

	}

	@RequestMapping("add")
	public String add(SysUser sysUser) {
		boolean flag = sysUserService.add(sysUser);
		return "redirect:search";

	}

	@RequestMapping("showUpdate")
	public ModelAndView showUpdate(Integer id) {
		ModelAndView mv = new ModelAndView("sysUser/update");
		SysUser sysUser = sysUserService.searchById(id);
        List<SysRole> roles = sysRoleService.search();
		mv.addObject("sysUser", sysUser);
        mv.addObject("roles", roles);
		return mv;

	}

	@RequestMapping("update")
	public String update(SysUser sysUser) {
		boolean flag = sysUserService.update(sysUser);
		return "redirect:search";
	}

	@RequestMapping("delete")
	public String delete(Integer id) {
		boolean flag = sysUserService.delete(id);
		return "redirect:search";
	}
}
