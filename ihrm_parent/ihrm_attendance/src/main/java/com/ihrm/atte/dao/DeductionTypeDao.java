package com.ihrm.atte.dao;

import com.ihrm.domain.atte.entity.DeductionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeductionTypeDao extends JpaRepository<DeductionType, String>, JpaSpecificationExecutor<DeductionType> {


}
