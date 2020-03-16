package com.ihrm.domain.atte.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class ReportVO implements Serializable {


    private String companyId;

    @NotBlank(message = "部门ID不能为空")
    private String departmentId;

    @NotBlank(message = "考勤日期不能为空精确到月，格式'%Y-%m'")
    private String atteDate;

    private String userId;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页条数
     */
    private  Integer pageSize;
}
