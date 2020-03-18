package com.ihrm.salarys.dao;

import com.ihrm.domain.salarys.SalaryArchiveDetail;
import com.ihrm.domain.social_security.ArchiveDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 自定义dao接口继承
 * JpaRepository<实体类，主键>
 * JpaSpecificationExecutor<实体类>
 */
public interface ArchiveDetailDao extends JpaRepository<SalaryArchiveDetail, String>, JpaSpecificationExecutor<SalaryArchiveDetail> {

    List<SalaryArchiveDetail> findByArchiveId(String archiveId);
}
