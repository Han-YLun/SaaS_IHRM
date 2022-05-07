package com.ihrm.social.service;

import com.ihrm.domain.social_security.CompanySettings;
import com.ihrm.social.dao.CompanySettingsDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CompanySettingsService {
	
    @Resource
    private CompanySettingsDao companySettingsDao;

    //根据企业id查询
	public CompanySettings findById(String companyId) {
		Optional<CompanySettings> optional = companySettingsDao.findById(companyId);
		return optional.orElse(null);
	}

	//保存企业设置
	public void save(CompanySettings companySettings) {
		//已经完成当月设置
		companySettings.setIsSettings(1);
		companySettingsDao.save(companySettings);
	}
}
