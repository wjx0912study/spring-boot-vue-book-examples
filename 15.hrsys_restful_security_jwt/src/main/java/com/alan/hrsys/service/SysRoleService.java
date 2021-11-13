package com.alan.hrsys.service;


import com.alan.hrsys.entity.SysRole;

import java.util.List;

public interface SysRoleService {
     List<SysRole> search();
    boolean add(SysRole sysRole);

    boolean delete(Integer id);

     SysRole searchById(Integer id);

    boolean update(SysRole sysRole);
}
