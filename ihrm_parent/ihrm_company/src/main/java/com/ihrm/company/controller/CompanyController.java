package com.ihrm.company.controller;

import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.company.service.CompanyService;
import com.ihrm.domain.company.Company;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//解决跨域问题
@CrossOrigin
@RestController
@RequestMapping(value="/company")
public class CompanyController {

    @Resource
    private CompanyService companyService;

    //保存用户
    @RequestMapping(value="",method = RequestMethod.POST)
    public Result save(@RequestBody Company company)  {
        //业务操作
        companyService.add(company);
        return new Result(ResultCode.SUCCESS);
    }

    //根据id更新企业信息
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable(value="id") String id, @RequestBody Company company ) {
        //业务操作
        company.setId(id);
        companyService.update(company);
        return new Result(ResultCode.SUCCESS);
    }

    //根据id删除企业
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value="id") String id) {
        companyService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    //根据id查询企业
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Result findCompanyById(@PathVariable(value="id") String id){
        Company company = companyService.findById(id);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(company);
        return result;
    }

    //查询全部企业列表
    @RequestMapping(value="",method = RequestMethod.GET)
    public Result findAll() {
        List<Company> list = companyService.findAll();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(list);
        return result;
    }

    //更新企业状态
    @RequestMapping(value="/{id}/state/{state}",method = RequestMethod.PUT)
    public Result findAll(@PathVariable(value = "id") String id ,
                          @PathVariable(value = "state") int state) {
        Company company = companyService.findById(id);
        company.setState(state);
        companyService.save(company);
        return new Result(ResultCode.SUCCESS);
    }
}
