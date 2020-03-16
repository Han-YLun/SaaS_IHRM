package com.ihrm.domain.atte.bo;

import lombok.Data;

@Data
public class AtteTotalsBO {

    //待处理审批数量
    private Integer tobeTaskCount;

    //当前报表月份
    private Integer monthOfReport;


}
