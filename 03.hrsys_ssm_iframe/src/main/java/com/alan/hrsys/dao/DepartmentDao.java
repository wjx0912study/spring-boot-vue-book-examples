package com.alan.hrsys.dao;

import com.alan.hrsys.entity.Department;

import java.util.List;

public interface DepartmentDao {

    public List<Department> search();

    public Department searchById(Integer id);

    public int add(Department dep);

    public int update(Department dep);

    public int delete(int id);

}
