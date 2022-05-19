package com.ihrm.atte.dao;


import com.ihrm.domain.social_security.SocialsecurityCompanySettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("ihrm_attendance")
public interface SocialSecurityCompanySettingsDao extends JpaRepository<SocialsecurityCompanySettings,String> ,JpaSpecificationExecutor<SocialsecurityCompanySettings> {
}
