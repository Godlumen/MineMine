package com.venti.model.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_permission_init")
@Data
public class SysPermissionInit {
    @Id
    private Integer id;
    private String url;
    private String permissionInit;
}
