package com.alan.hrsys.dao;

import com.alan.hrsys.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysPermissionDao extends JpaRepository<SysPermission,Integer>, JpaSpecificationExecutor<SysPermission> {
}
