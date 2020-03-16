package com.ihrm.domain.atte.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class ReportItemVO implements Serializable {


    /**
     * 编号
     */
    String id                                     ;
    /**
     * 姓名
     */
    String fullName ;
    /**
     * 工号
     */
    String workNumber ;

    /**
     * 手机号
     */
    String cellPhoneNumber ;
    /**
     * 当月考勤方案
     */
    String attendancePlanForTheMonth      ;
    /**
     * 一级部门
     */
    String firstLevelDepartment ;

    /**
     * 二级部门
     */
    String twoLevelDepartment ;
    /**
     * 三级部门
     */
    String threeLevelDepartment ;
    /**
     * 工作城市
     */
    String workingCity ;

    /**
     * 事假
     */
    String compassionateLeave ;
    /**
     * 调休
     */
    String breakDown ;
    /**
     *调休
     */
    String normal ;

    /**
     * 迟到
     */
    Integer numberOfLateness ;
    /**
     * 早退次数
     */
    Integer theNumberOfEarlyRetreat ;
    /**
     * 日均时长自然日
     */
    String averageDailyNaturalDays ;

    /**
     * 日均时长工作日
     */
    String averageDailyWorkingDay ;
    /**
     * 日均时长休息日
     */
    String averageDailyRestDays ;
    /**
     * 日均时长休息日
     */
    String percentageOfPunchingRate ;

    /**
     * 旷工天数
     */
    String absenteeismDays ;
    /**
     * 是否全勤
     */
    Boolean whetherItIsFullOfWork ;
    /**
     * 实际出勤天数非正式
     */
    String actualAttendanceDays ;

    /**
     * 实际出勤天数正式
     */
    String actualAttendanceDaysAreOfficial ;
    /**
     * 应出勤工作日
     */
    String attendanceDay ;
    /**
     * 计薪标准
     */
    String salaryStandard ;

    /**
     * 计薪天数调整
     */
    String adjustmentOfSalaryDays ;
    /**
     * 工作时长
     */
    String longWorkingTime ;
    /**
     * 计薪天数非正式
     */
    String informalSalaryDays ;

    /**
     * 计薪天数正式
     */
    String officialSalaryDays ;


}
