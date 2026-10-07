package com.math.alstudymathjava.system.entity.user;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SysPermission {
    private Long id;
    private String permCode;
    private String permName;
    private Integer delFlag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}