package com.math.alstudymathjava.system.mapper;



import com.math.alstudymathjava.system.entity.user.SysUserRole;
import java.util.List;

public interface SysUserRoleMapper {
    int insert(SysUserRole sysUserRole);
    List<SysUserRole> selectByUserId(Long userId);
    int deleteById(Long id);
}

