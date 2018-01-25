package com.venti.model.po;

import com.venti.enums.UserStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 用户登录信息表
 */
@Entity
@Table(name = "user_login")
@Data
@DynamicInsert
@DynamicUpdate
public class UserLogin implements Serializable{
    private static final long serialVersionUID = -1L;
    @Id
    /** 主键 规则：13位时间戳+6位随机数**/
    private String id;
    /** 用户名 **/
    private String userName;
    /** 密码 规则：6-16位**/
    private String password;
    /** 手机号 **/
    private String mobile;
    /** 邮箱号 **/
    private String email;
    /** 微信号 **/
    private String wechatId;
    /** QQ **/
    private String qqId;
    /** 账号状态，“0”未激活、“1”已激活 **/
    private Integer status= UserStatusEnum.INACTIVE.getCode();
    /** 最后登录时间 **/
    private Date lastLoginTime;
    /** 记录最近更新时间 **/
    private Date updateTime;
    /** 记录创建时间 **/
    private Date createTime;

}