package com.math.alstudymathjava.system.controller;

import com.math.alstudymathjava.system.entity.user.PageResult;
import com.math.alstudymathjava.system.entity.user.SysRole;
import com.math.alstudymathjava.system.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public SysRole getById(@PathVariable("id") Long id) {   // ⭐ 加上 ("id")
        return sysRoleService.getRoleById(id);
    }

    // 分页查询（管理页面用）
    @GetMapping("/list")
    public PageResult<SysRole> list(SysRole query) {
        return sysRoleService.getRolePageList(query);
    }

    /**
     * 查询所有角色（下拉框用，不分页）
     * 接口地址：GET /sys/role/all
     */
    @GetMapping("/all")
    public List<SysRole> all() {
        return sysRoleService.getAllRoles();
    }

    @PutMapping("/update")
    public String update(@RequestBody SysRole sysRole) {
        int rows = sysRoleService.updateRole(sysRole);
        return rows > 0 ? "success" : "fail";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {     // ⭐ 加上 ("id")
        int rows = sysRoleService.deleteRole(id);
        return rows > 0 ? "success" : "fail";
    }

    // ==================== ⭐ 新增：角色-菜单权限相关 ====================

    /**
     * 查询某个角色已分配的菜单ID列表（编辑角色权限时回显勾选状态）
     * 接口地址：GET /sys/role/menuIds/{roleId}
     */
    @GetMapping("/menuIds/{roleId}")
    public List<Long> getMenuIds(@PathVariable("roleId") Long roleId) {
        return sysRoleService.getMenuIdsByRoleId(roleId);
    }

    /**
     * 给角色分配菜单权限（全量覆盖：先删旧，再插新）
     * 请求体示例：{ "roleId": 1, "menuIds": [1, 2, 3] }
     * 接口地址：POST /sys/role/assignMenus
     */
    @PostMapping("/assignMenus")
    public String assignMenus(@RequestBody Map<String, Object> params) {
        try {
            Long roleId = Long.valueOf(params.get("roleId").toString());
            @SuppressWarnings("unchecked")
            List<Long> menuIds = (List<Long>) params.get("menuIds");
            // 注意：JSON 数组里的数字默认会被解析为 Integer，这里需要转成 Long
            if (menuIds != null) {
                menuIds = menuIds.stream()
                        .map(o -> Long.valueOf(o.toString()))
                        .toList();
            }
            return sysRoleService.assignMenus(roleId, menuIds);
        } catch (Exception e) {
            return "fail: " + e.getMessage();
        }
    }
}