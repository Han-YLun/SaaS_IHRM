package com.ihrm.domain.atte.entity;

import com.ihrm.domain.atte.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 加班规则
 * @author itcast
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atte_extra_duty_rule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraDutyRule extends BaseEntity implements Serializable {

  @Id
  private String id;
  private String extraDutyConfigId;
  private String companyId;
  private String departmentId;


  /**
   * 规则内容
   */
  private String rule;
  private String  ruleStartTime;
  private String ruleEndTime;


  /**
   * 是否调休0不调休1调休
   */
  private Integer isTimeOff;

  /**
   * 是否可用 0开启 1 关闭
   */
  private Integer isEnable;


}
