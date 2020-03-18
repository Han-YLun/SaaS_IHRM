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
@Table(name = "sa_archive")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryArchive implements Serializable {
    private static final long serialVersionUID = -8362752557188295516L;
    @Id
    private String id;
    /**
     * 企业id
     */
    private String companyId;
    /**
     * 年月
     */
    private String yearsMonth;
    /**
     * 创建时间
     */
    private Date creationTime;
    /**
     * 人工成本
     */
    private BigDecimal artificialCost;
    /**
     * 税前工资
     */
    private BigDecimal grossSalary;
    /**
     * 五险一金
     */
    private BigDecimal fiveInsurances;
}
