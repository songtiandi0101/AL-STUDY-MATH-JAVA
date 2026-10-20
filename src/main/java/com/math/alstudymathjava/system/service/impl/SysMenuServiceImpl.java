package com.math.alstudymathjava.system.service.impl;

import com.math.alstudymathjava.system.entity.user.SysMenu;
import com.math.alstudymathjava.system.mapper.SysMenuMapper;
import com.math.alstudymathjava.system.service.SysMenuService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public int addMenu(SysMenu sysMenu) {
        LocalDateTime now = LocalDateTime.now();
        sysMenu.setCreateTime(now);
        sysMenu.setUpdateTime(now);
        sysMenu.setDelFlag(0);
        return sysMenuMapper.insert(sysMenu);
    }

    @Override
    public SysMenu getMenuById(Long id) {
        return sysMenuMapper.selectById(id);
    }

    @Override
    public List<SysMenu> getMenuList(SysMenu query) {
        return sysMenuMapper.selectList(query.getMenuName());
    }

    @Override
    public int updateMenu(SysMenu sysMenu) {
        return sysMenuMapper.update(sysMenu);
    }

    @Override
    public int deleteMenu(Long id) {
        return sysMenuMapper.deleteById(id);
    }

    @Override
    public List<SysMenu> getMenuTreeByRoleId(Long roleId) {
        List<SysMenu> flatList = sysMenuMapper.selectMenuByRoleId(roleId);
        return buildTree(flatList, 0L);
    }

    @Override
    public List<SysMenu> getMenuTreeAll() {
        List<SysMenu> flatList = sysMenuMapper.getMenuTreeAll();
        return buildTree(flatList, 0L);
    }

    private List<SysMenu> buildTree(List<SysMenu> allMenu, Long parentId) {
        List<SysMenu> treeList = new ArrayList<>();
        for (SysMenu menu : allMenu) {
            if (menu.getParentId().equals(parentId)) {
                List<SysMenu> children = buildTree(allMenu, menu.getId());
                menu.setChildren(children);
                treeList.add(menu);
            }
        }
        return treeList;
    }
}
