package com.ihrm.atte.service;

import com.ihrm.atte.dao.AttendanceConfigDao;
import com.ihrm.atte.dao.AttendanceDao;
import com.ihrm.atte.dao.UserCDao;
import com.ihrm.common.poi.ExcelImportUtil;
import com.ihrm.common.utils.DateUtil;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.atte.entity.Attendance;
import com.ihrm.domain.atte.entity.AttendanceConfig;
import com.ihrm.domain.atte.vo.AtteUploadVo;
import com.ihrm.domain.system.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Log4j2
@Service
public class ExcelImportService {

    @Resource
    private UserCDao userDao;

    @Resource
    private AttendanceDao attendanceDao;

    @Resource
    private AttendanceConfigDao attendanceConfigDao;

    @Resource
    private IdWorker idWorker;

    @Value("${atte_workingDays}")
    private String workingDays;

	@Value("${atte_holidays}")
	private String holidays;

    /**
     * 处理考勤数据的文件上传
     * @param file  文件
     * @param companyId 公司id
     */
    public void importAttendanceExcel(MultipartFile file, String companyId) throws Exception {
        //1.将导入的excel文件解析为vo的list集合
        List<AtteUploadVo> atteUploadVos = new ExcelImportUtil<AtteUploadVo>(AtteUploadVo.class).readExcel(file.getInputStream(), 1, 0);
        //2.循环list集合
        for (AtteUploadVo atteUploadVo : atteUploadVos) {
            //2.1 根据上传的手机号码查询用户
            User user = userDao.findByMobile(atteUploadVo.getMobile());
            //2.2 构造考勤对象
            Attendance attendance = new Attendance(atteUploadVo , user);
            attendance.setDay(atteUploadVo.getAtteDate());
            //2.3 判断是否为休假
            if (holidays.contains(atteUploadVo.getAtteDate())){
                attendance.setAdtStatu(23); //休息
            }else if(DateUtil.isWeekend(atteUploadVo.getAtteDate()) || !workingDays.contains(atteUploadVo.getAtteDate())){
                attendance.setAdtStatu(23);
            }else{
                //2.4 判断迟到与早退
                AttendanceConfig ac = attendanceConfigDao.findByCompanyIdAndDepartmentId(companyId , user.getDepartmentId());
                //比较上班时间是否晚于规定上班时间
                if (!DateUtil.comparingDate(ac.getAfternoonStartTime() , atteUploadVo.getInTime())){
                    attendance.setAdtStatu(3);  //迟到
                }else if (DateUtil.comparingDate(ac.getAfternoonEndTime() , atteUploadVo.getOutTime())){
                    //比较下班时间是否早于规定下班时间
                    attendance.setAdtStatu(4);  //早退
                }else{
                    attendance.setAdtStatu(1);  //正常
                }
            }
            //2.5 查询用户是否有考勤记录,如果不存在,保存到数据库
            Attendance atte = attendanceDao.findByUserIdAndDay(user.getId(), atteUploadVo.getAtteDate());
            if (atte == null){
                //之前没有记录
                attendance.setId(idWorker.nextId() + "");
                attendanceDao.save(attendance);
            }
        }
    }
}
