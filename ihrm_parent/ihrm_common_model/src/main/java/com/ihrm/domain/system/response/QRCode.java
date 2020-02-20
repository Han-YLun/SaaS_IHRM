package com.ihrm.domain.system.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QRCode implements Serializable {
    private static final long serialVersionUID = 4375387973088123002L;
    /**
     * 随机生成码
     */
    private String code;
    /**
     * Base64 二维码文件
     */
    private String file;
}
