package com.ihrm.social.dao;

import com.ihrm.domain.social_security.UserSocialSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserSocialSecurityDao extends JpaRepository<UserSocialSecurity, String>, JpaSpecificationExecutor<UserSocialSecurity> {

}
