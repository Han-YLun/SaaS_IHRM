package com.ihrm.domain.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


//调岗申请实体类
@Entity
@Table(name = "em_transferposition")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTransferPosition implements Serializable {
    private static final long serialVersionUID = 4482354983818596821L;
    /**
     * 员工ID
     */
    @Id
    private String userId;
    /**
     * 岗位
     */
    private String post;
    /**
     * 职级
     */
    private String rank;
    /**
     * 汇报对象
     */
    private String reportingObject;
    /**
     * HRBP
     */
    private String hrbp;
    /**
     * 调岗时间
     */
    private Date adjustmentTime;
    /**
     * 调岗原因
     */
    private String causeOfAdjustingPost;
    /**
     * 附件 [1,2,3]
     */
    private String enclosure;
    /**
     * 管理形式
     */
    private String formOfManagement;
    /**
     * 工作城市
     */
    private String workingCity;
    /**
     * 纳税城市
     */
    private String taxableCity;
    /**
     * 现合同开始时间
     */
    private String currentContractStartTime;
    /**
     * 现合同结束时间
     */
    private String closingTimeOfCurrentContract;
    /**
     * 工作地点
     */
    private String workingPlace;
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
     * 续签次数
     */
    private Integer renewalNumber;
    /**
     * 推荐企业人
     */
    private String recommenderBusinessPeople;
    /**
     * 单据状态 1是未执行，2是已执行
     */
    private Integer estatus;
    /**
     * 创建时间
     */
    private Date createTime;
}
