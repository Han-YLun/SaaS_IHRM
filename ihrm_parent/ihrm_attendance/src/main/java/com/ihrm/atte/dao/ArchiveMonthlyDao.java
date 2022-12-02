package com.ihrm.atte.dao;

import com.ihrm.domain.atte.entity.ArchiveMonthly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


/**
 * @author arvinyl
 */
public interface ArchiveMonthlyDao extends PagingAndSortingRepository<ArchiveMonthly,String>, CrudRepository<ArchiveMonthly,String>, JpaRepository<ArchiveMonthly,String>, JpaSpecificationExecutor<ArchiveMonthly> {

    /**
     * 查询某一年的归档列表
     * @param companyId 企业ID
     * @param archiveYear   年份
     * @return  对应企业id和年份的归档列表
     */
    List<ArchiveMonthly> findByCompanyIdAndArchiveYear(String companyId, String archiveYear);
}
