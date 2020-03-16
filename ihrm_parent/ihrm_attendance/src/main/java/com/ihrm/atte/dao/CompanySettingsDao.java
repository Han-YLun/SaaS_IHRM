package com.ihrm.atte.dao;


import com.ihrm.domain.social_security.CompanySettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("ihrm_attendance")
public interface CompanySettingsDao extends JpaRepository<CompanySettings,String> ,JpaSpecificationExecutor<CompanySettings> {
}
