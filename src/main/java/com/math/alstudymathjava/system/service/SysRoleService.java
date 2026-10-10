package com.math.alstudymathjava.system.service;




import com.math.alstudymathjava.system.entity.user.PageResult;
import com.math.alstudymathjava.system.entity.user.SysRole;
import com.math.alstudymathjava.system.entity.user.SysUser;

import java.util.List;

public interface SysRoleService {
    int addRole(SysRole sysRole);
    SysRole getRoleById(Long id);

    int updateRole(SysRole sysRole);
    int deleteRole(Long id);
    // 新增分页查询方法
    PageResult<SysRole> getRolePageList(SysRole query);
    List<SysRole> getAllRoles();
}
