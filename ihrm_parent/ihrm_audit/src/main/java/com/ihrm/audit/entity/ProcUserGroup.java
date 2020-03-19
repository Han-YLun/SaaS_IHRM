package com.ihrm.audit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * (DepartmentApprover)实体类
 */
@Entity
@Table(name = "proc_user_group")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcUserGroup {
	private static final long serialVersionUID = -9084332495284489553L;
	@Id
	private String id;
	private String name;
	private String param;
	private String isql;
}
