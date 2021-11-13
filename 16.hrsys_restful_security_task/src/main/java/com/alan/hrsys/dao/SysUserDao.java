package com.alan.hrsys.dao;

import com.alan.hrsys.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysUserDao extends JpaRepository<SysUser,Integer>, JpaSpecificationExecutor<SysUser> {
    SysUser findByUsername(String username);
}
