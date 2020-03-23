package com.ihrm.audit.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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



    @Column(name = "task_key")
    private String taskKey; // 任务节点key

    @Column(name = "task_id")
	private String taskId; // 任务实例ID

    @Column(name = "handle_opinion")
    private String handleOpinion; // 处理意见

    @Column(name = "handle_time")
    private Date   handleTime; // 处理时间

    @Column(name = "handle_type")
    private String handleType; // 处理类型（2审批通过；3审批不通过；4撤销）

    @Column(name = "handle_user_id")
    private String handleUserId; // 实际处理用户ID

    @Column(name = "handle_user_name")
    private String handleUserName; // 实际处理用户
    
    @Id
    @Column(name = "process_id")
    private String processId; // 流程实例ID


    @Column(name = "task_name")
    private String taskName; // 任务节点
}
