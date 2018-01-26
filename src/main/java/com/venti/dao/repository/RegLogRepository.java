package com.venti.dao.repository;

import com.venti.model.po.UserLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 注册Dao
 */
public interface RegLogRepository extends BaseRepository<UserLogin, String> {

    /**
     * 分页查找所有记录
     **/
    Page<UserLogin> findAll(Pageable pageable);

    /**
     * 根据手机号查找
     **/
    UserLogin findByMobile(String mobile);

    /**
     * 根据邮箱查找
     */
    UserLogin findByEmail(String mail);

    /**
     * 根据主键查找
     **/
    UserLogin findById(String id);

    /**
     * 添加用户
     **/
    UserLogin save(UserLogin userLogin);

    /**
     * 根据手机号或邮箱号登录
     **/
    UserLogin findByMobileOrEmail(String mobile, String email);

}
