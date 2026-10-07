package com.math.alstudymathjava.system.service;




import com.math.alstudymathjava.system.entity.user.SysRole;

import java.util.List;

public interface SysRoleService {
    int addRole(SysRole sysRole);
    SysRole getRoleById(Long id);
    List<SysRole> getRoleList(SysRole query);
    int updateRole(SysRole sysRole);
    int deleteRole(Long id);
}
