package com.venti.dao.repository;

import com.venti.model.po.UserLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 注册Dao
 */
public interface RegisterRepository extends BaseRepository<UserLogin, String> {
    /**
     * 分页查找所有记录
     * @param pageable
     * @return
     */
    Page<UserLogin> findAll(Pageable pageable);

    /**
     * 根据手机号查找
     * @param mobile
     * @return
     */
    UserLogin findByMobile(String mobile);

    /**
     * 添加用户记录
     * @param userLogin
     * @return
     */
    UserLogin save(UserLogin userLogin);

}
