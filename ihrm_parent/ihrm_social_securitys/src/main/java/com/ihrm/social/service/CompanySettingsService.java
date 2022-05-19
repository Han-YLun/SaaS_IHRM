package com.ihrm.social.service;

import com.ihrm.domain.social_security.SocialsecurityCompanySettings;
import com.ihrm.social.dao.CompanySettingsDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CompanySettingsService {
	
    @Resource
    private CompanySettingsDao companySettingsDao;

    //根据企业id查询
	public SocialsecurityCompanySettings findById(String companyId) {
		Optional<SocialsecurityCompanySettings> optional = companySettingsDao.findById(companyId);
		return optional.orElse(null);
	}

	//保存企业设置
	public void save(SocialsecurityCompanySettings socialsecurityCompanySettings) {
		//已经完成当月设置
		socialsecurityCompanySettings.setIsSettings(1);
		companySettingsDao.save(socialsecurityCompanySettings);
	}
}
