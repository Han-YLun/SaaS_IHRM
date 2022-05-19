package com.ihrm.domain.social_security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ss_company_settings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialsecurityCompanySettings implements Serializable {
    private static final long serialVersionUID = 5753095342370704726L;
    /**
     * 企业id
     */
    @Id
    private String companyId;
    /**
     * 是否设置 0为未设置，1为已设置
     */
    private Integer isSettings;
    /**
     * 当前显示报表月份
     */
    private String dataMonth;
}
