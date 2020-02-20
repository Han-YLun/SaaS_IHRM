package com.ihrm.domain.system.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@Data
@NoArgsConstructor
@ToString
public class FaceLoginResult implements Serializable {
    private static final long serialVersionUID = -1616426041373762391L;
    /**
     * 二维码使用状态
     */
    private String state;
    /**
     * 登录信息
     */
    private String token;
    /**
     * 用户ID
     */
    private String userId;

    public FaceLoginResult(String state, String token, String userId) {
        this.state = state;
        this.token = token;
        this.userId = userId;
    }

    public FaceLoginResult(String state) {
        this.state = state;
    }
}

