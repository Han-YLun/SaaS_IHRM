package com.ihrm.employee.service;

import com.ihrm.domain.employee.EmployeeContract;
import com.ihrm.employee.dao.EmployeeContractDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeContractService {

    @Resource
    private EmployeeContractDao employeeContractDao;

    public void save(EmployeeContract contract) {
        contract.setCreateTime(new Date());
        employeeContractDao.save(contract);
    }

    public EmployeeContract findById(String userId) {
        return employeeContractDao.findByUserId(userId);
    }

    public List<EmployeeContract> findExpiringContracts(String companyId, int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String startDate = sdf.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, days);
        String endDate = sdf.format(calendar.getTime());
        return employeeContractDao.findExpiringContracts(companyId, startDate, endDate);
    }
}
