package com.venti.model.po;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sys_role")
@Data
public class SysRole {
    @Id
    private Integer id;
    /** 角色名 如：admin**/
    private String role;
    /** 角色描述 如：管理员 **/
    private String description;
    /** 角色状态 “0”不可用，“1”可用 **/
    private Integer status;
    /** 一个角色对应多个权限 **/
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="SysRolePermission",joinColumns={@JoinColumn(name="rid")},inverseJoinColumns={@JoinColumn(name="pid")})
    private List<SysPermission> permissionList;
    /** 一个角色对应多个用户 **/
    @ManyToMany
    @JoinTable(name="SysUserRole",joinColumns={@JoinColumn(name="rid")},inverseJoinColumns={@JoinColumn(name="uid")})
    private List<UserLogin> userLoginList;
}
