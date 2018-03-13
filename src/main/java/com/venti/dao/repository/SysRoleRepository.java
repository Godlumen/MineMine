package com.venti.dao.repository;

import com.venti.model.po.SysRole;

public interface SysRoleRepository extends BaseRepository<SysRole, Integer> {

    SysRole findById(Integer id);
}
