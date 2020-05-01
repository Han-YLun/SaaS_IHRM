package com.ihrm.system.dao;



import com.ihrm.domain.system.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
  * 权限数据访问接口
  */
public interface PermissionDao extends JpaRepository<Permission, String>, JpaSpecificationExecutor<Permission> {

    List<Permission> findByTypeAndPid(int type,String pid);

    List<Permission> findByTypeAndEnVisible(int type, String enVisible);
}