package com.ihrm.domain.atte.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum DeductionEnum {


    /**
     * 迟到扣款
     */
    LATE_DEDUCTION("51000", "LATE_DEDUCTION", "迟到扣款"),

    EARLY_DEDUCTION("52000", "EARLY_DEDUCTION", "早退扣款"),

    ABSENCE_DEDUCTION("53000", "ABSENCE_DEDUCTION", "旷工扣款");




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


    DeductionEnum(String code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }

    private static final Map<String, DeductionEnum> LOOKUP = new HashMap<>();

    //初始化
    static {

        for (DeductionEnum deductionEnum : EnumSet.allOf(DeductionEnum.class)) {

            LOOKUP.put(deductionEnum.code, deductionEnum);
        }
    }

    /**
     * 获取Map的值
     *
     * @param code 值
     * @return 值
     */
    public static DeductionEnum lookup(String code) {

        return LOOKUP.get(code);
    }

    public String getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() { return desc;}

}
