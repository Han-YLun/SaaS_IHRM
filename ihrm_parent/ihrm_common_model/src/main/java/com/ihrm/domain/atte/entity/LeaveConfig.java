package com.ihrm.domain.atte.entity;


import com.ihrm.domain.atte.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 请假配置表
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atte_leave_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveConfig extends BaseEntity implements Serializable {

  @Id
  private String id;

  private String companyId;

  private String departmentId;

  private String leaveType; //类型

  private Integer isEnable;


}
