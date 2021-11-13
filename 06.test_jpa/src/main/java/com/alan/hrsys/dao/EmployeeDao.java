package com.alan.hrsys.dao;

import com.alan.hrsys.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    @Query(value="from Employee emp")
    List<Employee> find();

    List<Employee> findByGender(String gender);

    List<Employee> findByGenderAndAge(String gender, Integer age);

    List<Employee> findByNameAndGenderAndAge(String name, String gender, Integer age);

    List<Employee> findByNameContainingAndGenderAndAge(String name, String gender, Integer age);

    @Query(value="from Employee emp where emp.name=:name")
    List<Employee> findByName(String name);
    @Query(value="from Employee emp where e.name=:#{#e.age}")
    List<Employee> findByName(Employee e);
    @Query(value="select * from employee where name=:name",nativeQuery = true)
    List<Employee> findByNameNative(String name);

    @Transactional//配置全局事务，此处可不加
    @Modifying
    @Query(value="update Employee emp set emp.age=30 where emp.number=:number")
    int  updateByNumber(Integer number);

    @Transactional//配置全局事务，此处可不加
    @Modifying
    @Query(value="update Employee emp set emp.age=:#{#e.age} where emp.number=:#{#e.number}")
    int  updateByNumber( Employee e);

    @Transactional//配置全局事务，此处可不加
    @Modifying
    @Query(value="update employee  set age=:#{#e.age} where number=:#{#e.number}",nativeQuery = true)
    int  updateByNumberNative(Employee e);
}
