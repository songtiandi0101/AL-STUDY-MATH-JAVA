package com.math.alstudymathjava.system.service.impl;

import com.math.alstudymathjava.system.entity.user.PageResult;
import com.math.alstudymathjava.system.entity.user.SysRole;
import com.math.alstudymathjava.system.entity.user.SysRoleMenu;
import com.math.alstudymathjava.system.mapper.SysRoleMapper;
import com.math.alstudymathjava.system.mapper.SysRoleMenuMapper;
import com.math.alstudymathjava.system.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    // ⭐ 新增：注入角色-菜单关联 Mapper
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public int addRole(SysRole sysRole) {
        sysRole.setDelFlag(0);
        sysRole.setCreateTime(LocalDateTime.now());
        sysRole.setUpdateTime(LocalDateTime.now());
        return sysRoleMapper.insert(sysRole);
    }

    @Override
    public SysRole getRoleById(Long id) {
        return sysRoleMapper.selectById(id);
    }

    @Override
    public PageResult<SysRole> getRolePageList(SysRole query) {
        Integer pageNum = query.getPageNum();
        Integer pageSize = query.getPageSize();
        int start = (pageNum - 1) * pageSize;
        query.setStart(start);

        PageResult<SysRole> pageResult = new PageResult<>();
        List<SysRole> records = sysRoleMapper.selectPage(query);
        long total = sysRoleMapper.countPage(query);

        pageResult.setRecords(records);
        pageResult.setTotal(total);
        return pageResult;
    }

    @Override
    public int updateRole(SysRole sysRole) {
        sysRole.setUpdateTime(LocalDateTime.now());
        return sysRoleMapper.update(sysRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)   // ⭐ 删除角色时同时清理关联
    public int deleteRole(Long id) {
        // 先删除角色-菜单关联，避免残留脏数据
        sysRoleMenuMapper.deleteByRoleId(id);
        return sysRoleMapper.deleteById(id);
    }

    @Override
    public List<SysRole> getAllRoles() {
        return sysRoleMapper.selectAll();
    }

    // ==================== ⭐ 新增：角色-菜单权限相关 ====================

    @Override
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        return sysRoleMenuMapper.selectMenuIdsByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String assignMenus(Long roleId, List<Long> menuIds) {
        // 1. 先删除该角色旧的菜单绑定
        sysRoleMenuMapper.deleteByRoleId(roleId);
        // 2. 再插入新的菜单绑定
        if (menuIds != null && !menuIds.isEmpty()) {
            for (Long menuId : menuIds) {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                sysRoleMenuMapper.insert(roleMenu);
            }
        }
        return "success";
    }
}