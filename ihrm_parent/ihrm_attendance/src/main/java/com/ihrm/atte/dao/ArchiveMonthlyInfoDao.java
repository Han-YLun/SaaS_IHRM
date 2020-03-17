package com.ihrm.atte.dao;

import com.ihrm.domain.atte.entity.ArchiveMonthlyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface ArchiveMonthlyInfoDao extends CrudRepository<ArchiveMonthlyInfo,String>, JpaRepository<ArchiveMonthlyInfo,String>, JpaSpecificationExecutor<ArchiveMonthlyInfo> {


    /**
     * 根据归档列表查询月归档详情
     * @param atteArchiveMonthlyId
     * @return
     */
    List<ArchiveMonthlyInfo> findByAtteArchiveMonthlyId(String atteArchiveMonthlyId);

    /**
     * 根据用户id和年月查询归档明细
     * @param userId	用户id
     * @param yearMonth	年月
     * @return	用户id和对应年月的归档明细
     */
    ArchiveMonthlyInfo findByUserIdAndArchiveDate(String userId, String yearMonth);
}