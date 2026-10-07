package com.math.alstudymathjava.system.service.impl;



import com.math.alstudymathjava.system.entity.user.PageResult;
import com.math.alstudymathjava.system.mapper.SysUserMapper;
import com.math.alstudymathjava.system.entity.user.SysUser;
import com.math.alstudymathjava.system.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public Map<String, Object> login(SysUser loginUser) {
        Map<String,Object> result = new HashMap<>();
        SysUser user = sysUserMapper.selectUser(loginUser.getUsername(), loginUser.getPassword());
        if(user != null){
            String token = UUID.randomUUID().toString();
            result.put("code",200);
            result.put("msg","登录成功");
            Map<String,Object> data = new HashMap<>();
            data.put("token",token);
            result.put("data",data);
        }else{
            result.put("code",500);
            result.put("msg","账号或密码错误");
        }
        return result;
    }

    @Override
    public int addUser(SysUser sysUser) {
        // 校验：未删除用户里是否存在同名用户名
        boolean exist = checkUsernameExist(sysUser.getUsername());
        if(exist){
            throw new RuntimeException("用户名已存在！");
        }
        sysUser.setDelFlag(0);
        sysUser.setStatus(1);
        sysUser.setCreateTime(LocalDateTime.now());
        sysUser.setUpdateTime(LocalDateTime.now());
        return sysUserMapper.insert(sysUser);
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
    public int updateUser(SysUser sysUser) {
        sysUser.setUpdateTime(LocalDateTime.now());
        return sysUserMapper.update(sysUser);
    }

    @Override
    public int deleteUser(Long id) {
        return sysUserMapper.deleteById(id);
    }
}
