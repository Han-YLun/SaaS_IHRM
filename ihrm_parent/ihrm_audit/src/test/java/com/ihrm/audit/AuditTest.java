package com.ihrm.audit;

import com.ihrm.audit.dao.ProcUserGroupDao;
import com.ihrm.audit.entity.ProcUserGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;

/**
 * @author: hyl
 * @date: 2020/03/19
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AuditTest {

    /**
     * 测试业务数据库
     */
    @Autowired
    private ProcUserGroupDao procUserGroupDao;

    @Test
    public void test(){
        int size = procUserGroupDao.findAll().size();
        System.out.println(size);
    }
}
