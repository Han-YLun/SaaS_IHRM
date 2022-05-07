package com.ihrm.salarys.feign;

import com.alibaba.fastjson.JSON;
import com.ihrm.common.entity.Result;
import com.ihrm.domain.atte.entity.ArchiveMonthlyInfo;
import com.ihrm.domain.social_security.ArchiveDetail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FeignClientService {

	@Resource
	private AttendanceFeignClient attendanceFeignClient;

	@Resource
	private SocialSecurityFeignClient socialFeignClient;

	//考勤
	public ArchiveMonthlyInfo getAtteInfo(String userId,String yearMonth) {
		Result result = attendanceFeignClient.historyData(userId, yearMonth);
		ArchiveMonthlyInfo info = null;
		if (result.isSuccess()) {
			info = JSON.parseObject(JSON.toJSONString(result.getData()), ArchiveMonthlyInfo.class);
		}else{
			System.out.println("请求考勤模块失败");
		}
		return info;
	}

	//社保
	public ArchiveDetail getSocialInfo(String userId, String yearMonth) {
		Result result = socialFeignClient.historyData(userId, yearMonth);
		ArchiveDetail info = null;
		if (result.isSuccess()) {
			info = JSON.parseObject(JSON.toJSONString(result.getData()), ArchiveDetail.class);
		}else{
			System.out.println("请求考勤社保失败");
		}
		return info;
	}
}
