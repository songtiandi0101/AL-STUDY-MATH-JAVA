package com.math.alstudymathjava.system.entity.user;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SysRole {
    private Long id;
    private String roleCode;
    private String roleName;
    private Integer delFlag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
