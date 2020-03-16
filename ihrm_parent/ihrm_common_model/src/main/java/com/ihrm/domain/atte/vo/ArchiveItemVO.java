package com.ihrm.domain.atte.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class ArchiveItemVO implements Serializable {


    private String companyId;


    @NotNull(message = "部门ID不能为空")
    private String departmentId;


    /**
     * 年份
     */
    @NotNull(message = "归档年月不能为空，格式是%Y-%m")
    private String archiveDate;





}
