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
@Table(name = "proc_task_instance")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcTaskInstance implements Serializable {

    private static final long serialVersionUID = 1197039778202034930L;

	@Id
	private String taskId; // 任务实例ID
    private String handleOpinion; // 处理意见
    private Date   handleTime; // 处理时间
    private String handleType; // 处理类型（2审批通过；3审批不通过；4撤销）
    private String handleUserId; // 实际处理用户ID
    private String handleUserName; // 实际处理用户
    private String processId; // 流程实例ID
    private String taskKey; // 任务节点key
    private String taskName; // 任务节点
}
