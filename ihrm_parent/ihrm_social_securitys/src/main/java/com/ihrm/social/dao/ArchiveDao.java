package com.ihrm.social.dao;

import com.ihrm.domain.social_security.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.Lob;
import java.util.List;

/**
 * 自定义dao接口继承
 *      JpaRepository<实体类，主键>
 *      JpaSpecificationExecutor<实体类>
 */
public interface ArchiveDao extends JpaRepository<Archive,String> ,JpaSpecificationExecutor<Archive> {

    Archive findByCompanyIdAndYearsMonth(String companyId, String yearMonth);

    List<Archive> findByCompanyIdAndYearsMonthLike(String companyId, String s);
}
