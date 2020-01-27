package com.ihrm.system.dao;

import com.ihrm.domain.system.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: hyl
 * @date: 2020/01/09
 **/
public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

    User findByMobile(String mobile);
}
