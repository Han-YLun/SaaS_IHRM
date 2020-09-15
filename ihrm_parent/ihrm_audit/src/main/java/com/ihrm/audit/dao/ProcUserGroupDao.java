package com.ihrm.audit.dao;

import com.ihrm.audit.entity.ProcUserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author Administrator
 */
public interface ProcUserGroupDao extends JpaRepository<ProcUserGroup,String>,
		JpaSpecificationExecutor<ProcUserGroup> {
}
