package com.math.alstudymathjava.system.mapper;

import com.math.alstudymathjava.system.entity.user.SysUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface SysUserMapper {

    int insert(SysUser sysUser);

    SysUser selectById(Long id);

    List<SysUser> selectList(SysUser query);

    int update(SysUser sysUser);

    int deleteById(Long id);

    SysUser selectUser(@Param("username") String username, @Param("password") String password);

    /**
     * 查询未删除用户，根据用户名统计数量
     */
    int countUserByUsername(@Param("username") String username);

    // ========== 新增分页相关方法 ==========
    /**
     * 用户分页查询
     * @param query 查询条件、分页参数
     * @return 当前页用户数据
     */
    List<SysUser> selectPage(SysUser query);

    /**
     * 查询用户总条数（分页统计总数）
     * @param query 查询条件
     * @return 总记录数
     */
    long countPage(SysUser query);
}

