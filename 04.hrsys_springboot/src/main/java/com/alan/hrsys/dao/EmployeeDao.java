package com.alan.hrsys.dao;

import com.alan.hrsys.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeDao {
    List<Employee> search(Employee condition);
    Employee searchById(int id);
    int add(Employee emp);

    int update(Employee emp);

    int delete(int id);

    int updateByDep(int depId);

}
