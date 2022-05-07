package com.ihrm.company.service;

import com.ihrm.common.utils.IdWorker;
import com.ihrm.company.dao.CompanyDao;
import com.ihrm.domain.company.Company;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyService {

    @Resource
    private CompanyDao companyDao;

    @Resource
    private IdWorker idWorker;
    /**
     * 保存用户
     *  1.配置idwork到工程
     *  2.在service中注入idwork
     *  3.通过idwork生成id
     *  4.保存用户
     */
    public void add(Company company) {
        //基本属性的设置
        String id = idWorker.nextId()+"";
        company.setId(id);
        //默认的状态
        company.setAuditState("0");//0：未审核，1：已审核
        company.setState(1); //0.未激活，1：已激活
        companyDao.save(company);
    }

    /**
     * 更新用户
     *  1.参数：Company
     *  2.根据id查询用户对象
     *  3.设置修改的属性
     *  4.调用dao完成更新
     */
    public void update(Company company) {
        companyDao.save(company);
    }


    /**
     * 删除用户
     */
    public void deleteById(String id) {
        companyDao.deleteById(id);
    }

    /**
     * 根据id查询用户
     */
    public Company findById(String id) {
        return companyDao.findById(id).get();
    }

    /**
     * 查询用户列表
     */
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    public Company save(Company company) {
        return companyDao.save(company);
    }
}
