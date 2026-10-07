package com.math.alstudymathjava.system.service;




import com.math.alstudymathjava.system.entity.user.SysUserRole;

import java.util.List;

public interface SysUserRoleService {
    int add(SysUserRole sysUserRole);
    List<SysUserRole> selectByUserId(Long userId);
    int deleteById(Long id);
}
