package com.ihrm.system.utils;

import com.baidu.aip.face.AipFace;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
public class BaiduAiUtil {
    
    //@Value("${my.appId}")
    private String APP_ID;
    //@Value("${my.apiKey}")
    private String API_KEY;
    //@Value("${my.secretKey}")
    private String SECRET_KEY;
    //@Value("${my.imageType}")
    private String IMAGE_TYPE;
    //@Value("${my.groupId}")
    private String groupId;

    private AipFace client;

    private HashMap<String, String> options = new HashMap<String, String>();

    public BaiduAiUtil() {
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
    }

    @PostConstruct
    public void init() {
        client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
    }

    //判断用户是否已经注册了面部信息
    public Boolean faceExist(String userId) throws JSONException {
        JSONObject res = client.getUser(userId, groupId, null);
        int errorCode = res.getInt("error_code");
        return errorCode == 0 ? true : false;
    }


    /**
     *  人脸注册 ：将用户照片存入人脸库中
     */
    public Boolean faceRegister(String userId, String image) throws JSONException {
        // 人脸注册
        JSONObject res = client.addUser(image, IMAGE_TYPE, groupId, userId, options);
        Integer errorCode = res.getInt("error_code");
        return errorCode == 0 ? true : false;
    }

    /**
     *  人脸更新 ：更新人脸库中的用户照片
     */
    public Boolean faceUpdate(String userId, String image) throws JSONException {
        // 人脸更新
        JSONObject res = client.updateUser(image, IMAGE_TYPE, groupId, userId, options);
        Integer errorCode = res.getInt("error_code");
        return errorCode == 0 ? true : false;
    }

    /**
     * 人脸检测：判断上传图片中是否具有面部头像
     */
    public Boolean faceCheck(String image) throws JSONException {
        JSONObject res = client.detect(image, IMAGE_TYPE, options);
        if (res.has("error_code") && res.getInt("error_code") == 0) {
            JSONObject resultObject = res.getJSONObject("result");
            Integer faceNum = resultObject.getInt("face_num");
            return faceNum == 1?true:false;
        }else{
            return false;
        }
    }

    /**
     *  人脸查找：查找人脸库中最相似的人脸并返回数据
     *          处理：用户的匹配得分（score）大于80分，即可认为是同一个用户
     */
    public String faceSearch(String image) throws JSONException {
        JSONObject res = client.search(image, IMAGE_TYPE, groupId, options);
        if (res.has("error_code") && res.getInt("error_code") == 0) {
            JSONObject result = res.getJSONObject("result");
            JSONArray userList = result.getJSONArray("user_list");
            if (userList.length() > 0) {
                JSONObject user = userList.getJSONObject(0);
                double score = user.getDouble("score");
                if(score > 80) {
                    return user.getString("user_id");
                }
            }
        }
        return null;
    }
}
