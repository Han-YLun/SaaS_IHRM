package com.ihrm.domain.atte.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtteReportMonthlyBO implements Serializable {


    @Id
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 工号
     */
    private String workNumber;
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 一级部门
     */
    private String department;
    private String yearLeaveDays;

    private String leaveDays;
    private String sickLeaveDays;
    private String longSickLeaveDays;

    /**
     * 婚假
     */
    private String marraiageLeaveDays;
    /**
     * 丧假
     */
    private String funeralLeaveDays;
    /**
     * 产假
     */
    private String maternityLeaveDays;

    /**
     * 奖励产假
     */
    private String rewardMaternityLeaveDays;
    /**
     * 陪产假
     */
    private String paternityLeaveDays;
    /**
     * 探亲假
     */
    private String homeLeavaDays;

    /**
     * 工伤假
     */
    private String accidentialLeaveDays;
    /**
     * 调休
     */
    private String dayOffLeaveDays;
    /**
     * 产检假
     */
    private String doctorOffLeaveDays;

    /**
     * 流产假
     */
    private String abortionLeaveDays;
    private String normalDays;
    private String outgoingDays;

    private String onBusinessDays;
    private Integer laterTimes;
    private Integer earlyTimes;


    private String hoursPerWorkDay;
    private String hoursPerRestDay;
    private String clockRate;

    private String absenceDays;
    private Integer isFullAttendance;
    /**
     * 实际出勤天数（非正式）
     */
    private String actualAtteUnofficialDays;

    /**
     * 实际出勤天数（正式）
     */
    private String actualAtteOfficialDays;
    //private Integer workingDays;

    /**
     * 日均时长（自然日）
     */
    private String hoursPerDays;

    /**
     * 计薪标准
     */
    private String salaryStandards;

    /**
     * 计薪天数调整
     */
    private String salaryAdjustmentDays;

    /**
     * 工作时长
     */
    private String workHour;

    /*报表补充*/
    /*在职状态 1.在职  2.离职*/
    private Integer inServiceStatus;
    /*聘用形式*/
    private Integer formOfEmployment;

    /*证件类型*/
    private String credentialsType;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 最新工资基数合计
     */
    private String  salaryBaseTotalLately;

    /**
     * 最新基本工资基数
     */
    private String salaryBaseLately;

    /**
     * 当月基本公司基数
     */
    private String salaryBaseMonthly;

    /**
     * 计薪天数
     */
    private String salaryDays;

    /**
     * 计税方式
     */
    private String taxWay;


    /**
     * 当月纳税基本工资
     */
    private String taxBaseSalaryMonthly;


    /**
     * 当月免税基本工资
     */
    private String freeTaxBaseSalaryMonthly;

    /**
     * 考勤扣款
     */
    private BigDecimal atteCutPayment;

    /**
     * 公积金需纳税额
     */

    private BigDecimal accumulationFundTax;

    /**
     * 长期病假工资
     */
    private  BigDecimal longSickSalary;

    /**
     * 补签次数
     */
    private Integer signedTimes;

    /**
     * 应出勤工作日
     */
    private  String standardWorkDays;


    /**
     * 计薪天数（非正式）
     */
    private String salaryUnofficialDays;

    /**
     * 计薪天数（正式）
     */
    private String salaryOfficialDays;


    /**
     * 是否归档(0已经归档1没有归档)
     */
    //private Integer isArchived;


    /**
     * 考勤方案
     */
    private String atteSolution;

    /**
     * 工作城市
     */
    private String workCity;


}
