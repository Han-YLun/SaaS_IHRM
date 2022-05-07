package com.ihrm.employee.service;

import com.ihrm.domain.employee.EmployeePositive;
import com.ihrm.employee.dao.PositiveDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


@Service
public class PositiveService {

    @Resource
    private PositiveDao positiveDao;

    public EmployeePositive findById(String uid, Integer status) {
        EmployeePositive positive = positiveDao.findByUserId(uid);
        if (status != null && positive != null) {
            if (!positive.getEstatus().equals(status)) {
                positive = null;
            }
        }
        return positive;
    }

    public EmployeePositive findById(String uid) {
        return positiveDao.findByUserId(uid);
    }

    public void save(EmployeePositive positive) {
        positive.setCreateTime(new Date());
        positive.setEstatus(1);//未执行
        positiveDao.save(positive);
    }
}
