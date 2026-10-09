package com.math.alstudymathjava.system.mapper;



import com.math.alstudymathjava.system.entity.user.SysRole;
import com.math.alstudymathjava.system.entity.user.SysUser;

import java.util.List;

public interface SysRoleMapper {
    int insert(SysRole sysRole);
    SysRole selectById(Long id);
    List<SysRole> selectList(SysRole query);
    int update(SysRole sysRole);
    int deleteById(Long id);

    /**
     * 用户分页查询
     * @param query 查询条件、分页参数
     * @return 当前页用户数据
     */
    List<SysRole> selectPage(SysRole query);

    /**
     * 查询用户总条数（分页统计总数）
     * @param query 查询条件
     * @return 总记录数
     */
    long countPage(SysRole query);
}
