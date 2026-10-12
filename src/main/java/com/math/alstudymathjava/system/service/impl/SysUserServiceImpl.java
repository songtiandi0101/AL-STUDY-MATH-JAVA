package com.math.alstudymathjava.system.service.impl;



import com.math.alstudymathjava.system.entity.user.PageResult;
import com.math.alstudymathjava.system.entity.user.SysUserRole;
import com.math.alstudymathjava.system.mapper.SysUserMapper;
import com.math.alstudymathjava.system.entity.user.SysUser;
import com.math.alstudymathjava.system.mapper.SysUserRoleMapper;
import com.math.alstudymathjava.system.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Map<String, Object> login(SysUser loginUser) {
        Map<String, Object> result = new HashMap<>();
        SysUser user = sysUserMapper.selectUser(loginUser.getUsername(), loginUser.getPassword());
        if (user != null) {
            String token = UUID.randomUUID().toString();
            result.put("code", 200);
            result.put("msg", "登录成功");
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            result.put("data", data);
        } else {
            result.put("code", 500);
            result.put("msg", "账号或密码错误");
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)   // ⭐ 加事务
    public int addUser(SysUser sysUser) {
        // 校验用户名是否重复
        boolean exist = checkUsernameExist(sysUser.getUsername());
        if (exist) {
            throw new RuntimeException("用户名已存在！");
        }

        // 补全默认字段
        sysUser.setDelFlag(0);
        sysUser.setStatus(1);
        sysUser.setCreateTime(LocalDateTime.now());
        sysUser.setUpdateTime(LocalDateTime.now());

        // 插入用户主体（MyBatis 自动回填主键到 sysUser.id）
        int rows = sysUserMapper.insert(sysUser);

        // ⭐ 绑定角色
        if (sysUser.getRoleIds() != null && !sysUser.getRoleIds().isEmpty()) {
            for (Long roleId : sysUser.getRoleIds()) {
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(sysUser.getId());
                userRole.setRoleId(roleId);
                sysUserRoleMapper.insert(userRole);
            }
        }

        return rows;   // 保持返回 int
    }

    @Override
    public boolean checkUsernameExist(String username) {
        int count = sysUserMapper.countUserByUsername(username);
        return count > 0;
    }

    @Override
    public SysUser getUserById(Long id) {
        return sysUserMapper.selectById(id);
    }

    // 新增分页实现
    @Override
    public PageResult<SysUser> getUserPageList(SysUser query) {
        Integer pageNum = query.getPageNum();
        Integer pageSize = query.getPageSize();
        // 计算起始下标
        int start = (pageNum - 1) * pageSize;
        query.setStart(start);

        PageResult<SysUser> pageResult = new PageResult<>();
        // 查询当前页数据
        List<SysUser> records = sysUserMapper.selectPage(query);
        // 查询总条数
        long total = sysUserMapper.countPage(query);

        pageResult.setRecords(records);
        pageResult.setTotal(total);
        return pageResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)   // ⭐ 1. 加事务注解
    public int updateUser(SysUser sysUser) {
        sysUser.setUpdateTime(LocalDateTime.now());

        // ⭐ 2. 更新用户主体（返回影响行数）
        int rows = sysUserMapper.update(sysUser);

        // ⭐ 3. 先删除该用户旧的角色关联
        sysUserRoleMapper.deleteByUserId(sysUser.getId());

        // ⭐ 4. 再重新插入新的角色关联
        if (sysUser.getRoleIds() != null && !sysUser.getRoleIds().isEmpty()) {
            for (Long roleId : sysUser.getRoleIds()) {
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(sysUser.getId());
                userRole.setRoleId(roleId);
                sysUserRoleMapper.insert(userRole);
            }
        }

        return rows;   // ⭐ 5. 保持返回 int
    }

    @Override
    @Transactional(rollbackFor = Exception.class)   // ⭐ 1. 加上事务注解
    public int deleteUser(Long id) {
        // ⭐ 2. 先删除 sys_user_role 表中的角色绑定记录
        sysUserRoleMapper.deleteByUserId(id);

        // ⭐ 3. 再删除（或逻辑删除）sys_user 表中的用户记录
        return sysUserMapper.deleteById(id);
    }
}