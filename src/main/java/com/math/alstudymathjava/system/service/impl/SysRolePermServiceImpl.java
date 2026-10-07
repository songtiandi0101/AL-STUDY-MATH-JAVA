package com.math.alstudymathjava.system.service.impl;


import com.math.alstudymathjava.system.mapper.SysRolePermMapper;
import com.math.alstudymathjava.system.entity.user.SysRolePerm;
import com.math.alstudymathjava.system.service.SysRolePermService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SysRolePermServiceImpl implements SysRolePermService {

    @Resource
    private SysRolePermMapper sysRolePermMapper;

    @Override
    public int add(SysRolePerm sysRolePerm) {
        return sysRolePermMapper.insert(sysRolePerm);
    }

    @Override
    public List<SysRolePerm> selectByRoleId(Long roleId) {
        return sysRolePermMapper.selectByRoleId(roleId);
    }

    @Override
    public int deleteById(Long id) {
        return sysRolePermMapper.deleteById(id);
    }
}
