package com.math.alstudymathjava.system.service.impl;

import com.math.alstudymathjava.system.entity.user.SysRoleMenu;
import com.math.alstudymathjava.system.mapper.SysRoleMenuMapper;
import com.math.alstudymathjava.system.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.annotation.Resource;
import java.util.List;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignMenu(Long roleId, List<Long> menuIdList) {
        // 删除该角色原有菜单关联
        sysRoleMenuMapper.deleteByRoleId(roleId);
        if(menuIdList != null && !menuIdList.isEmpty()){
            for (Long menuId : menuIdList) {
                SysRoleMenu rm = new SysRoleMenu();
                rm.setRoleId(roleId);
                rm.setMenuId(menuId);
                sysRoleMenuMapper.insert(rm);
            }
        }
    }

    @Override
    public List<Long> getMenuIdListByRoleId(Long roleId) {
        return sysRoleMenuMapper.selectMenuIdsByRoleId(roleId);
    }
}
