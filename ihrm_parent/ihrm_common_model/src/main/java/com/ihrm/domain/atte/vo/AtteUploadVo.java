package com.ihrm.domain.atte.vo;

import com.ihrm.domain.poi.ExcelAttribute;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class AtteUploadVo {

	@ExcelAttribute(sort=0,format="String")
	private String username;	//员工姓名

	@ExcelAttribute(sort=1,format="String")
	private String mobile;		//手机号

	@ExcelAttribute(sort=2,format="String")
	private String workNumber;	//工号

	@ExcelAttribute(sort=3,format="Date")
	private Date inTime;		//上班时间

	@ExcelAttribute(sort=4,format="Date")
	private Date outTime;		//下班时间

	@ExcelAttribute(sort = 5 , format = "String")
	private String atteDate;	//考勤日期
}
