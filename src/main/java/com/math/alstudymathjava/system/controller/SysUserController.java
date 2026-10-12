package com.math.alstudymathjava.system.controller;

import com.math.alstudymathjava.system.entity.user.PageResult;
import com.math.alstudymathjava.system.entity.user.SysUser;
import com.math.alstudymathjava.system.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody SysUser loginUser){
        return sysUserService.login(loginUser);
    }

    @PostMapping("/add")
    public String add(@RequestBody SysUser sysUser) {
        try{
            int rows = sysUserService.addUser(sysUser);
            return rows > 0 ? "success" : "fail";
        }catch (RuntimeException e){
            // 返回错误信息给前端
            return e.getMessage();
        }
    }

    @GetMapping("/get/{id}")
    public SysUser getById(@PathVariable Long id) {
        return sysUserService.getUserById(id);
    }

    // 修改：返回分页PageResult，调用分页方法
    @GetMapping("/list")
    public PageResult<SysUser> list(SysUser query) {
        return sysUserService.getUserPageList(query);
    }

    @PutMapping("/update")
    public String update(@RequestBody SysUser sysUser) {
        int rows = sysUserService.updateUser(sysUser);
        return rows > 0 ? "success" : "fail";
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {     // ⭐ 加上 ("id")
        int rows = sysUserService.deleteUser(id);
        return rows > 0 ? "success" : "fail";
    }
}
