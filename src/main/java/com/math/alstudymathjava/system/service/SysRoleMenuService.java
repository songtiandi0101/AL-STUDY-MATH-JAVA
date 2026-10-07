package com.math.alstudymathjava.system.service;

import java.util.List;

public interface SysRoleMenuService {
    /**
     * 角色分配菜单：先删旧关联，批量新增勾选的菜单id
     */
    void assignMenu(Long roleId, List<Long> menuIdList);
    List<Long> getMenuIdListByRoleId(Long roleId);


}
