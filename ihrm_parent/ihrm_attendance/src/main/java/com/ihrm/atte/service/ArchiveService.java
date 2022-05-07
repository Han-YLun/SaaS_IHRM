package com.ihrm.atte.service;

import com.ihrm.atte.dao.ArchiveMonthlyDao;
import com.ihrm.atte.dao.ArchiveMonthlyInfoDao;
import com.ihrm.atte.dao.AttendanceDao;
import com.ihrm.atte.dao.UserCDao;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.atte.entity.ArchiveMonthly;
import com.ihrm.domain.atte.entity.ArchiveMonthlyInfo;
import com.ihrm.domain.system.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("ihrm_attendance_archiveService")
@Transactional
public class ArchiveService {

	@Resource
	private IdWorker idWorker;

	@Resource(name = "com.ihrm.atte")
	private UserCDao userDao;

	@Resource
	private ArchiveMonthlyInfoDao archiveMonthlyInfoDao;

	@Resource
	private ArchiveMonthlyDao archiveMonthlyDao;

	@Resource
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
			info.setArchiveDate(archiveDate);
			archiveMonthlyInfoDao.save(info);
		}
		//总人数
		archiveMonthly.setTotalPeopleNum(users.size());
		archiveMonthly.setFullAttePeopleNum(users.size());
		archiveMonthly.setIsArchived(0);
		archiveMonthlyDao.save(archiveMonthly);
    }

	/**
	 * 根据年份查询,当年的所有考勤历史
	 * @param year	年份
	 * @param companyId	企业id
	 * @return	对应年份和对应企业id的所有考勤历史
	 */
	public List<ArchiveMonthly> findByYear(String year,String companyId) {
		return archiveMonthlyDao.findByCompanyIdAndArchiveYear(companyId , year);
    }

	/**
	 *	查询归档详情列表
	 * @param id	atte_archive_monthly_id
	 * @return
	 */
	public List<ArchiveMonthlyInfo> findMonthInfoByAmid(String id) {
		return archiveMonthlyInfoDao.findByAtteArchiveMonthlyId(id);
	}

	/**
	 * 根据用户id和年月查询归档明细
	 * @param userId	用户id
	 * @param yearMonth	年月
	 * @return	用户id和对应年月的归档明细
	 */
	public ArchiveMonthlyInfo findUserArchiveDetail(String userId, String yearMonth) {
		return	archiveMonthlyInfoDao.findByUserIdAndArchiveDate(userId , yearMonth);
	}
}
