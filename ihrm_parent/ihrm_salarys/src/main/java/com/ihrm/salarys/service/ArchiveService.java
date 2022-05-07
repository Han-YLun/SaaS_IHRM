package com.ihrm.salarys.service;

import com.alibaba.fastjson.JSON;
import com.ihrm.domain.atte.entity.ArchiveMonthlyInfo;
import com.ihrm.domain.salarys.SalaryArchive;
import com.ihrm.domain.salarys.SalaryArchiveDetail;
import com.ihrm.domain.salarys.Settings;
import com.ihrm.domain.salarys.UserSalary;
import com.ihrm.domain.social_security.ArchiveDetail;
import com.ihrm.salarys.dao.ArchiveDao;
import com.ihrm.salarys.dao.ArchiveDetailDao;
import com.ihrm.salarys.dao.UserSalaryDao;
import com.ihrm.salarys.feign.AttendanceFeignClient;
import com.ihrm.salarys.feign.SocialSecurityFeignClient;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 归档service
 */
@Service
public class ArchiveService {

    @Resource
    private ArchiveDao archiveDao;

    @Resource
    private ArchiveDetailDao archiveDetailDao;

    @Resource
    private AttendanceFeignClient attendanceFeignClient;

    @Resource
    private SocialSecurityFeignClient socialSecurityFeignClient;

    @Resource
    private SettingsService settingsService;

    @Resource
    private UserSalaryDao userSalaryDao;

    @Resource
    private SalaryService salaryService;


    /**
     * 根据企业和年月查询归档主表数据
     * @param yearMonth 年月
     * @param companyId 企业id
     * @return  企业和年月对应的归档主表数据
     */
    public SalaryArchive findSalaryArchive(String yearMonth, String companyId) {
        return archiveDao.findByCompanyIdAndYearsMonth(companyId , yearMonth);
    }

    /**
     * 根据归档的id查询所有的归档明细记录
     * @param id    归档id
     * @return  归档id对应的所有的归档明细记录
     */
    public List<SalaryArchiveDetail> findSalaryDetail(String id) {
        return archiveDetailDao.findByArchiveId(id);
    }

    /**
     *  查询月报表
     * @param yearMonth 年月
     * @param companyId 企业id
     * @return  对应企业id和年月的月报表数据
     */
    public List<SalaryArchiveDetail> getReports(String yearMonth, String companyId) {
        List<SalaryArchiveDetail> list = new ArrayList<>();
        //查询当前企业的福利津贴
        Settings setting = settingsService.findById(companyId);
        //查询所有的用户
        Page<Map> users = userSalaryDao.findPage(companyId, null);
        //遍历用户数据
        for (Map user : users.getContent()) {
            //构造SalaryArchiveDetail
            SalaryArchiveDetail saDetail = new SalaryArchiveDetail();
            saDetail.setUser(user);
            //获取每个用户社保数据
            Object obj = socialSecurityFeignClient.historyData(saDetail.getUserId(), yearMonth).getData();
            if (obj != null){
                ArchiveDetail socialInfo = JSON.parseObject(JSON.toJSONString(obj),  ArchiveDetail.class);
                if (socialInfo != null){
                    saDetail.setSocialInfo(socialInfo);
                    obj = attendanceFeignClient.historyData(saDetail.getUserId(), yearMonth).getData();
                    if (obj != null){
                        ArchiveMonthlyInfo atteInfo = JSON.parseObject(JSON.toJSONString(obj),  ArchiveMonthlyInfo.class);
                        if (atteInfo != null){
                            saDetail.setAtteInfo(atteInfo);
                            //获取每个用户的薪资
                            UserSalary userSalary = salaryService.findUserSalary(saDetail.getUserId());
                            if (userSalary != null){
                                saDetail.setUserSalary(userSalary);
                                //计算工资
                                saDetail.calSalary(setting);
                            }
                        }
                    }
                }
            }

            list.add(saDetail);
        }
        return list;
    }
}
