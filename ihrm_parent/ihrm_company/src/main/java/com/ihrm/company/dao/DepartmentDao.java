package com.ihrm.company.dao;

import com.ihrm.domain.company.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: hyl
 * @date: 2020/01/03
 * 用户dao接口
 **/
public interface DepartmentDao
        extends JpaRepository<Department,String>, JpaSpecificationExecutor<Department> {
    Department findByCodeAndCompanyId(String code, String companyId);
}
