package com.ihrm.domain.atte.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class AtteVo implements Serializable {

    /**
     * 公司ID
     */
    private String companyId;

    @NotBlank(message = "用户ID不能为空")
    private String uid;

    @NotBlank(message = "日期不能为空,格式为yyyy-MM-dd")
    private String day;

    @NotBlank(message = "考勤状态不能为空")
    private String stateID;

    @NotBlank(message = "部门ID不能为空")
    private String departmentId;
}
