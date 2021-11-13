package com.alan.hrsys.dao;

import com.alan.hrsys.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDao  extends JpaRepository<Department,Integer> {

}
