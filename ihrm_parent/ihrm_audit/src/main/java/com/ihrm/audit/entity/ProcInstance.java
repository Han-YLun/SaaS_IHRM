package com.ihrm.audit.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String processId; // 流程实例ID

	private String processDefinitionId; // 流程定义ID
	private String processKey; // 流程标识
	private String processName; // 流程名称
	private String processState; // 流程状态（1审批中；2审批通过；3审批不通过；4撤销)
    private String userId; // 申请人ID
    private String username; // 申请人
	private String departmentId; // 部门ID
	private String departmentName; // 部门
	private Date timeOfEntry; // 入职时间

	private String procCurrNodeUserId;   // 当前节点审批人ID
	private String procCurrNodeUserName; // 当前节点审批人
	private Date procApplyTime;          // 申请时间
	private Date procEndTime;            // 结束流程时间
	private String procData;             //申请的业务数据(需要转化为json)
}
