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
 * 扣款类型表
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atte_deduction_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeductionType extends BaseEntity implements Serializable {

  @Id
  private String code;
  private String description;



}
