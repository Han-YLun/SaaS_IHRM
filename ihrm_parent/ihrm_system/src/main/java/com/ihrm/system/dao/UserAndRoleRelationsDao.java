package com.ihrm.system.dao;

import com.ihrm.domain.system.RoleAndUserRelations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author: hyl
 * @date: 2020/05/25
 **/
public interface UserAndRoleRelationsDao extends JpaRepository<RoleAndUserRelations, String> ,
        JpaSpecificationExecutor<RoleAndUserRelations> {


    /**
     * 根据用户id查询对应的角色id
     * @param userId
     * @return
     */
    List<RoleAndUserRelations> findByUserId(String userId);
}
