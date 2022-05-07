package com.ihrm.audit.client;

import com.alibaba.fastjson.JSON;
import com.ihrm.common.entity.Result;
import com.ihrm.domain.system.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author hyl
 */
@Service
public class FeignClientService {

	@Resource
	private SystemFeignClient systemFeignClient;

	/**
	 * 查询用户信息
	 * @param userId
	 * @return
	 */
	public User getUserInfoByUserId(String userId) {
		if (StringUtils.isEmpty(userId)){
			throw new RuntimeException("获取当前用户为空！");
		}

		Result result = systemFeignClient.findById(userId);
		if (!result.isSuccess()){
			throw new RuntimeException("用户ID不存在，请联系管理员：" + userId);
		}
		return JSON.parseObject(JSON.toJSONString(result.getData()), User.class);
	}
}
