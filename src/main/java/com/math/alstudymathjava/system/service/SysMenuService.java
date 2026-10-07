package com.math.alstudymathjava.system.service;

import com.math.alstudymathjava.system.entity.user.SysMenu;
import java.util.List;

public interface SysMenuService {
    int addMenu(SysMenu sysMenu);
    SysMenu getMenuById(Long id);
    List<SysMenu> getMenuList(SysMenu query);
    int updateMenu(SysMenu sysMenu);
    int deleteMenu(Long id);

    // 新增：根据角色id获取菜单树（登录前端渲染侧边栏专用）
    List<SysMenu> getMenuTreeByRoleId(Long roleId);

    List<SysMenu> getMenuTreeAll();
}