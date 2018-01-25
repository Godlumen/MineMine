package com.venti.model.po;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sys_permission")
@Data
public class SysPermission {
    @Id
    private Integer id;
    /** 权限名 **/
    private String name;
    /** 请求url地址 **/
    private String url;
    /** 权限字符串 **/
    private String permission;
    /** 权限状态 “0”不可用，“1”可用 **/
    private Integer status;
    /** 一个权限对应多个角色 **/
    @ManyToMany
    @JoinTable(name="SysRolePermission",joinColumns={@JoinColumn(name="pid")},inverseJoinColumns={@JoinColumn(name="rid")})
    private List<SysRole> roleList;

}
