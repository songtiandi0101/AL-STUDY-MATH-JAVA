package com.math.alstudymathjava.system.entity.user;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SysMenu {
    private Long id;
    private Long parentId;
    private String menuName;
    private String path;
    private String icon;
    private Integer sort;
    private Integer visible;
    private Integer type; //1目录 2页面
    private Integer delFlag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 树形结构子菜单，数据库不存在，仅接口返回使用
    private List<SysMenu> children;
}