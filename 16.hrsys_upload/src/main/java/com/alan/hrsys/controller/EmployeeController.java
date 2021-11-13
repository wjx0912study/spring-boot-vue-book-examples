package com.alan.hrsys.controller;

import com.alan.hrsys.entity.Department;
import com.alan.hrsys.entity.Employee;
import com.alan.hrsys.service.DepartmentService;
import com.alan.hrsys.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("emp")
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	@Autowired
	DepartmentService depService;
	@RequestMapping(value="search")
	public ModelAndView search(Employee condition) {
		ModelAndView mv = new ModelAndView("emp/show");
		List<Employee> list = empService.search(condition);
		List<Department> depList=depService.search();
		mv.addObject("list", list);
        mv.addObject("depList",depList);
        mv.addObject("c", condition);
		return mv;
	}

	@RequestMapping("showAdd")
	public ModelAndView showAdd() {
		ModelAndView mv = new ModelAndView("emp/add2");
		List<Department> depList = depService.search();
		mv.addObject("depList", depList);
		return mv;
	}

	//@RequestMapping("add")
	public String add(Employee emp, @RequestParam(value = "photo") MultipartFile[] photos) throws FileNotFoundException {
         //获取运行时的项目路径，得到存放照片的文件夹
        String path = ResourceUtils.getURL("classpath:").getPath() + "/static/photo/";
        String photoName = "";
        for (MultipartFile file : photos) {
            if (!file.isEmpty()) {
                //通过UUID生成不唯一的文件名
                UUID uuid = UUID.randomUUID();
                //获取原文件名
                String oldName = file.getOriginalFilename();
                //获取原文件名的后缀
                String suffix=oldName.substring(oldName.lastIndexOf("."));
                //UUID+原文件后缀名生成新的文件名
                photoName = uuid.toString() + suffix;
                try {
                    //保存文件
                    file.transferTo(new File(path + "/" + photoName));
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //将新文件名存储到emp对象中
        emp.setPhotoName(photoName);
       //调用Service层方法，最终将数据保存到数据库
        boolean flag = empService.add(emp);
        return "redirect:search";
	}

    /*
     *上传文件后响应回新生成的文件名
     */
    @RequestMapping("upload")
    @ResponseBody
    public String upload( @RequestParam(value = "photo") MultipartFile[] photos) throws FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath() + "/static/photo/";
        String photoName = "";
        for (MultipartFile file : photos) {
            if (!file.isEmpty()) {
                UUID uuid = UUID.randomUUID();
                String oldName = file.getOriginalFilename();
                String suffix=oldName.substring(oldName.lastIndexOf("."));
                photoName = uuid.toString() + suffix;
                try {
                    file.transferTo(new File(path + "/" + photoName));
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return photoName;
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
