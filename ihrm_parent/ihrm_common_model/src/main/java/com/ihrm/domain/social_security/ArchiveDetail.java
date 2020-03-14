package com.ihrm.domain.social_security;

import com.ihrm.domain.employee.UserCompanyPersonal;
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
@Table(name = "ss_archive_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveDetail implements Serializable {
    private static final long serialVersionUID = -5571547188954376291L;

	public ArchiveDetail(String userId, String mobile, String username, String departmentName) {
		this.userId = userId;
		this.mobile = mobile;
		this.username = username;
		this.firstLevelDepartment = departmentName;
	}

	public void setUserSocialSecurity(UserSocialSecurity userSocialSecurity) {
	    this.householdRegistrationType = userSocialSecurity.getHouseholdRegistration();
	    this.participatingInTheCity = userSocialSecurity.getParticipatingInTheCity();
	    this.providentFundCity = userSocialSecurity.getProvidentFundCity();
	    this.socialSecurityNotes = userSocialSecurity.getSocialSecurityNotes();
	    this.providentFundNotes = userSocialSecurity.getProvidentFundNotes();
	    this.socialSecurityBase = userSocialSecurity.getSocialSecurityBase();
	    this.providentFundBase = userSocialSecurity.getProvidentFundBase();
	    this.accumulationFundEnterpriseBase = userSocialSecurity.getProvidentFundBase();
	    this.proportionOfProvidentFundEnterprises = userSocialSecurity.getEnterpriseProportion();
	    this.personalRatioOfProvidentFund = userSocialSecurity.getPersonalProportion();
	    this.individualBaseOfProvidentFund = userSocialSecurity.getProvidentFundBase();
	    this.providentFundEnterprises = userSocialSecurity.getEnterpriseProvidentFundPayment();
	    this.providentFundIndividual = userSocialSecurity.getPersonalProvidentFundPayment();
	    this.totalProvidentFund = this.providentFundEnterprises.add(this.providentFundIndividual);
    }

    public void setUserCompanyPersonal(UserCompanyPersonal userCompanyPersonal) {
	    this.idNumber = userCompanyPersonal.getIdNumber();
	    this.theHighestDegreeOfEducation = userCompanyPersonal.getTheHighestDegreeOfEducation();
	    this.openingBank = userCompanyPersonal.getOpeningBank();
	    this.bankCardNumber = userCompanyPersonal.getBankCardNumber();
	    this.socialSecurityComputerNumber = userCompanyPersonal.getSocialSecurityComputerNumber();
	    this.providentFundAccount = userCompanyPersonal.getProvidentFundAccount();
    }

    /**
     * id
     */
    @Id
    private String id;
    /**
     * 归档id
     */
    private String archiveId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 姓名
     */
    private String username;
    /**
     * 入职时间
     */
    private Date timeOfEntry;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 学历
     */
    private String theHighestDegreeOfEducation;
    /**
     * 开户行
     */
    private String openingBank;
    /**
     * 银行卡号
     */
    private String bankCardNumber;
    /**
     * 一级部门
     */
    private String firstLevelDepartment;
    /**
     * 二级部门
     */
    private String twoLevelDepartment;
    /**
     * 工作城市
     */
    private String workingCity;
    /**
     * 社保电脑号
     */
    private String socialSecurityComputerNumber;
    /**
     * 公积金账号
     */
    private String providentFundAccount;
    /**
     * 离职时间
     */
    private String leaveDate;
    /**
     * 户籍类型
     */
    private String householdRegistrationType;
    /**
     * 参保城市
     */
    private String participatingInTheCity;
    /**
     * 社保月份
     */
    private String socialSecurityMonth;
    /**
     * 社保基数
     */
    private BigDecimal socialSecurityBase;
    /**
     * 社保合计
     */
    private BigDecimal socialSecurity;
    /**
     * 社保企业
     */
    private BigDecimal socialSecurityEnterprise;
    /**
     * 社保个人
     */
    private BigDecimal socialSecurityIndividual;
    /**
     * 公积金城市
     */
    private String providentFundCity;
    /**
     * 公积金月份
     */
    private String providentFundMonth;
    /**
     * 公积金基数
     */
    private BigDecimal providentFundBase;
    /**
     * 公积金企业基数
     */
    private BigDecimal accumulationFundEnterpriseBase;
    /**
     * 公积金企业比例
     */
    private BigDecimal proportionOfProvidentFundEnterprises;
    /**
     * 公积金个人基数
     */
    private BigDecimal individualBaseOfProvidentFund;
    /**
     * 公积金个人比例
     */
    private BigDecimal personalRatioOfProvidentFund;
    /**
     * 公积金合计
     */
    private BigDecimal totalProvidentFund;
    /**
     * 公积金企业
     */
    private BigDecimal providentFundEnterprises;
    /**
     * 公积金个人
     */
    private BigDecimal providentFundIndividual;
    /**
     * 养老企业基数
     */
    private BigDecimal pensionEnterpriseBase;
    /**
     * 养老企业比例
     */
    private BigDecimal proportionOfPensionEnterprises;
    /**
     * 养老企业
     */
    private BigDecimal pensionEnterprise;
    /**
     * 养老个人基数
     */
    private BigDecimal personalPensionBase;
    /**
     * 养老个人比例
     */
    private BigDecimal personalPensionRatio;
    /**
     * 养老个人
     */
    private BigDecimal oldAgeIndividual;
    /**
     * 失业企业基数
     */
    private BigDecimal unemploymentEnterpriseBase;
    /**
     * 失业企业比例
     */
    private BigDecimal proportionOfUnemployedEnterprises;
    /**
     * 失业企业
     */
    private BigDecimal unemployedEnterprise;
    /**
     * 失业个人基数
     */
    private BigDecimal theNumberOfUnemployedIndividuals;
    /**
     * 失业个人比例
     */
    private BigDecimal percentageOfUnemployedIndividuals;
    /**
     * 失业个人
     */
    private BigDecimal unemployedIndividual;
    /**
     * 医疗企业基数
     */
    private BigDecimal medicalEnterpriseBase;
    /**
     * 医疗企业比例
     */
    private BigDecimal proportionOfMedicalEnterprises;
    /**
     * 医疗企业
     */
    private BigDecimal medicalEnterprise;
    /**
     * 医疗个人基数
     */
    private BigDecimal medicalPersonalBase;
    /**
     * 医疗个人比例
     */
    private BigDecimal medicalPersonalRatio;
    /**
     * 医疗个人
     */
    private BigDecimal medicalIndividual;
    /**
     * 工伤企业基数
     */
    private BigDecimal baseOfIndustrialInjuryEnterprises;
    /**
     * 工伤企业比例
     */
    private BigDecimal proportionOfIndustrialInjuryEnterprises;
    /**
     * 工伤企业
     */
    private BigDecimal industrialInjuryEnterprise;
    /**
     * 生育企业基数
     */
    private BigDecimal fertilityEnterpriseBase;
    /**
     * 生育企业比例
     */
    private BigDecimal proportionOfFertilityEnterprises;
    /**
     * 生育企业
     */
    private BigDecimal childbearingEnterprise;
    /**
     * 大病企业基数
     */
    private BigDecimal baseOfSeriousIllness;
    /**
     * 大病企业比例
     */
    private BigDecimal proportionOfSeriouslyIllEnterprises;
    /**
     * 大病企业
     */
    private BigDecimal bigDiseaseEnterprise;
    /**
     * 大病个人基数
     */
    private BigDecimal personalBaseOfSeriousIllness;
    /**
     * 大病个人比例
     */
    private BigDecimal personalProportionOfSeriousIllness;
    /**
     * 大病个人
     */
    private BigDecimal aPersonOfGreatDisease;
    /**
     * 公积金备注
     */
    private String providentFundNotes;
    /**
     * 社保备注
     */
    private String socialSecurityNotes;

    private String yearsMonth;
}
