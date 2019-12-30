package com.ihrm.company;

import com.ihrm.company.dao.CompanyDao;
import com.ihrm.domain.company.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyDaoTest {
    @Autowired
    private CompanyDao companyDao;

    @Test
    public void test() {

        //save(company) ;  保存或更新（id）
        //deleteByIid); 根据id删除
        //findById（id）；根据id查询
        //findAll() 查询全部

        Company company = companyDao.findById("1").get();
        System.out.println(company);
    }
}
