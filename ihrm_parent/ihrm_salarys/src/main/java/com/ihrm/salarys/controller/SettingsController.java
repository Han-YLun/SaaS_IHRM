package com.ihrm.salarys.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.salarys.Settings;
import com.ihrm.salarys.service.SettingsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/*
 *	津贴controller
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/salarys")
public class SettingsController extends BaseController {

	@Resource
	private SettingsService settingsService;

	/**
	 * 获取企业计薪及津贴设置
	 */
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public Result getSettings() throws Exception {
		Settings settings = settingsService.findById(companyId);
		return new Result(ResultCode.SUCCESS, settings);
	}

	/**
	 * 保存企业计薪及津贴设置
	 */
	@RequestMapping(value = "/settings", method = RequestMethod.POST)
	public Result saveSettings(@RequestBody Settings settings) {
		settings.setCompanyId(companyId);
		settingsService.save(settings);
		return new Result(ResultCode.SUCCESS);
	}
}
