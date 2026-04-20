package com.ihrm.atte.dao;

import com.ihrm.domain.atte.entity.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveApplicationDao extends CrudRepository<LeaveApplication, String>,
        JpaRepository<LeaveApplication, String>, JpaSpecificationExecutor<LeaveApplication> {

    List<LeaveApplication> findByCompanyId(String companyId);

    List<LeaveApplication> findByUserId(String userId);

    List<LeaveApplication> findByUserIdAndApprovalStatus(String userId, String approvalStatus);

    @Query(nativeQuery = true,
            value = "SELECT COALESCE(SUM(leave_days), 0) FROM atte_leave_application " +
                    "WHERE user_id = ?1 AND approval_status = '2' " +
                    "AND start_time >= ?2 AND end_time <= ?3")
    Double sumApprovedLeaveDaysByUserIdAndDateRange(String userId, String startDate, String endDate);

    @Query(nativeQuery = true,
            value = "SELECT COALESCE(SUM(leave_days), 0) FROM atte_leave_application " +
                    "WHERE user_id = ?1 AND approval_status = '2' " +
                    "AND leave_type = ?2 " +
                    "AND start_time >= ?3 AND end_time <= ?4")
    Double sumApprovedLeaveDaysByUserIdAndTypeAndDateRange(String userId, String leaveType, String startDate, String endDate);
}
