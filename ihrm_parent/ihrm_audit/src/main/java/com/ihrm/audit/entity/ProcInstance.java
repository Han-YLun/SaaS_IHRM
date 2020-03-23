package com.ihrm.audit.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "proc_instance")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcInstance implements Serializable {
    private static final long serialVersionUID = 1197039778202034930L;

	@Id
	@Column(name = "process_id")
    private String processId; // 流程实例ID

	@Column(name = "process_definition_id")
	private String processDefinitionId; // 流程定义ID

	@Column(name = "process_key")
	private String processKey; // 流程标识

	@Column(name = "process_name")
	private String processName; // 流程名称

	@Column(name = "process_state")
	private String processState; // 流程状态（1审批中；2审批通过；3审批不通过；4撤销)

	@Column(name = "user_id")
    private String userId; // 申请人ID

	@Column(name = "username")
    private String username; // 申请人

	@Column(name = "department_id")
	private String departmentId; // 部门ID

	@Column(name = "department_name")
	private String departmentName; // 部门

	@Column(name = "time_of_entry")
	private Date timeOfEntry; // 入职时间

	@Column(name = "proc_curr_node_user_id")
	private String procCurrNodeUserId;   // 当前节点审批人ID

	@Column(name = "proc_curr_node_user_name")
	private String procCurrNodeUserName; // 当前节点审批人

	@Column(name = "proc_apply_time")
	private Date procApplyTime;          // 申请时间

	@Column(name = "proc_end_time")
	private Date procEndTime;            // 结束流程时间

	@Column(name = "proc_data")
	private String procData;             //申请的业务数据(需要转化为json)
}
