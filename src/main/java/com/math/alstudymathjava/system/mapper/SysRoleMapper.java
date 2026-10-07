package com.math.alstudymathjava.system.mapper;



import com.math.alstudymathjava.system.entity.user.SysRole;
import java.util.List;

public interface SysRoleMapper {
    int insert(SysRole sysRole);
    SysRole selectById(Long id);
    List<SysRole> selectList(SysRole query);
    int update(SysRole sysRole);
    int deleteById(Long id);
}
