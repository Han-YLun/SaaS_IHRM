package com.ihrm.domain.salarys;

import com.ihrm.domain.poi.ExcelAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "sa_archive_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryArchiveDetail implements Serializable {
    private static final long serialVersionUID = 6021094301665428271L;
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
    @ExcelAttribute(sort = 1)
    private String username;
    /**
     * 手机号
     */
    @ExcelAttribute(sort = 2)
    private String mobile;
    /**
     * 工号
     */
    @ExcelAttribute(sort = 3)
    private String workNumber;
    /**
     * 部门名称
     */
    @ExcelAttribute(sort = 4)
    private String departmentName;
    /**
     * 身份证号
     */
    @ExcelAttribute(sort = 5)
    private String idNumber;
    /**
     * 在职状态
     */
    @ExcelAttribute(sort = 6)
    private String inServiceStatus;
    /**
     * 聘用形式
     */
    @ExcelAttribute(sort = 7)
    private String formOfEmployment;
    /**
     * 银行卡号
     */
    @ExcelAttribute(sort = 49)
    private String bankCardNumber;
    /**
     * 开户行
     */
    @ExcelAttribute(sort = 50)
    private String openingBank;

    //社保相关
    /**
     * 公积金个人
     */
    @ExcelAttribute(sort = 20)
    private BigDecimal providentFundIndividual;
    /**
     * 社保个人
     */
    @ExcelAttribute(sort = 21)
    private BigDecimal socialSecurityIndividual;
    /**
     * 养老个人
     */
    @ExcelAttribute(sort = 22)
    private BigDecimal oldAgeIndividual;
    /**
     * 医疗个人
     */
    @ExcelAttribute(sort = 23)
    private BigDecimal medicalIndividual;
    /**
     * 失业个人
     */
    @ExcelAttribute(sort = 24)
    private BigDecimal unemployedIndividual;
    /**
     * 大病个人
     */
    @ExcelAttribute(sort = 25)
    private BigDecimal aPersonOfGreatDisease;
    /**
     * 社保扣款 个人社保扣款
     */
    @ExcelAttribute(sort = 26)
    private BigDecimal socialSecurity;
    /**
     * 公积金扣款 个人公积金扣款
     */
    @ExcelAttribute(sort = 27)
    private BigDecimal totalProvidentFundIndividual;
    /**
     * 社保企业
     */
    @ExcelAttribute(sort = 33)
    private BigDecimal socialSecurityEnterprise;
    /**
     * 养老企业
     */
    @ExcelAttribute(sort = 34)
    private BigDecimal pensionEnterprise;
    /**
     * 医疗企业
     */
    @ExcelAttribute(sort = 35)
    private BigDecimal medicalEnterprise;
    /**
     * 失业企业
     */
    @ExcelAttribute(sort = 36)
    private BigDecimal unemployedEnterprise;
    /**
     * 工伤企业
     */
    @ExcelAttribute(sort = 37)
    private BigDecimal industrialInjuryEnterprise;
    /**
     * 生育企业
     */
    @ExcelAttribute(sort = 38)
    private BigDecimal childbearingEnterprise;
    /**
     * 大病企业
     */
    @ExcelAttribute(sort = 39)
    private BigDecimal bigDiseaseEnterprise;
    /**
     * 公积金企业
     */
    @ExcelAttribute(sort = 40)
    private BigDecimal providentFundEnterprises;
    /**
     * 公积金社保企业
     */
    @ExcelAttribute(sort = 41)
    private BigDecimal socialSecurityProvidentFundEnterprises;
    /**
     * 公积金需纳税额 ？
     */
    @ExcelAttribute(sort = 16)
    private BigDecimal taxToProvidentFund;

    //考勤相关
    /**
     * 计薪天数
     */
    @ExcelAttribute(sort = 11)
    private BigDecimal officialSalaryDays;
    /**
     * 考勤扣款
     */
    @ExcelAttribute(sort = 15)
    private String attendanceDeductionMonthly;
    /**
     * 计薪标准
     */
    @ExcelAttribute(sort = 12)
    private BigDecimal salaryStandard;

    //薪资相关
    /**
     * 最新工资基数合计 基本工资+岗位工资
     */
    @ExcelAttribute(sort = 8)
    private BigDecimal currentSalaryTotalBase;
    /**
     * 最新基本工资基数
     */
    @ExcelAttribute(sort = 9)
    private BigDecimal currentBaseSalary;
    /**
     * 当月基本工资基数
     */
    @ExcelAttribute(sort = 10)
    private BigDecimal baseSalaryByMonth;
    /**
     * 计税方式
     */
    @ExcelAttribute(sort = 13)
    private String taxCountingMethod;
    /**
     * 当月纳税基本工资 = 当月基本工资基数
     */
    @ExcelAttribute(sort = 14)
    private BigDecimal baseSalaryToTaxByMonth;
    /**
     * 税前工资合计 （当月基本工资+当月岗位工资）
     */
    @ExcelAttribute(sort = 17)
    private BigDecimal salaryBeforeTax;
    /**
     * 工资合计 （基本工资+岗位工资+津贴
     */
    @ExcelAttribute(sort = 18)
    private BigDecimal salary;
    /**
     * 应纳税工资 基本工资 + 岗位工资 + （【开关】津贴）
     */
    @ExcelAttribute(sort = 19)
    private BigDecimal salaryByTax;
    /**
     * 税前实发  基本工资 + 岗位工资 + 津贴 - 五险一金  ？
     */
    @ExcelAttribute(sort = 28)
    private BigDecimal paymentBeforeTax;
    /**
     * 应扣税 （工资 + 【开关】津贴）* 阶梯税率 - 速算扣除数
     */
    @ExcelAttribute(sort = 29)
    private BigDecimal tax;
    /**
     * 税后工资合计 税前工资 - 税
     */
    @ExcelAttribute(sort = 30)
    private BigDecimal salaryAfterTax;
    /**
     * 实发工资  基本工资+岗位工资 + 津贴 - 五险一金 -税
     */
    @ExcelAttribute(sort = 31)
    private BigDecimal payment;
    /**
     * 实发工资备注
     */
    @ExcelAttribute(sort = 32)
    private String paymentRemark;
    /**
     * 薪酬成本  0
     */
    @ExcelAttribute(sort = 42)
    private BigDecimal salaryCost;
    /**
     * 企业人工成本 0
     */
    @ExcelAttribute(sort = 43)
    private BigDecimal enterpriseLaborCost;
    /**
     * 调薪金额 当月的
     */
    @ExcelAttribute(sort = 44)
    private BigDecimal salaryChangeAmount;
    /**
     * 调薪比例
     */
    @ExcelAttribute(sort = 45)
    private BigDecimal salaryChangeScale;
    /**
     * 调薪生效时间
     */
    @ExcelAttribute(sort = 46)
    private String effectiveTimeOfPayAdjustment;
    /**
     * 调薪原因
     */
    @ExcelAttribute(sort = 47)
    private String causeOfSalaryAdjustment;
    /**
     * 注释  --
     */
    @ExcelAttribute(sort = 48)
    private String remark;
    /**
     * 发薪月数   0
     */
    @ExcelAttribute(sort = 51)
    private Integer paymentMonths;

    public BigDecimal getProvidentFundEnterprises() {
    	return this.providentFundEnterprises == null ? new BigDecimal(0) : this.providentFundEnterprises;
    }

	public BigDecimal getSocialSecurityEnterprise() {
		return this.socialSecurityEnterprise == null ? new BigDecimal(0) : this.socialSecurityEnterprise;
	}

	public BigDecimal getSocialSecurityIndividual() {
		return this.socialSecurityIndividual == null ? new BigDecimal(0) : this.socialSecurityIndividual;
	}
	public BigDecimal getProvidentFundIndividual() {
		return this.providentFundIndividual == null ? new BigDecimal(0) : this.providentFundIndividual;
	}

	public BigDecimal getSalaryBeforeTax() {
		return this.salaryBeforeTax == null ? new BigDecimal(0) : this.salaryBeforeTax;
	}

	public BigDecimal getEntTotal() {
    	return getProvidentFundEnterprises().add(getSocialSecurityEnterprise());
	}

	public BigDecimal getPerTotal() {
		return getSocialSecurityIndividual().add(getProvidentFundIndividual());
	}

}
