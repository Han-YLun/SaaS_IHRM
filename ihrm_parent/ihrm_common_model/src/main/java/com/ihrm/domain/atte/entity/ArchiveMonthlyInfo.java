package com.ihrm.domain.atte.entity;


import com.ihrm.domain.atte.base.BaseEntity;
import com.ihrm.domain.system.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Map;

/**
 * @author itcast
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "atte_archive_monthly_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveMonthlyInfo extends BaseEntity implements Serializable {

  @Id
  private String id;

  private String userId;
  private String atteArchiveMonthlyId;
  private String name;

  private String workNumber;
  private String mobile;
  private String atteSolution;

  private String department;
  private String workCity;
  private String yearLeaveDays;

  private String leaveDays;
  private String sickLeaveDays;
  private String longSickLeaveDays;

  private String marraiageLeaveDays;
  private String funeralLeaveDays;
  /**
   * 产假
   */
  private String maternityLeaveDays;

  private String rewardMaternityLeaveDays;

  /**
   * 陪产假
   */
  private String paternityLeaveDays;
  /**
   * 探亲假
   */
  private String homeLeavaDays;

  /**
   *工伤假
   */
  private String accidentialLeaveDays;
  private String dayOffLeaveDays;
  /**
   * 产检假
   */
  private String doctorOffLeaveDays;

  /**
   * 流产假
   */
  private String abortionLeaveDays;
  private String normalDays;
  private String outgoingDays;

  private String onBusinessDays;
  private String laterTimes;
  private String earlyTimes;

  private Integer signedTimes;
  /**
   * 日均时长（自然日）
   */
  private String hoursPerDays;
  private String hoursPerWorkDay;

  private String hoursPerRestDay;
  private String clockRate;
  private String absenceDays;

  private Integer isFullAttendanceint;
  private String actualAtteUnofficialDays;
  private String actualAtteOfficialDays;

  /**
   * 应出勤工作日
   */
  private String workingDays;
  private String salaryStandards;
  /**
   * 计薪天数调整
   */
  private String salaryAdjustmentDays;

  /**
   * 工作时长
   */
  private String workHour;

  /**
   * 计薪天数（非正式）
   */
  private String salaryUnofficialDays;
  /**
   * 计薪天数（正式）
   */
  private String salaryOfficialDays;


	public ArchiveMonthlyInfo(User user) {
		this.userId = user.getId();
		this.name = user.getUsername();
		this.workNumber = user.getWorkNumber();
		this.department = user.getDepartmentName();
		this.mobile = user.getMobile();
	}


	public void setStatisData(Map map) {
		this.normalDays = (String) map.get("at1").toString();
		this.absenceDays = (String) map.get("at2").toString();
		this.laterTimes = (String) map.get("at3").toString();
		this.earlyTimes = (String) map.get("at4").toString();
		this.leaveDays = (String) map.get("at8").toString();
		this.dayOffLeaveDays = (String) map.get("at17").toString();
	}
}
