package com.ihrm.domain.social_security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
//@Table(name = "ss_user_social_security")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSocialSecurityItem implements Serializable {
    @Transient
    private static final long serialVersionUID = 9039032874868025548L;
    //ID
    @Id
    private String id;

    /**
     * 姓名
     */
    private String username;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 工号
     */
    private String workNumber;
    /**
     * 部门名称
     */
    private String departmentName;
    /**
     * 入职时间
     */
    private Date timeOfEntry;
    /**
     * 离职时间
     */
    private Date leaveTime;
    /**
     * 参保城市id
     */
    private String participatingInTheCityId;
	private String participatingInTheCity;

    /**
     * 社保基数
     */
    private Integer socialSecurityBase;
    /**
     * 公积金城市id
     */
    private String providentFundCityId;
	private String providentFundCity;
    /**
     * 公积金基数
     */
    private Integer providentFundBase;
}
