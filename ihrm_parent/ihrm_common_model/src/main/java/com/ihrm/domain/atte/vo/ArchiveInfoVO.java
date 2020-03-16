package com.ihrm.domain.atte.vo;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class ArchiveInfoVO implements Serializable {



    private String companyId;

    private String departmentId;

    @NotBlank(message = "月归档ID不能为空")
    private String  atteArchiveMonthlyId;

    /**
     * 月份
     */
    private String month;



}
