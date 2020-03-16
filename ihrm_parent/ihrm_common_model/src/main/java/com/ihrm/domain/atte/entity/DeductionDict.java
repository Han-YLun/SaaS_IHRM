package com.ihrm.domain.atte.entity;

import com.ihrm.domain.atte.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atte_deduction_dict")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeductionDict extends BaseEntity implements Serializable {


    @Id
    private String id;
    private String companyId;
    @NotBlank(message = "部门ID不能为空")
    private String departmentId;

    @NotBlank(message = "扣款类型编码不能为空")
    //扣款类型编码
    private String dedTypeCode;

    //时间段下限
    private String periodLowerLimit;

    //时间段上限
    private String periodUpperLimit;

    //次数下限
    private String timesLowerLimit;

    //次数上限
    private String timesUpperLimit;


    /**
     * 旷工次数上限
     */
    private String absenceTimesUpperLimt;


    //扣款金额下限
    private BigDecimal dedAmonutLowerLimit;


    //扣款金额上限
    private BigDecimal dedAmonutUpperLimit;


    //旷工天数
    private String absenceDays;


    //是否旷工0不旷工1旷工
    private String isAbsenteeism;

    //罚款工资倍数
    private String fineSalaryMultiples;

    //是否可用 0开启 1 关闭
    @NotNull(message = "是否启用参数不能为空")
    private Integer isEnable;

}
