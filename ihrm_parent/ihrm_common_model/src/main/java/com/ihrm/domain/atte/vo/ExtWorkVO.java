package com.ihrm.domain.atte.vo;

import com.ihrm.domain.atte.entity.DayOffConfig;
import com.ihrm.domain.atte.entity.ExtraDutyConfig;
import com.ihrm.domain.atte.entity.ExtraDutyRule;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ExtWorkVO implements Serializable{

    /**
     * 加班配置
     */
    private ExtraDutyConfig extraDutyConfig;

    /**
     * 加班规则
     */
    private List<ExtraDutyRule> extraDutyRuleList;

    /**
     * 调休配置
     */
    private DayOffConfig dayOffConfigs;


}
