package com.alan.hrsys.dao;

import com.alan.hrsys.entity.Department;
import com.alan.hrsys.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DepartmentDao  extends JpaRepository<Department,Integer> {

}
