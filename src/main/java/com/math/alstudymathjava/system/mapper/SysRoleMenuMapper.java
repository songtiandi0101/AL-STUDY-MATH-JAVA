package com.math.alstudymathjava.system.mapper;

import com.math.alstudymathjava.system.entity.user.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper {

    /** 插入一条角色-菜单关联 */
    int insert(SysRoleMenu sysRoleMenu);

    /** ⭐ 按角色ID查询菜单ID列表（方法名补上 s，与 Service 调用一致） */
    List<Long> selectMenuIdsByRoleId(@Param("roleId") Long roleId);

    /** 按角色ID删除所有菜单关联 */
    int deleteByRoleId(@Param("roleId") Long roleId);
}