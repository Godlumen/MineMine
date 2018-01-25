package com.venti.dao.repository;

import com.venti.model.po.SysPermissionInit;

import java.util.List;

public interface SysPermissionInitRepository extends BaseRepository<SysPermissionInit,Integer> {
    List<SysPermissionInit> findAll();
}
