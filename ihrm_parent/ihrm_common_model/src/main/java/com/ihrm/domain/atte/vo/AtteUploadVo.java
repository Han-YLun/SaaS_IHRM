package com.ihrm.domain.atte.vo;

import com.ihrm.domain.poi.ExcelAttribute;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class AtteUploadVo {

	//员工姓名	手机号	工号	上班时间	下班时间
	@ExcelAttribute(sort=0,format="String")
	private String username;
	@ExcelAttribute(sort=1,format="String")
	private String mobile;
	@ExcelAttribute(sort=2,format="String")
	private String workNumber;
	@ExcelAttribute(sort=3,format="Date")
	private Date inTime;
	@ExcelAttribute(sort=4,format="Date")
	private Date outTime;
}
