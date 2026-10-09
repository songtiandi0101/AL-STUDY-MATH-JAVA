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
    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 每页显示条数
     */
    private Integer pageSize;

    /**
     * limit起始偏移量，(pageNum-1)*pageSize，数据库无此字段
     */
    private Integer start;

}
