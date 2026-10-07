package com.math.alstudymathjava.system.service;




import com.math.alstudymathjava.system.entity.user.SysPermission;

import java.util.List;

public interface SysPermissionService {
    int addPermission(SysPermission sysPermission);
    SysPermission getPermissionById(Long id);
    List<SysPermission> getPermissionList(SysPermission query);
    int updatePermission(SysPermission sysPermission);
    int deletePermission(Long id);
}
