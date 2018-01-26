package com.venti.model.po;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sys_role")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class SysRole implements Serializable{
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SysPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<SysPermission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<UserLogin> getUserLoginList() {
        return userLoginList;
    }

    public void setUserLoginList(List<UserLogin> userLoginList) {
        this.userLoginList = userLoginList;
    }
}
