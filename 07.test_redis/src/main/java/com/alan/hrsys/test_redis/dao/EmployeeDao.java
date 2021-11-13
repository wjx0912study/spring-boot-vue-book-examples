package com.alan.hrsys.test_redis.dao;

import com.alan.hrsys.test_redis.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Integer> {
    Iterable<Employee> findByName(String name);
}
