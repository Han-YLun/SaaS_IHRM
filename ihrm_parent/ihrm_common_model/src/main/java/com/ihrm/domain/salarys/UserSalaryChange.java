package com.ihrm.domain.salarys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "sa_user_salary_change")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSalaryChange implements Serializable {
    private static final long serialVersionUID = -6458210016597635655L;
    @Id
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 当前基本工资
     */
    private BigDecimal currentBasicSalary;
    /**
     * 当前岗位工资
     */
    private BigDecimal currentPostWage;
    /**
     * 调整基本工资
     */
    private BigDecimal adjustmentOfBasicWages;
    /**
     * 调整岗位工资
     */
    private BigDecimal adjustPostWages;
    /**
     * 调整生效时间
     */
    private Date effectiveTimeOfPayAdjustment;
    /**
     * 调整原因
     */
    private String causeOfSalaryAdjustment;
    /**
     * 附件
     */
    private String enclosure;
}
