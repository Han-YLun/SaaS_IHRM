package com.ihrm.audit.client;

import com.alibaba.fastjson.JSON;
import com.ihrm.common.entity.Result;
import com.ihrm.domain.company.Department;
import com.ihrm.domain.employee.UserCompanyJobs;
import com.ihrm.domain.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignClientService {

	@Autowired
	private SystemFeignClient systemFeignClient;

	// 查询用户信息
	public User getUserInfoByUserId(String user_id) {
		if (user_id == null) throw new RuntimeException("获取当前用户为空！");
		Result result = systemFeignClient.findById(user_id);
		if (!result.isSuccess()) throw new RuntimeException("用户ID不存在，请联系管理员：" + user_id);
		return JSON.parseObject(JSON.toJSONString(result.getData()), User.class);
	}
}
