package com.math.alstudymathjava.system.service.impl;


import com.math.alstudymathjava.system.entity.user.SysPermission;
import  com.math.alstudymathjava.system.service.SysPermissionService;
import com.math.alstudymathjava.system.mapper.SysPermissionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public int addPermission(SysPermission sysPermission) {
        sysPermission.setDelFlag(0);
        sysPermission.setCreateTime(LocalDateTime.now());
        sysPermission.setUpdateTime(LocalDateTime.now());
        return sysPermissionMapper.insert(sysPermission);
    }

    @Override
    public SysPermission getPermissionById(Long id) {
        return sysPermissionMapper.selectById(id);
    }

    @Override
    public List<SysPermission> getPermissionList(SysPermission query) {
        return sysPermissionMapper.selectList(query);
    }

    @Override
    public int updatePermission(SysPermission sysPermission) {
        sysPermission.setUpdateTime(LocalDateTime.now());
        return sysPermissionMapper.update(sysPermission);
    }

    @Override
    public int deletePermission(Long id) {
        return sysPermissionMapper.deleteById(id);
    }
}

