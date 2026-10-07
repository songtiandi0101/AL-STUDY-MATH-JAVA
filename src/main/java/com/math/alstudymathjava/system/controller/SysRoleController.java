package com.math.alstudymathjava.system.controller;


import com.math.alstudymathjava.system.entity.user.SysRole;

import com.math.alstudymathjava.system.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    @PostMapping("/add")
    public String add(@RequestBody SysRole sysRole) {
        int rows = sysRoleService.addRole(sysRole);
        return rows > 0 ? "success" : "fail";
    }

    @GetMapping("/get/{id}")
    public SysRole getById(@PathVariable Long id) {
        return sysRoleService.getRoleById(id);
    }

    @GetMapping("/list")
    public List<SysRole> list(SysRole query) {
        return sysRoleService.getRoleList(query);
    }

    @PutMapping("/update")
    public String update(@RequestBody SysRole sysRole) {
        int rows = sysRoleService.updateRole(sysRole);
        return rows > 0 ? "success" : "fail";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        int rows = sysRoleService.deleteRole(id);
        return rows > 0 ? "success" : "fail";
    }
}
