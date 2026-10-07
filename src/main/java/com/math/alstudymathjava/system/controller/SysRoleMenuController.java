package com.math.alstudymathjava.system.controller;

import com.math.alstudymathjava.system.service.SysRoleMenuService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sys/roleMenu")
public class SysRoleMenuController {

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 根据角色ID查询已勾选的菜单ID列表（回显到前端复选框）
     */
    @GetMapping("/getMenuIds")
    public List<Long> getMenuIds(@RequestParam Long roleId) {
        return sysRoleMenuService.getMenuIdListByRoleId(roleId);
    }

    /**
     * 角色保存分配菜单，前端传：roleId + 菜单id数组
     */
    @PostMapping("/assign")
    public String assignMenu(@RequestParam Long roleId, @RequestBody List<Long> menuIdList) {
        sysRoleMenuService.assignMenu(roleId, menuIdList);
        return "success";
    }
}
