package com.ihrm.domain.atte.resp;

import com.ihrm.domain.atte.bo.AtteTotalsBO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {

    //当前记录数
    private Integer counts;
    private Integer pageSize;
    //总页数
    private Integer pages;

    //当前页码
    private Integer page;
    private AtteTotalsBO totals;
    private List<T> items;


    /**
     * 是否归档过
     */
    private Integer isArchived;

}