package com.alan.hrsys;

import com.alan.hrsys.dao.DepartmentDao;
import com.alan.hrsys.dao.EmployeeDao;
import com.alan.hrsys.entity.Department;
import com.alan.hrsys.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EmployeeDaoTest {
    @Autowired
    EmployeeDao empDao;
    @Autowired
    DepartmentDao depDao;
    @Test
    public void testSearchOne() {
        Employee emp = empDao.findById(2).get();
//        Employee emp=empDao.getOne(2);
        Employee e = new Employee();
        System.out.println(emp.getId() + " " + emp.getName() + " " + emp.getGender());

    }

    @Test
    public void testSearch() {
        List<Employee> list = empDao.findAll();
        for (Employee emp : list) {
            System.out.println(emp.getId() + " " + emp.getName() + " " + emp.getGender());
        }
    }

    @Test
    public void testAdd() {
        Employee emp = new Employee();
        emp.setNumber(1010);
        emp.setName("王杰");
        emp.setGender("女");
        emp.setAge(23);
        Employee newEmp = empDao.save(emp);
        System.out.println(newEmp.getId());
    }

    @Test
    public void testUpdate() {
        Employee emp = empDao.findById(1).get();
        emp.setNumber(1009);
        emp.setAge(26);
        Employee newEmp = empDao.save(emp);
        System.out.println(newEmp.getId());
    }

    @Test
    public void testDelete() {
        empDao.deleteById(1);
    }

    @Test
    public void testSearch2() {
        // List<Employee> list = empDao.findByGender("女");
        //List<Employee> list = empDao.findByGenderAndAge("女",23);
        //List<Employee> list = empDao.findByNameAndGenderAndAge("王玉","女",24);
        List<Employee> list = empDao.findByNameContainingAndGenderAndAge("王", "女", 23);
        for (Employee emp : list) {
            System.out.println(emp.getId() + " " + emp.getName() + " " + emp.getGender());
        }
    }

    @Test
    public void testSearch3() {
        // List<Employee> list = empDao.findByName("王玉");
        List<Employee> list = empDao.findByNameNative("王玉");
        for (Employee emp : list) {
            System.out.println(emp.getId() + " " + emp.getName() + " " + emp.getGender());
        }
    }

    @Test
    public void testUpdate2() {
        // int rs= empDao.updateByNumber(1010);
        Employee emp = new Employee();
        emp.setAge(33);
        emp.setNumber(1010);
        // int rs= empDao.updateByNumber(emp);
        int rs = empDao.updateByNumberNative(emp);
        System.out.println(rs);
    }

    @Test
    public void testSearchWithDep() {
        List<Employee> list = empDao.findAll();
        for (Employee emp : list) {
            System.out.print(emp.getId() + " " + emp.getName());
            System.out.println("隔断符");
            System.out.println(emp.getDep().getName());
        }
    }
    @Test
    public void testAddWithDep() {
        Employee emp = new Employee();
        emp.setNumber(1011);
        emp.setName("李明");
        emp.setGender("男");
        emp.setAge(25);
        Department dep=depDao.findById(3).get();
        dep.setName("产品部1");
        emp.setDep(dep);
        Employee newEmp = empDao.save(emp);
        System.out.println(newEmp.getId());
    }
}
