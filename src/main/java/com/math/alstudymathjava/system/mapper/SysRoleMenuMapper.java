package com.math.alstudymathjava.system.mapper;


import com.math.alstudymathjava.system.entity.user.SysRoleMenu;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface SysRoleMenuMapper {
    int insert(SysRoleMenu sysRoleMenu);
    List<Long> selectMenuIdByRoleId(@Param("roleId") Long roleId);
    int deleteByRoleId(@Param("roleId") Long roleId);
}
