package com.math.alstudymathjava.system.mapper;


import com.math.alstudymathjava.system.entity.user.SysMenu;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface SysMenuMapper  {

    int insert(SysMenu sysMenu);

    SysMenu selectById(@Param("id") Long id);

    List<SysMenu> selectList(@Param("menuName") String menuName);

    int update(SysMenu sysMenu);

    int deleteById(@Param("id") Long id);

    List<SysMenu> selectMenuByRoleId(@Param("roleId") Long roleId);

    List<SysMenu> getMenuTreeAll();
}