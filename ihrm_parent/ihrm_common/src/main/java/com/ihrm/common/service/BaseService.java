package com.ihrm.common.service;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author: hyl
 * @date: 2020/01/04
 **/
public class BaseService<T> {

    protected Specification<T> getSpec(String companyId){
        return (Specification<T>) (Specification) (root, query, cb) -> cb.equal(root.get("companyId").as(String.class) , companyId);
    }
}
