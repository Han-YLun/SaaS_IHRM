package com.ihrm.domain.atte.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ApprovalStatusEnum {

    PENDING("1", "PENDING", "待审批"),
    APPROVED("2", "APPROVED", "已通过"),
    REJECTED("3", "REJECTED", "已拒绝");

    private final String code;
    private final String value;
    private final String desc;

    ApprovalStatusEnum(String code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }

    private static final Map<String, ApprovalStatusEnum> LOOKUP = new HashMap<>();
    private static final Map<String, ApprovalStatusEnum> REVERSEMAP = new HashMap<>();

    static {
        for (ApprovalStatusEnum statusEnum : EnumSet.allOf(ApprovalStatusEnum.class)) {
            LOOKUP.put(statusEnum.code, statusEnum);
            REVERSEMAP.put(statusEnum.getValue(), statusEnum);
        }
    }

    public String getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ApprovalStatusEnum lookup(String code) {
        return LOOKUP.get(code);
    }

    public static ApprovalStatusEnum reverselookup(String value) {
        return REVERSEMAP.get(value);
    }
}
