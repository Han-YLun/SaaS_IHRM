package com.ihrm.domain.atte.entity;


import com.ihrm.domain.atte.base.BaseEntity;
import com.ihrm.domain.atte.vo.AtteUploadVo;
import com.ihrm.domain.system.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 考勤表
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atte_attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance extends BaseEntity implements Serializable  {

  private static final long serialVersionUID = 594829320797158219L;

  @Id
  private String id;
  private String companyId;
  private String departmentId;

  private String userId;
  private Integer adtStatu;
  private long jobStatu;

  private Date adtInTime;
  private String adtInPlace;
  private String adtInHourse;

  private String adtInCoordinate;
  private Date adtOutTime;
  private String adtOutPlace;
  private String adtOutHourse;
  private String day;   //考勤日期

  public  Attendance(AtteUploadVo vo,User user) {
	 this.adtInTime = vo.getInTime();
	 this.adtOutTime = vo.getOutTime();
	 this.userId = user.getId();
	 this.companyId = user.getCompanyId();
	 this.departmentId = user.getDepartmentId();
	 this.jobStatu = user.getInServiceStatus();
  }

}
