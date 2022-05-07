package com.ihrm.employee.service;

import com.ihrm.domain.employee.UserCompanyJobs;
import com.ihrm.employee.dao.UserCompanyJobsDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserCompanyJobsService {

    @Resource
    private UserCompanyJobsDao userCompanyJobsDao;

    public void save(UserCompanyJobs jobsInfo) {
        userCompanyJobsDao.save(jobsInfo);
    }

    public UserCompanyJobs findById(String userId) {
        return userCompanyJobsDao.findByUserId(userId);
    }
}
