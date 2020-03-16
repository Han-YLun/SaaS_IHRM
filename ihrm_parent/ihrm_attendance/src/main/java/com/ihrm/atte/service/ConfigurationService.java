package com.ihrm.atte.service;

import com.ihrm.atte.dao.*;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.atte.entity.*;
import com.ihrm.domain.atte.enums.DeductionEnum;
import com.ihrm.domain.atte.enums.LeaveTypeEnum;
import com.ihrm.domain.atte.vo.ConfigVO;
import com.ihrm.domain.atte.vo.ExtDutyVO;
import com.ihrm.domain.atte.vo.ExtWorkVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;


@Service
public class ConfigurationService{

    @Autowired
    private AttendanceConfigDao attendanceConfigDao;

    @Autowired
    private LeaveConfigDao leaveConfigDao;

    @Autowired
    private DeductionDictDao deductionDictDao;

    @Autowired
    private ExtraDutyConfigDao extraDutyConfigDao;

    @Autowired
    private ExtraDutyRuleDao extraDutyRuleDao;

    @Autowired
    private DayOffConfigDao dayOffConfigDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询考勤设置
     * @param companyId 企业id
     * @param departmentId  部门id
     * @return  企业id和部门id对应的考勤设置
     */
    public AttendanceConfig getAtteConfig(String companyId, String departmentId) {
        return attendanceConfigDao.findByCompanyIdAndDepartmentId(companyId , departmentId);
    }

    /**
     * 保存/更新考勤设置
     * @param ac 考勤信息内容
     */
    public void saveAtteConfig(AttendanceConfig ac) {
        //1.查询是否存在响应的考勤记录
        AttendanceConfig vo = attendanceConfigDao.findByCompanyIdAndDepartmentId(ac.getCompanyId(), ac.getDepartmentId());
        if (vo != null){
            //2.如果存在,更新
            ac.setId(vo.getId());
        }else{
            //3.如果不存在,更新id,保存
            ac.setId(idWorker.nextId() + "");
        }
        attendanceConfigDao.save(ac);
        

    }
}
