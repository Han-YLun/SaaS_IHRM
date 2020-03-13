package com.ihrm.employee.service;

import com.ihrm.common.service.BaseService;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.employee.EmployeeArchive;
import com.ihrm.employee.dao.ArchiveDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class ArchiveService extends BaseService {
    @Autowired
    private ArchiveDao archiveDao;
    @Autowired
    private IdWorker idWorker;

    public void save(EmployeeArchive archive) {
        archive.setId(idWorker.nextId() + "");
        archive.setCreateTime(new Date());
        archiveDao.save(archive);
    }

    public EmployeeArchive findLast(String companyId, String month) {
        EmployeeArchive archive = archiveDao.findByLast(companyId, month);
        return archive;
    }

    public List<EmployeeArchive> findAll(Integer page, Integer pagesize, String year, String companyId) {
        int index = (page - 1) * pagesize;
        return archiveDao.findAllData(companyId, year + "%", index, pagesize);
    }

    public Long countAll(String year, String companyId) {
        return archiveDao.countAllData(companyId, year+"%");
    }


    public Page<EmployeeArchive> findSearch(Map<String,Object> map, int page, int size) {
        return archiveDao.findAll(createSpecification(map), PageRequest.of(page-1, size));
    }

    /**
     * 动态条件构建
     * @param searchMap
     * @return
     */
    private Specification<EmployeeArchive> createSpecification(Map searchMap) {
        return new Specification<EmployeeArchive>() {
            @Override
            public Predicate toPredicate(Root<EmployeeArchive> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 企业id
                if (searchMap.get("companyId")!=null && !"".equals(searchMap.get("companyId"))) {
                    predicateList.add(cb.like(root.get("companyId").as(String.class), (String)searchMap.get("companyId")));
                }
                if (searchMap.get("year")!=null && !"".equals(searchMap.get("year"))) {
                    predicateList.add(cb.like(root.get("mouth").as(String.class), (String)searchMap.get("year")));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
