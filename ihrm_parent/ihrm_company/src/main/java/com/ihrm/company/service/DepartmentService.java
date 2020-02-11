package com.ihrm.company.service;

import com.ihrm.common.service.BaseService;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.company.dao.DepartmentDao;
import com.ihrm.domain.company.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: hyl
 * @date: 2020/01/03
 **/

@Service
public class DepartmentService extends BaseService {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 1.保存部门
     */
    public void save(Department department) {
        //设置主键的值
        String id = idWorker.nextId()+"";
        department.setId(id);
        //调用dao保存部门
        departmentDao.save(department);
    }

    /**
     * 2.更新部门
     */
    public void update(Department department) {
        //1.根据id查询部门
        Department dept = departmentDao.findById(department.getId()).get();
        //2.设置部门属性
        dept.setCode(department.getCode());
        dept.setIntroduce(department.getIntroduce());
        dept.setName(department.getName());
        //3.更新部门
        departmentDao.save(dept);
    }

    /**
     * 3.根据id查询部门
     */
    public Department findById(String id) {
        return departmentDao.findById(id).get();
    }

    /**
     * 4.查询全部部门列表
     */
    public List<Department> findAll(String companyId) {
        return departmentDao.findAll(getSpec(companyId));
    }

    /**
     * 5.根据id删除部门
     */
    public void deleteById(String id) {
        departmentDao.deleteById(id);
    }

    /**
     * 根据部门编码和企业id查询部门
     * @param code  部门编码
     * @param companyId 企业id
     * @return
     */
    public Department findByCode(String code, String companyId) {
        return departmentDao.findByCodeAndCompanyId(code , companyId);
    }
}
