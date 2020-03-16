package com.ihrm.atte.dao;

import com.ihrm.domain.system.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

    User findByMobile(String mobile);

    List<User> findByCompanyId(String companyId);
}
