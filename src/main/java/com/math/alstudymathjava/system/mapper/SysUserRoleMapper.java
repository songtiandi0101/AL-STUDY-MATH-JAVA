package com.math.alstudymathjava.system.mapper;



import com.math.alstudymathjava.system.entity.user.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserRoleMapper {
    int insert(SysUserRole sysUserRole);
    List<SysUserRole> selectByUserId(Long userId);
    int deleteById(Long id);

    /** ⭐ 根据用户ID删除其所有角色关联 */
    int deleteByUserId(@Param("userId") Long userId);

    /** ⭐ 根据用户ID查询其角色ID列表 */
    List<Long> selectRoleIdsByUserId(@Param("userId") Long userId);
}

