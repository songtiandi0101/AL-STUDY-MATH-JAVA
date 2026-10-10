package com.math.alstudymathjava.system.service.impl;



import com.math.alstudymathjava.system.entity.user.PageResult;
import com.math.alstudymathjava.system.entity.user.SysUser;
import com.math.alstudymathjava.system.mapper.SysRoleMapper;
import com.math.alstudymathjava.system.entity.user.SysRole;
import com.math.alstudymathjava.system.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public int addRole(SysRole sysRole) {
        sysRole.setDelFlag(0);
        sysRole.setCreateTime(LocalDateTime.now());
        sysRole.setUpdateTime(LocalDateTime.now());
        return sysRoleMapper.insert(sysRole);
    }

    @Override
    public SysRole getRoleById(Long id) {
        return sysRoleMapper.selectById(id);
    }


    // 新增分页实现
    @Override
    public PageResult<SysRole> getRolePageList(SysRole query) {
        Integer pageNum = query.getPageNum();
        Integer pageSize = query.getPageSize();
        // 计算起始下标
        int start = (pageNum - 1) * pageSize;
        query.setStart(start);

        PageResult<SysRole> pageResult = new PageResult<>();
        // 查询当前页数据
        List<SysRole> records = sysRoleMapper.selectPage(query);
        // 查询总条数
        long total = sysRoleMapper.countPage(query);

        pageResult.setRecords(records);
        pageResult.setTotal(total);
        return pageResult;
    }

    @Override
    public int updateRole(SysRole sysRole) {
        sysRole.setUpdateTime(LocalDateTime.now());
        return sysRoleMapper.update(sysRole);
    }

    @Override
    public int deleteRole(Long id) {
        return sysRoleMapper.deleteById(id);
    }

    @Override
    public List<SysRole> getAllRoles() {
        return sysRoleMapper.selectAll();
    }
}

