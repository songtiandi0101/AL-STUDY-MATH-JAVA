package com.math.alstudymathjava.system.controller;

import com.math.alstudymathjava.system.entity.user.SysMenu;
import com.math.alstudymathjava.system.service.SysMenuService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    @PostMapping("/add")
    public String add(@RequestBody SysMenu sysMenu) {
        int rows = sysMenuService.addMenu(sysMenu);
        return rows > 0 ? "success" : "fail";
    }

    @GetMapping("/get/{id}")
    public SysMenu getById(@PathVariable Long id) {
        return sysMenuService.getMenuById(id);
    }

    @GetMapping("/list")
    public List<SysMenu> list(SysMenu query) {
        return sysMenuService.getMenuList(query);
    }

    @PutMapping("/update")
    public String update(@RequestBody SysMenu sysMenu) {
        int rows = sysMenuService.updateMenu(sysMenu);
        return rows > 0 ? "success" : "fail";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        int rows = sysMenuService.deleteMenu(id);
        return rows > 0 ? "success" : "fail";
    }

    // 登录后获取角色对应的菜单树，给前端Vue渲染侧边栏
    /*@GetMapping("/getMenuTree")
    public List<SysMenu> getMenuTree(@RequestParam Long roleId) {
        return sysMenuService.getMenuTreeByRoleId(roleId);
    }*/

    // 修改后
    @GetMapping("/getMenuTree")
    public List<SysMenu> getMenuTree() {
        // 直接查询所有菜单，组装树返回
        return sysMenuService.getMenuTreeAll();
    }
}

