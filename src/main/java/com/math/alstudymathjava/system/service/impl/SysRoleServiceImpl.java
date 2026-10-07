package com.math.alstudymathjava.system.service.impl;



import com.math.alstudymathjava.system.mapper.SysRoleMapper;
import com.math.alstudymathjava.system.entity.user.SysRole;
import com.math.alstudymathjava.system.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

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
    public List<SysRole> getRoleList(SysRole query) {
        return sysRoleMapper.selectList(query);
    }

    @Override
    public int updateRole(SysRole sysRole) {
        sysRole.setUpdateTime(LocalDateTime.now());
        return sysRoleMapper.update(sysRole);
    }

    @Override
    public int deleteRole(Long id) {
        return sysRoleMapper.deleteById(id);
    }
}

