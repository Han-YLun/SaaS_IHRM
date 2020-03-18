package com.ihrm.salarys.dao;

import com.ihrm.domain.salarys.UserSalaryChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 自定义dao接口继承
 * JpaRepository<实体类，主键>
 * JpaSpecificationExecutor<实体类>
 */
public interface UserSalaryChangeDao extends JpaRepository<UserSalaryChange, String>, JpaSpecificationExecutor<UserSalaryChange> {

}
