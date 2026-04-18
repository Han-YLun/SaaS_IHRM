package com.ihrm.atte.service;

import com.ihrm.atte.dao.LeaveApplicationDao;
import com.ihrm.atte.dao.UserDao;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.atte.entity.LeaveApplication;
import com.ihrm.domain.atte.enums.ApprovalStatusEnum;
import com.ihrm.domain.system.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LeaveApplicationService {

    @Resource
    private IdWorker idWorker;

    @Resource
    private LeaveApplicationDao leaveApplicationDao;

    @Resource
    private UserDao userDao;

    public LeaveApplication apply(LeaveApplication leaveApplication, String companyId) {
        leaveApplication.setId(idWorker.nextId() + "");
        leaveApplication.setCompanyId(companyId);
        leaveApplication.setApprovalStatus(ApprovalStatusEnum.PENDING.getCode());
        leaveApplication.setCreateDate(new Date());
        return leaveApplicationDao.save(leaveApplication);
    }

    public List<LeaveApplication> findByCompanyId(String companyId) {
        return leaveApplicationDao.findByCompanyId(companyId);
    }

    public List<LeaveApplication> findByUserId(String userId) {
        return leaveApplicationDao.findByUserId(userId);
    }

    public List<LeaveApplication> findByUserIdAndStatus(String userId, String status) {
        return leaveApplicationDao.findByUserIdAndApprovalStatus(userId, status);
    }

    public LeaveApplication findById(String id) {
        Optional<LeaveApplication> optional = leaveApplicationDao.findById(id);
        return optional.orElse(null);
    }

    public LeaveApplication approve(String id, String approverId, Boolean approved) {
        Optional<LeaveApplication> optional = leaveApplicationDao.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        LeaveApplication leaveApplication = optional.get();
        if (approved) {
            leaveApplication.setApprovalStatus(ApprovalStatusEnum.APPROVED.getCode());
        } else {
            leaveApplication.setApprovalStatus(ApprovalStatusEnum.REJECTED.getCode());
        }
        Optional<User> approverOpt = userDao.findById(approverId);
        if (approverOpt.isPresent()) {
            leaveApplication.setApproverId(approverId);
            leaveApplication.setApproverName(approverOpt.get().getUsername());
        }
        leaveApplication.setUpdateDate(new Date());
        return leaveApplicationDao.save(leaveApplication);
    }

    public Double getTotalLeaveDaysByUserIdAndMonth(String userId, String yearMonth) {
        String startDate = yearMonth + "01";
        String endDate = yearMonth + "31";
        return leaveApplicationDao.sumApprovedLeaveDaysByUserIdAndDateRange(userId, startDate, endDate);
    }

    public Double getLeaveDaysByTypeAndMonth(String userId, String leaveType, String yearMonth) {
        String startDate = yearMonth + "01";
        String endDate = yearMonth + "31";
        return leaveApplicationDao.sumApprovedLeaveDaysByUserIdAndTypeAndDateRange(userId, leaveType, startDate, endDate);
    }
}
