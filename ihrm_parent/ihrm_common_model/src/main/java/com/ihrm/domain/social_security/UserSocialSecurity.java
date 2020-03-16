package com.ihrm.domain.social_security;

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
@Table(name = "ss_user_social_security")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSocialSecurity implements Serializable {
    private static final long serialVersionUID = 9039032874868025548L;
    //ID
    @Id
    private String userId;

    /**
     * 本月是否缴纳社保 0为不缴纳 1为缴纳
     */
    private Integer enterprisesPaySocialSecurityThisMonth;
    /**
     * 本月是否缴纳公积金 0为不缴纳 1为缴纳
     */
    private Integer enterprisesPayTheProvidentFundThisMonth;
    /**
     * 参保城市id
     */
    private String participatingInTheCityId;

    /**
     * 参保类型  1为首次开户 2为非首次开户
     */
    private Integer socialSecurityType;

    /**
     * 户籍类型 1为本市城镇 2为本市农村 3为外埠城镇 4为外埠农村
     */
    private Integer householdRegistrationType;

    /**
     * 社保基数
     */
    private BigDecimal socialSecurityBase;

    /**
     * 工伤比例
     */
    private BigDecimal industrialInjuryRatio;

    /**
     * 社保备注
     */
    private String socialSecurityNotes;
    /**
     * 公积金城市id
     */
    private String providentFundCityId;
    /**
     * 公积金基数
     */
    private BigDecimal providentFundBase;
    /**
     * 公积金企业比例
     */
    private BigDecimal enterpriseProportion;
    /**
     * 公积金个人比例
     */
    private BigDecimal personalProportion;
    /**
     * 公积金企业缴纳数额
     */
    private BigDecimal enterpriseProvidentFundPayment;
    /**
     * 公积金个人缴纳数额
     */
    private BigDecimal personalProvidentFundPayment;
    /**
     * 公积金备注
     */
    private String providentFundNotes;

    /**
     * 最后修改时间
     */
    private Date lastModifyTime;
    /**
     * 社保是否缴纳变更时间
     */
    private Date socialSecuritySwitchUpdateTime;
    /**
     * 公积金是否缴纳变更时间
     */
    private Date providentFundSwitchUpdateTime;
    private String householdRegistration;
	private String participatingInTheCity;
	private String providentFundCity;
}
