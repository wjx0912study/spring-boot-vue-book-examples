package com.alan.hrsys.controller;

import com.alan.hrsys.entity.Department;
import com.alan.hrsys.entity.Employee;
import com.alan.hrsys.service.DepartmentService;
import com.alan.hrsys.service.EmployeeService;
import com.alan.hrsys.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("emp")
@PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER','ADMIN')")
public class EmployeeController {

    @Autowired
    EmployeeService empService;
    @Autowired
    DepartmentService depService;


    @GetMapping
    public ServerResponse search(Employee condition, Integer page, Integer size ,HttpServletRequest request) {
        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());

        Page<Employee> emps = empService.search(condition, page, size);
        ServerResponse<List<Employee>> sr = new ServerResponse<>(emps.getTotalElements(), emps.toList());
        return sr;
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
    @RequestMapping("upload")
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
