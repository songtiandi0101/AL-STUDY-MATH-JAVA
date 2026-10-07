package com.math.alstudymathjava.system.controller;




import com.math.alstudymathjava.system.entity.user.SysPermission;
import com.math.alstudymathjava.system.service.SysPermissionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sys/permission")
public class SysPermissionController {

    @Resource
    private SysPermissionService sysPermissionService;

    @PostMapping("/add")
    public String add(@RequestBody SysPermission sysPermission) {
        int rows = sysPermissionService.addPermission(sysPermission);
        return rows > 0 ? "success" : "fail";
    }

    @GetMapping("/get/{id}")
    public SysPermission getById(@PathVariable Long id) {
        return sysPermissionService.getPermissionById(id);
    }

    @GetMapping("/list")
    public List<SysPermission> list(SysPermission query) {
        return sysPermissionService.getPermissionList(query);
    }

    @PutMapping("/update")
    public String update(@RequestBody SysPermission sysPermission) {
        int rows = sysPermissionService.updatePermission(sysPermission);
        return rows > 0 ? "success" : "fail";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        int rows = sysPermissionService.deletePermission(id);
        return rows > 0 ? "success" : "fail";
    }
}
