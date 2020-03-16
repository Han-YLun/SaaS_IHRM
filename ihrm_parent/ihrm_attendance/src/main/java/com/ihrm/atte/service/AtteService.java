package com.ihrm.atte.service;

import com.ihrm.atte.dao.AttendanceConfigDao;
import com.ihrm.atte.dao.AttendanceDao;
import com.ihrm.atte.dao.DeductionDictDao;
import com.ihrm.atte.dao.UserDao;
import com.ihrm.common.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtteService  {

	@Autowired
	private IdWorker idWorker;

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private DeductionDictDao deductionDictDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AttendanceConfigDao attendanceConfigDao;
}
