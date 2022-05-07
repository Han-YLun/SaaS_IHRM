package com.ihrm.employee.service;

import com.ihrm.domain.employee.EmployeeTransferPosition;
import com.ihrm.employee.dao.TransferPositionDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


@Service
public class TransferPositionService {

    @Resource
    private TransferPositionDao transferPositionDao;

    public EmployeeTransferPosition findById(String uid, Integer status) {
        EmployeeTransferPosition transferPosition = transferPositionDao.findByUserId(uid);
        if (status != null && transferPosition != null) {
            if (!transferPosition.getEstatus().equals(status)) {
                transferPosition = null;
            }
        }
        return transferPosition;
    }

    public void save(EmployeeTransferPosition transferPosition) {
        transferPosition.setCreateTime(new Date());
        transferPosition.setEstatus(1); //未执行
        transferPositionDao.save(transferPosition);
    }
}
