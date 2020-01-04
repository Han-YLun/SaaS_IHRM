package com.ihrm.domain.company.response;

import com.ihrm.domain.company.Company;
import com.ihrm.domain.company.Department;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author: hyl
 * @date: 2020/01/04
 **/
@Getter
@Setter
@NoArgsConstructor
public class DeptListResult {

    private String companyId;
    private String companyName;
    private String companyManage;
    private List<Department> depts;

    public DeptListResult(Company company,List<Department> depts){
        this.companyId = company.getId();
        this.companyName = company.getName();
        this.companyManage = company.getLegalRepresentative();//公司联系人
        this.depts = depts;
    }
}
