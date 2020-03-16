package com.ihrm.domain.atte.bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 考勤统计结果
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtteStatisBO implements Serializable {

    @Id
    private String id;


    private String day;


    private Integer adtStatu;

}
