package com.ihrm.salarys.service;

import com.ihrm.domain.salarys.Settings;
import com.ihrm.salarys.dao.SettingsDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 福利津贴service
 */
@Service
public class SettingsService {

    @Resource
    private SettingsDao settingsDao;

    /**
     * 根据id获取企业设置
     * @param companyId 企业id
     * @return 企业设置
     */
    public Settings findById(String companyId) {
        Optional<Settings> optionalSettings = settingsDao.findById(companyId);
        return optionalSettings.orElse(null);
    }

    /**
     * 保存配置
     */
    public void save(Settings settings) {
        settingsDao.save(settings);
    }
}
