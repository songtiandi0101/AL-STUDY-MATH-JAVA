package com.math.alstudymathjava.system.service;

import com.math.alstudymathjava.system.entity.user.PageResult;
import com.math.alstudymathjava.system.entity.user.SysRole;

import java.util.List;

public interface SysRoleService {

    int addRole(SysRole sysRole);

    SysRole getRoleById(Long id);

    int updateRole(SysRole sysRole);

    int deleteRole(Long id);

    // 分页查询方法
    PageResult<SysRole> getRolePageList(SysRole query);

    List<SysRole> getAllRoles();

    // ==================== ⭐ 新增：角色-菜单权限相关 ====================

    /**
     * 查询某个角色已分配的菜单ID列表（用于编辑权限时回显勾选状态）
     */
    List<Long> getMenuIdsByRoleId(Long roleId);

    /**
     * 给角色分配菜单权限（全量覆盖：先删旧，再插新）
     */
    String assignMenus(Long roleId, List<Long> menuIds);
}