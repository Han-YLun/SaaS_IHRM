package com.ihrm.atte.dao;

import com.ihrm.domain.atte.entity.LeaveConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeaveConfigDao  extends CrudRepository<LeaveConfig,Long>, JpaRepository<LeaveConfig, Long>, JpaSpecificationExecutor<LeaveConfig> {



    /**
     * @param companyId
     * @param departmentId
     * @return 根据公司和部门查询考请假配置信息
     *
     */
    List<LeaveConfig> findByCompanyIdAndDepartmentId(String companyId, String departmentId);


    /**
     * @return 根据公司、部门和请假类型查询考请假配置信息
     */
    LeaveConfig findByCompanyIdAndDepartmentIdAndLeaveType(String companyId, String departmentId, String leaveType);

}
