package com.ihrm.audit.dao;

import com.ihrm.audit.entity.ProcInstance;
import com.ihrm.audit.entity.ProcTaskInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ProcInstanceDao extends JpaRepository<ProcInstance,String>,
		JpaSpecificationExecutor<ProcInstance> {
}
