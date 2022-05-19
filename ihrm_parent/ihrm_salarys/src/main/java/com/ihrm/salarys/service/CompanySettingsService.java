package com.ihrm.salarys.service;

import com.ihrm.domain.salarys.SalaryCompanySettings;
import com.ihrm.salarys.dao.SalaryCompanySettingsDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CompanySettingsService {
	
    @Resource
    private SalaryCompanySettingsDao salaryCompanySettingsDao;

    //根据id获取查询
    public SalaryCompanySettings findById(String companyId) {
        Optional<SalaryCompanySettings> optionalCompanySettins = salaryCompanySettingsDao.findById(companyId);
        return optionalCompanySettins.orElse(null);
    }

    //保存配置
    public void save(SalaryCompanySettings companySettings) {
        companySettings.setIsSettings(1);
        salaryCompanySettingsDao.save(companySettings);
    }
}
