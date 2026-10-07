package com.math.alstudymathjava.system.entity.user;

import lombok.Data;

@Data
public class SysRolePerm {
    private Long id;
    private Long roleId;
    private Long permId;
}