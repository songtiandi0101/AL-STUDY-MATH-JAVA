package com.math.alstudymathjava.system.service;




import com.math.alstudymathjava.system.entity.user.PageResult;
import com.math.alstudymathjava.system.entity.user.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserService {
    int addUser(SysUser sysUser);
    SysUser getUserById(Long id);

    // 新增分页查询方法
    PageResult<SysUser> getUserPageList(SysUser query);
    int updateUser(SysUser sysUser);
    int deleteUser(Long id);
    Map<String,Object> login(SysUser loginUser);
    /**
     * 判断用户名是否存在（未删除）
     */
    boolean checkUsernameExist(String username);
}

