package com.ihrm.atte.service;

import com.ihrm.atte.dao.*;
import com.ihrm.common.entity.PageResult;
import com.ihrm.common.utils.DateUtil;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.atte.bo.AtteItemBO;
import com.ihrm.domain.atte.entity.ArchiveMonthlyInfo;
import com.ihrm.domain.atte.entity.Attendance;
import com.ihrm.domain.social_security.SocialsecurityCompanySettings;
import com.ihrm.domain.system.User;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

@Service
public class AtteService  {

	@Resource
	private IdWorker idWorker;
    @Resource
    private AttendanceDao attendanceDao;
    @Resource
    private UserCDao userDao;
    @Resource(name = "ihrm_attendance")
    private SocialSecurityCompanySettingsDao socialSecurityCompanySettingsDao;

    /**
     * 获取用户的考勤数据
     * @param companyId 公司id
     * @param page  页码
     * @param pageSize  每页大小
     * @return  获取对应公司的对应月份的考勤数据
     */
    public Map<String,Object> getAtteDate(String companyId, int page, int pageSize) throws ParseException {
        //考勤月
        Optional<SocialsecurityCompanySettings> socialSecurityCompanySettingsOptional = socialSecurityCompanySettingsDao.findById(companyId);
        if (!socialSecurityCompanySettingsOptional.isPresent()){
            return Collections.emptyMap();
        }

        SocialsecurityCompanySettings css = socialSecurityCompanySettingsOptional.get();
        String dataMonth = css.getDataMonth();
        //分页查询用户
        Page<User> users = userDao.findPage(companyId, PageRequest.of(page - 1, pageSize));
        List<AtteItemBO> list = new ArrayList<>();
        //循环所有的用户,获取每个用户每天的考勤情况
        for (User user : users.getContent()) {
            AtteItemBO bo = new AtteItemBO();
            BeanUtils.copyProperties(user , bo);
            List<Attendance> attendanceRecord = new ArrayList<>();
            //获取当前月所有的天数
            String[] days = DateUtil.getDaysByYearMonth(dataMonth);
            //循环每天查询考勤记录
            for (String day : days) {
                Attendance atte = attendanceDao.findByUserIdAndDay(user.getId(), day);
                if (atte == null){
                    atte = new Attendance();
                    //旷工
                    atte.setAdtStatu(2);
                    atte.setId(user.getId());
                    atte.setDay(day);
                }
                attendanceRecord.add(atte);
            }
            //封装到attendanceRecord
            bo.setAttendanceRecord(attendanceRecord);
            list.add(bo);
        }
        Map<String,Object> map = new HashMap<>(3);

        //分页对象数据
        PageResult<AtteItemBO> pr = new PageResult<>(users.getTotalElements(), list);
        //待处理的考勤数据
        map.put("data" , pr);
        //待处理的考勤数量
        map.put("tobeTaskCount" , 0);
        //当前的考勤月份
        int month = Integer.parseInt(dataMonth.substring(4));
        map.put("monthOfReport" , month);
        return map;
    }

    /**
     * 编辑考勤
     * @param attendance 考勤数据
     */
    public void editAtte(Attendance attendance) {
        //查询考勤记录是否存在
        Attendance ac = attendanceDao.findByUserIdAndDay(attendance.getUserId(), attendance.getDay());
        if (ac == null){
            //如果不存在,需要设置id
            attendance.setId(idWorker.nextId() + "");
        }else{
            attendance.setId(ac.getId());
        }
        attendanceDao.save(attendance);
    }

    /**
     * 查询归档数据
     * @param atteDate  日期
     * @param companyId 企业id
     * @return  对应日期和对应企业id的归档数据
     */
    public List<ArchiveMonthlyInfo> getReports(String atteDate, String companyId) {
        //查询所有企业用户
        List<User> users = userDao.findByCompanyId(companyId);
        //循环遍历用户列表,统计每个用户的当月考勤记录
        List<ArchiveMonthlyInfo> list = new ArrayList<>();
        for (User user : users) {
            ArchiveMonthlyInfo info = new ArchiveMonthlyInfo(user);
            //统计每个用户的考勤数量
            Map<String,String> map = attendanceDao.statisByUser(user.getId() , atteDate+"%");
            info.setStatisData(map);
            list.add(info);
        }
        return list;
    }

    /**
     * 新建报表,将atte_company_settings中的月份修改为指定的数据
     * @param atteDate 年月
     * @param companyId 企业id
     */
    public void newReports(String atteDate, String companyId) {
        SocialsecurityCompanySettings socialsecurityCompanySettings = socialSecurityCompanySettingsDao.findById(companyId).get();
        socialsecurityCompanySettings.setDataMonth(atteDate);
        socialSecurityCompanySettingsDao.save(socialsecurityCompanySettings);
    }
}
