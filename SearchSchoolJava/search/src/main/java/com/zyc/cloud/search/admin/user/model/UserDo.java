package com.zyc.cloud.search.admin.user.model;

import com.zyc.cloud.search.utils.base.BaseDo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:17
 */
@Data
@Table(name = "tb_user")
public class UserDo extends BaseDo {

    /*用户id*/
    @Id
    @Column(name = "user_id")
    private String userId;

    /*用户账号*/
    @Column(name = "account")
    private String account;

    /*用户密码*/
    @Column(name="password")
    private String password;

    /*用户姓名*/
    @Column(name="user_name")
    private String userName;

    /*用户头像地址*/
    @Column(name="img_src")
    private String imgSrc;

    /*用户性别 */
    @Column(name="sex")
    private Integer sex;

    /*用户年龄 */
    @Column(name="age")
    private Integer age;

    /*密码盐值*/
    @Column(name="salt_value")
    private String saltValue;

    /*角色 admin管理员 user普通用户*/
    @Column(name="role")
    private String role;

    /*角色 admin管理员 user普通用户*/
    @Column(name="state")
    private Integer state;
}
