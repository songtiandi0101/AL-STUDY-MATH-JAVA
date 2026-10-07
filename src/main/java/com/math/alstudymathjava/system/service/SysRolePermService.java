package com.math.alstudymathjava.system.service;



import com.math.alstudymathjava.system.entity.user.SysRolePerm;
import java.util.List;

public interface SysRolePermService {
    int add(SysRolePerm sysRolePerm);
    List<SysRolePerm> selectByRoleId(Long roleId);
    int deleteById(Long id);
}

