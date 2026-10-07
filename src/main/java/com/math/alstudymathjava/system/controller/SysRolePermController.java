package com.math.alstudymathjava.system.controller;

import com.math.alstudymathjava.system.entity.user.SysRolePerm;
import com.math.alstudymathjava.system.service.SysRolePermService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sys/rolePerm")
public class SysRolePermController {

    @Resource
    private SysRolePermService sysRolePermService;

    @PostMapping("/add")
    public String add(@RequestBody SysRolePerm sysRolePerm) {
        int rows = sysRolePermService.add(sysRolePerm);
        return rows > 0 ? "success" : "fail";
    }

    @GetMapping("/listByRoleId/{roleId}")
    public List<SysRolePerm> listByRoleId(@PathVariable Long roleId) {
        return sysRolePermService.selectByRoleId(roleId);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        int rows = sysRolePermService.deleteById(id);
        return rows > 0 ? "success" : "fail";
    }
}
