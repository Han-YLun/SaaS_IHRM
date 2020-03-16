package com.ihrm.domain.atte.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ArchiveVO {


    private String companyId;


    @NotNull(message = "部门ID不能为空")
    private String departmentId;


    /**
     * 年份
     */
    @NotNull(message = "年份不能为空")
    private String year;




}
