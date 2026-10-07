package com.math.alstudymathjava.system.service.impl;



import com.math.alstudymathjava.system.mapper.SysUserRoleMapper;
import com.math.alstudymathjava.system.entity.user.SysUserRole;
import com.math.alstudymathjava.system.service.SysUserRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public int add(SysUserRole sysUserRole) {
        return sysUserRoleMapper.insert(sysUserRole);
    }

    @Override
    public List<SysUserRole> selectByUserId(Long userId) {
        return sysUserRoleMapper.selectByUserId(userId);
    }

    @Override
    public int deleteById(Long id) {
        return sysUserRoleMapper.deleteById(id);
    }
}
