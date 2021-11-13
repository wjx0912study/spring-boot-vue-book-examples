package com.alan.hrsys.service.impl;


import com.alan.hrsys.entity.SysPermission;
import com.alan.hrsys.entity.SysRole;
import com.alan.hrsys.entity.SysUser;
import com.alan.hrsys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        SysUser sysUser = sysUserService.searchByUsername(username);
        if (sysUser == null) {        // 判断用户是否存在
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 添加角色
        List<SysRole> sysRoles = sysUser.getRoles();
        for (SysRole sysRole : sysRoles) {
           authorities.add(new SimpleGrantedAuthority(sysRole.getCode()));
            //添加权限
            for (SysPermission sysPermission : sysRole.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(sysPermission.getCode()));
            }
        }
        return new User(sysUser.getUsername(), sysUser.getPassword(), authorities);        // 返回UserDetails实现类
    }

    //转换成自身业务的SysUser
    public SysUser toSysUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return sysUserService.searchByUsername(user.getUsername());
    }
}
