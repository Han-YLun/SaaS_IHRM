package com.ihrm.domain.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

//岗位信息
@Entity
@Table(name = "em_user_company_jobs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCompanyJobs implements Serializable {
    private static final long serialVersionUID = 6934846324503387883L;
    /**
     * 员工ID
     */
    @Id
    private String userId;
    /**
     * 公司ID
     */
    private String companyId;
    /**
     * 岗位
     */
    private String post;
    /**
     * 工作邮箱
     */
    private String workMailbox;
    /**
     * 职级
     */
    private String rank;
    /**
     * 转正评价
     */
    private String correctionEvaluation;
    /**
     * 汇报对象
     */
    private String reportId;

    private String reportName;
    /**
     * 转正状态
     */
    private String stateOfCorrection;
    /**
     * HRBP
     */
    private String hrbp;
    /**
     * 首次参加工作时间
     */
    private String workingTimeForTheFirstTime;
    /**
     * 调整司龄天
     */
    private Integer adjustmentAgedays;
    /**
     * 调整工龄天
     */
    private Integer adjustmentOfLengthOfService;
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
     * 其他招聘渠道
     */
    private String otherRecruitmentChannels;
    /**
     * 招聘渠道
     */
    private String recruitmentChannels;
    /**
     * 社招校招
     */
    private String socialRecruitment;
    /**
     * 推荐企业人
     */
    private String recommenderBusinessPeople;
}
