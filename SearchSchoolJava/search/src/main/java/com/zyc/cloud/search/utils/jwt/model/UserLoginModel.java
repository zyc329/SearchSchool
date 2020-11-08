package com.zyc.cloud.search.utils.jwt.model;

import com.zyc.cloud.search.utils.base.BaseDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
@EqualsAndHashCode(callSuper = true)
@Data
public class UserLoginModel extends BaseDo {

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

    /*角色 admin管理员 user普通用户*/
    @Column(name="role")
    private String role;

    @Column(name="state")
    private Integer state;
}
