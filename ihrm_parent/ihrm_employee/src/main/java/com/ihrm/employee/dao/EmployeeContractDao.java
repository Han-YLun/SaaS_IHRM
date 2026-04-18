package com.ihrm.employee.dao;

import com.ihrm.domain.employee.EmployeeContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 数据访问接口
 */
public interface EmployeeContractDao extends JpaRepository<EmployeeContract, String>, JpaSpecificationExecutor<EmployeeContract> {
    EmployeeContract findByUserId(String userId);

    @Query("select e from EmployeeContract e where e.companyId = :companyId and e.closingTimeOfCurrentContract is not null and e.closingTimeOfCurrentContract >= :startDate and e.closingTimeOfCurrentContract <= :endDate")
    List<EmployeeContract> findExpiringContracts(@Param("companyId") String companyId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
