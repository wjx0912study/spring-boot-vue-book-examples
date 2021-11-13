package com.alan.hrsys.test_redis;

import com.alan.hrsys.test_redis.dao.EmployeeDao;
import com.alan.hrsys.test_redis.entity.Department;
import com.alan.hrsys.test_redis.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TestRedisRepository {
    @Autowired
    private EmployeeDao employeeDao;

    @Test
    void setEmp() {
        Employee emp1 = new Employee();
        emp1.setName("李静");
        emp1.setGender("女");
        emp1.setAge(25);
        Department dep=new Department();
        dep.setName("开发部");
        emp1.setDep(dep);
        employeeDao.save(emp1);
        Employee emp2 = new Employee();
        emp2.setName("王正");
        emp2.setGender("男");
        emp2.setAge(30);

        employeeDao.save(emp2);
    }

    @Test
    void getEmps() {
        Iterable<Employee> iterable = employeeDao.findAll();
        Iterator<Employee> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            System.out.println(emp.getId() + " " + emp.getName() + " " + emp.getGender() + " " + emp.getAge());
        }
    }

    @Test
    void getEmp() {
        Employee emp = employeeDao.findById(313319554).get();
        System.out.println(emp.getId() + " " + emp.getName() + " " + emp.getGender() + " " + emp.getAge());

    }

    @Test
    void getEmpByName() {
        Iterable<Employee> iterable = employeeDao.findByName("李静");
        Iterator<Employee> iterator = iterable.iterator();
        System.out.println(iterator.hasNext());
        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            System.out.println(emp.getId() + " " + emp.getName() + " " + emp.getGender() + " " + emp.getAge()+" "+emp.getDep().getName());
        }
    }
}
