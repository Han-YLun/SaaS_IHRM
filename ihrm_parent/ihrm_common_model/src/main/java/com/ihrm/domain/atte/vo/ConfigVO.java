package com.ihrm.domain.atte.vo;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class ConfigVO  implements Serializable {


    /**
     * 公司ID
     */
    private String companyId;


    /**
     * 部门ID
     */
    @NotBlank(message = "部门ID不能为空")
    private String departmentId;
}
