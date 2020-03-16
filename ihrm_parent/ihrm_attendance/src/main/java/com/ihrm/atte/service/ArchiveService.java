package com.ihrm.atte.service;

import com.alibaba.fastjson.JSONObject;
import com.ihrm.atte.dao.*;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.atte.entity.ArchiveMonthly;
import com.ihrm.domain.atte.entity.ArchiveMonthlyInfo;
import com.ihrm.domain.atte.bo.AtteReportMonthlyBO;
import com.ihrm.domain.atte.vo.ArchiveInfoVO;
import com.ihrm.domain.atte.vo.ArchiveItemVO;
import com.ihrm.domain.atte.vo.ArchiveVO;
import com.ihrm.domain.atte.vo.ReportVO;
import com.ihrm.domain.system.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class ArchiveService {

	@Autowired
	private AttendanceDao attendanceDao;

	@Autowired
	private ArchiveMonthlyDao atteArchiveMonthlyDao;

	@Autowired
	private ArchiveMonthlyInfoDao archiveMonthlyInfoDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private IdWorker idWorkker;
}
