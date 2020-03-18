package com.ihrm.salarys.service;

import com.ihrm.domain.salarys.CompanySettings;
import com.ihrm.salarys.dao.CompanySettingsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanySettingsService {
	
    @Autowired
    private CompanySettingsDao companySettingsDao;

    //根据id获取查询
    public CompanySettings findById(String companyId) {
        Optional<CompanySettings> optionalCompanySettins = companySettingsDao.findById(companyId);
        return optionalCompanySettins.isPresent() ? optionalCompanySettins.get() : null;
    }

    //保存配置
    public void save(CompanySettings companySettings) {
        companySettings.setIsSettings(1);
        companySettingsDao.save(companySettings);
    }
}
