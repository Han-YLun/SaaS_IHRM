package com.ihrm.domain.atte.vo;


import com.ihrm.domain.atte.entity.DayOffConfig;
import com.ihrm.domain.atte.entity.ExtraDutyRule;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 加班配置VO
 * @author jingpengfei
 */
@Data
public class ExtDutyVO implements Serializable {


    private String companyId;

    @NotBlank(message = "部门ID不能为空")
    private String departmentId;

    /**
     * 每日标准工作时长，单位小时
     */
    private String workHoursDay;

    /**
     * 是否打卡0开启1关闭
     */
    @NotNull(message = "是否打卡不能为空")
    private Integer isClock;

    /**
     * 是否开启加班补偿0开启1关闭
     */
    @NotNull(message = "是否开启加班补偿不能为空0开启1关闭")
    private String isCompensationint;

    /**
     * 调休最后有效日期
     */
    private String latestEffectDate;
    /**
     * 调休单位
     */
    @NotBlank(message = "调休单位不能为空(天最小0.5)")
    private String unit;

    /**
     * 配置规则
     */
    List<ExtraDutyRule> rules;

    /**
     * 调休配置
     */
    List<DayOffConfig> dayOffConfigList;

}
