package com.ihrm.domain.atte.entity;

import com.ihrm.domain.atte.base.BaseEntity;
import com.ihrm.domain.atte.enums.ApprovalStatusEnum;
import com.ihrm.domain.atte.enums.LeaveTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atte_leave_application")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveApplication extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String companyId;
    private String departmentId;
    private String userId;

    private String leaveType;
    private Date startTime;
    private Date endTime;
    private Double leaveDays;

    private String approvalStatus;
    private String approverId;
    private String approverName;

    private String remark;
}
