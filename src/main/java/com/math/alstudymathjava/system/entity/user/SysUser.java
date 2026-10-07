package com.math.alstudymathjava.system.entity.user;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SysUser {
    /**
     * 用户主键ID
     */
    private Long id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 用户状态：0禁用，1启用
     */
    private Integer status;

    /**
     * 删除标记：0未删除，1已删除（逻辑删除）
     */
    private Integer delFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    // ========== 新增分页参数（MyBatis分页用，数据库无对应字段）==========
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
