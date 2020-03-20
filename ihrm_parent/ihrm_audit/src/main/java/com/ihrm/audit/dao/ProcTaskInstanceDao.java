package com.ihrm.audit.dao;

import com.ihrm.audit.entity.ProcTaskInstance;
import com.ihrm.domain.atte.entity.ArchiveMonthly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface ProcTaskInstanceDao extends JpaRepository<ProcTaskInstance,String>, JpaSpecificationExecutor<ProcTaskInstance> {


	ProcTaskInstance findByProcessIdAndTaskKey(String processId, String taskKey) ;

	List<ProcTaskInstance> findByProcessId(String processId);
}
