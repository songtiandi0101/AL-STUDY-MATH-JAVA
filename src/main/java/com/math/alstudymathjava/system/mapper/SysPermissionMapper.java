package com.math.alstudymathjava.system.mapper;


import com.math.alstudymathjava.system.entity.user.SysPermission;
import java.util.List;

public interface SysPermissionMapper {
    int insert(SysPermission sysPermission);
    SysPermission selectById(Long id);
    List<SysPermission> selectList(SysPermission query);
    int update(SysPermission sysPermission);
    int deleteById(Long id);
}

