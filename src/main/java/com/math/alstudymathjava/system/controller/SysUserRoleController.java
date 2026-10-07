package com.math.alstudymathjava.system.controller;


import com.math.alstudymathjava.system.entity.user.SysUserRole;
import com.math.alstudymathjava.system.service.SysUserRoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sys/userRole")
public class SysUserRoleController {

    @Resource
    private SysUserRoleService sysUserRoleService;

    @PostMapping("/add")
    public String add(@RequestBody SysUserRole sysUserRole) {
        int rows = sysUserRoleService.add(sysUserRole);
        return rows > 0 ? "success" : "fail";
    }

    @GetMapping("/listByUserId/{userId}")
    public List<SysUserRole> listByUserId(@PathVariable Long userId) {
        return sysUserRoleService.selectByUserId(userId);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        int rows = sysUserRoleService.deleteById(id);
        return rows > 0 ? "success" : "fail";
    }
}

