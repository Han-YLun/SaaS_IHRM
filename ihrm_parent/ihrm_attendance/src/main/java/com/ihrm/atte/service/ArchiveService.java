package com.ihrm.atte.service;

import com.ihrm.atte.dao.ArchiveMonthlyDao;
import com.ihrm.atte.dao.ArchiveMonthlyInfoDao;
import com.ihrm.atte.dao.AttendanceDao;
import com.ihrm.atte.dao.UserCDao;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.atte.entity.ArchiveMonthly;
import com.ihrm.domain.atte.entity.ArchiveMonthlyInfo;
import com.ihrm.domain.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.naming.Name;
import java.util.List;
import java.util.Map;

@Service("ihrm_attendance_archiveService")
@Transactional
public class ArchiveService {

	@Autowired
	private IdWorker idWorker;

	@Resource(name = "com.ihrm.atte")
	private UserCDao userDao;

	@Autowired
	private ArchiveMonthlyInfoDao archiveMonthlyInfoDao;

	@Autowired
	private ArchiveMonthlyDao archiveMonthlyDao;

	@Autowired
	private AttendanceDao attendanceDao;

	/**
     * 数据归档
	 * @param archiveDate	归档的日期
	 * @param companyId	公司id
	 */
    public void saveArchive(String archiveDate,String companyId) {
		//查询所有企业用户
		List<User> users = userDao.findByCompanyId(companyId);
    	//保存归档主表数据
		ArchiveMonthly archiveMonthly = new ArchiveMonthly();
		archiveMonthly.setId(idWorker.nextId() + "");
		archiveMonthly.setCompanyId(companyId);
		archiveMonthly.setArchiveYear(archiveDate.substring(0 , 4));
		archiveMonthly.setArchiveMonth(archiveDate.substring(5));
		//保存归档明细表数据
		for (User user : users) {
			ArchiveMonthlyInfo info = new ArchiveMonthlyInfo(user);
			//统计每个用户的考勤数量
			Map map = attendanceDao.statisByUser(user.getId() , archiveDate+"%");
			info.setStatisData(map);
			info.setId(idWorker.nextId() + "");
			info.setAtteArchiveMonthlyId(archiveMonthly.getId());
			archiveMonthlyInfoDao.save(info);
		}
		//总人数
		archiveMonthly.setTotalPeopleNum(users.size());
		archiveMonthly.setFullAttePeopleNum(users.size());
		archiveMonthly.setIsArchived(0);
		archiveMonthlyDao.save(archiveMonthly);
    }
}
