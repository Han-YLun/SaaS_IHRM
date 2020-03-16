package com.ihrm.domain.atte.vo;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AtteSalaryStatisticsVO implements Serializable {


    /**
     * 用户ID
     */
    @NotBlank(message = "用户ID不能为空")
    private String userId;


    /**
     * 每次扣款薪资
     */
    @NotNull(message = "每次扣款薪资不能为空")
    private String dedSalaryPerTimes;


    /**
     * 考勤日期
     */
    @NotBlank(message = "考勤日期不能为空，格式yyyy-mm")
    private String atteDate;

    /**
     * 部门ID
     */
    @NotBlank(message = "部门ID不能为空")
    private String departmentId;


    /**
     * 公司ID
     */
    private String companyId;


}
