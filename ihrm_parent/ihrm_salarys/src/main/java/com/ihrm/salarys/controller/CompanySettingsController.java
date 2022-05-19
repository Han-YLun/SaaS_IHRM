package com.ihrm.salarys.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.salarys.SalaryCompanySettings;
import com.ihrm.salarys.service.CompanySettingsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 企业设置Controller
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/salarys")
public class CompanySettingsController extends BaseController {

	@Resource
	private CompanySettingsService companySettingsService;

	/**
	 * 获取企业是否设置工资
	 */
	@RequestMapping(value = "/company-settings", method = RequestMethod.GET)
	public Result getCompanySettings() throws Exception {
		SalaryCompanySettings companySettings = companySettingsService.findById(companyId);
		return new Result(ResultCode.SUCCESS, companySettings);
	}

	/**
	 * 保存企业工资设置
	 */
	@RequestMapping(value = "/company-settings", method = RequestMethod.POST)
	public Result saveCompanySettings(@RequestBody SalaryCompanySettings companySettings) {
		companySettings.setCompanyId(companyId);
		companySettingsService.save(companySettings);
		return new Result(ResultCode.SUCCESS);
	}

	//构造新报表
	@PutMapping(value = "/reports/{yearMonth}/newReport")
	public Result newReport(@PathVariable(value = "yearMonth") String yearMonth) {
		SalaryCompanySettings companySettings = new SalaryCompanySettings();
		companySettings.setCompanyId(companyId);
		companySettings.setDataMonth(yearMonth);
		companySettingsService.save(companySettings);
		return new Result(ResultCode.SUCCESS);
	}
}
