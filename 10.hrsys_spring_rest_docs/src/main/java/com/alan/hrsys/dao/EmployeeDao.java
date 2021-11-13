package com.alan.hrsys.dao;

import com.alan.hrsys.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {
    @Modifying
    @Query("update Employee emp set emp.dep=null where emp.dep.id=:depId")
    int updateByDep(Integer depId);
}
