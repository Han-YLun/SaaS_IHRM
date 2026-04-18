package com.ihrm.domain.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

//员工合同信息
@Entity
@Table(name = "em_employee_contract")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeContract implements Serializable {
    private static final long serialVersionUID = 5678901234567890123L;
    /**
     * 员工Id
     */
    @Id
    private String userId;
    /**
     * 公司Id
     */
    private String companyId;
    /**
     * 现合同开始时间
     */
    private String currentContractStartTime;
    /**
     * 现合同结束时间
     */
    private String closingTimeOfCurrentContract;
    /**
     * 首次合同开始时间
     */
    private String initialContractStartTime;
    /**
     * 首次合同结束时间
     */
    private String firstContractTerminationTime;
    /**
     * 合同期限
     */
    private String contractPeriod;
    /**
     * 合同文件
     */
    private String contractDocuments;
    /**
     * 续签次数
     */
    private Integer renewalNumber;
    /**
     * 创建时间
     */
    private Date createTime;
}
