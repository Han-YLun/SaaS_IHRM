package com.ihrm.domain.atte.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 请假类型枚举 用Map封装
 * @author jingpenfei
 */
public enum LeaveTypeEnum {

    YEAR_LEAVE("60000","YEAR_LEAVE","年假"),
    LEAVE("60100","LEAVE","事假"),
    SICK_LEAVE("60200","SICK_LEAVE","病假"),
    MARRAIAGE_LEAVE("60300","MARRAIAGE_LEAVE","婚假"),
    FUNERAL_LEAVE("60400","FUNERAL_LEAVE","丧假"),
    MATERNITY_LEAVE("60500","MATERNITY_LEAVE","产假"),
    REWARD_MATERNITY_LEAVE("60600","REWARD_MATERNITY_LEAVE","奖励产假"),
    PATERNITY_LEAVE("60700","PATERNITY_LEAVE","陪产假"),
    HOME_LEAVE("60800","HOME_LEAVE","探亲假"),
    ACCIDENTIAL_LEAVE("60900","ACCIDENTIAL_LEAVE","工伤假"),
    DAY_OFF_LEAVE("61000","DAY_OFF_LEAVE","调休假"),
    DOCTOR_OFF_LEAVE("61100","DOCTOR_OFF_LEAVE","产检假"),
    ABORTION_LEAVE("61200","ABORTION_LEAVE","流产假"),
    LONG_SICK_LEAVE("61300","LONG_SICK_LEAVE","长期病假"),
    TEST_LEAVE("61400","TEST_LEAVE","测试假");


    /**
     * 类型编码
     */
    private final String code;

    /**
     * 类型值
     */
    private final String value;

    /**
     * 类型描述
     */
    private final String desc;


    LeaveTypeEnum(String code ,String value,String desc){
        this.code = code;
        this.value = value;
        this.desc = desc;
    }



    private static final Map<String, LeaveTypeEnum> LOOKUP = new HashMap<>();

    private static final Map<String, LeaveTypeEnum> REVERSEMAP = new HashMap<>();


    //初始化
    static {

        for ( LeaveTypeEnum leaveTypeEnum: EnumSet.allOf(LeaveTypeEnum.class)
        ) {

            LOOKUP.put(leaveTypeEnum.code,leaveTypeEnum);
            REVERSEMAP.put(leaveTypeEnum.getValue(),leaveTypeEnum);
        }
    }


    public String getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }




    /**
     * 获取Map的值
     * @param code  值
     * @return 值
     */
    public static LeaveTypeEnum lookup(String code) {

        return LOOKUP.get(code);
    }

    /**
     * 获取反向Map
     * @param value
     * @return
     */
    public static LeaveTypeEnum reverselookup(String value) {

        return REVERSEMAP.get(value);
    }


    public static void main(String[] args) {

        System.out.println(LeaveTypeEnum.lookup("60100").getValue());
        System.out.println(LeaveTypeEnum.reverselookup("LEAVE").getCode());


    }


}
