package com.alan.hrsys.service;

import com.alan.hrsys.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

	Page<Employee> search(Employee condition, Integer page, Integer size);

	Employee searchById(Integer id);

	boolean add(Employee emp);

	boolean update(Employee emp);

	boolean delete(Integer id);


}
