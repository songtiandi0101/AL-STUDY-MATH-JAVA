package com.math.alstudymathjava.system.mapper;

import com.math.alstudymathjava.system.entity.user.SysRolePerm;
import java.util.List;

public interface SysRolePermMapper {
    int insert(SysRolePerm sysRolePerm);
    List<SysRolePerm> selectByRoleId(Long roleId);
    int deleteById(Long id);
}