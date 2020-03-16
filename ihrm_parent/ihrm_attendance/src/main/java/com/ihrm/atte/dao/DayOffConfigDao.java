package com.ihrm.atte.dao;

import com.ihrm.domain.atte.entity.DayOffConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface DayOffConfigDao extends CrudRepository<DayOffConfig,Long>, JpaRepository<DayOffConfig, Long>, JpaSpecificationExecutor<DayOffConfig> {



    /**
     * @param companyId
     * @param departmentId
     * @return 根据公司和部门查询扣款配置信息
     *
     */
    DayOffConfig findByCompanyIdAndDepartmentId(String companyId, String departmentId);
}
