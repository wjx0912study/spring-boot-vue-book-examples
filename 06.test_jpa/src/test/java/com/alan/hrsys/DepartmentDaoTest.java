package com.alan.hrsys;

import com.alan.hrsys.dao.DepartmentDao;
import com.alan.hrsys.dao.EmployeeDao;
import com.alan.hrsys.entity.Department;
import com.alan.hrsys.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DepartmentDaoTest {
    @Autowired
    DepartmentDao depDao;
    @Test
    public void testSearch() {
        List< Department> list = depDao.findAll();
        for ( Department dep : list) {
            System.out.println(dep.getId() + " " + dep.getName());
            for(Employee emp:dep.getEmps()){
                System.out.print(emp.getName()+" ");
            }
            System.out.println();
        }
    }

    @Test
    public void testAddWithEmp() {
        Department dep=new Department();
        dep.setName("质量部");
        Employee emp1 = new Employee();
        emp1.setNumber(1021);
        emp1.setName("李明");
        emp1.setGender("男");
        emp1.setAge(25);
        emp1.setDep(dep);
        Employee emp2 = new Employee();
        emp2.setNumber(1022);
        emp2.setName("李小明");
        emp2.setGender("男");
        emp2.setAge(25);
        emp2.setDep(dep);
        List<Employee> emps=new ArrayList<>();
        emps.add(emp1);
        emps.add(emp2);
        dep.setEmps(emps);
        Department newDep = depDao.save(dep);
    }
}
