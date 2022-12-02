package com.ihrm.atte.dao;

import com.ihrm.domain.system.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author arvinyl
 */
@Repository("com.ihrm.atte")
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    /**
     * 根据手机号码查询用户信息
     *
     * @param mobile 手机号码
     * @return 手机号码对应的用户信息
     */
    User findByMobile(String mobile);

    /**
     * 查询企业id下面的所有用户信息
     *
     * @param companyId 企业id
     * @return 企业id下面的所有用户信息
     */
    List<User> findByCompanyId(String companyId);

    /**
     * 分页查询企业id下面的用户信息
     *
     * @param companyId 企业id
     * @param pageable  分页信息
     * @return 分页的用户信息
     */
    @Query(value = "select * from bs_user where company_id = ?1", nativeQuery = true)
    Page<User> findPage(String companyId, Pageable pageable);
}
