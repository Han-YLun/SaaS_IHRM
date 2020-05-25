package com.ihrm.system.service;


import com.baidu.aip.util.Base64Util;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.system.User;
import com.ihrm.domain.system.response.FaceLoginResult;
import com.ihrm.domain.system.response.QRCode;
import com.ihrm.system.dao.UserDao;
import com.ihrm.system.utils.BaiduAiUtil;
import com.ihrm.system.utils.QRCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

@Service
public class FaceLoginService {

    //@Value("${my.url}")
    private String url;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private QRCodeUtil qrCodeUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BaiduAiUtil baiduAiUtil;

    @Autowired
    private UserDao userDao;

	//创建二维码
    public QRCode getQRCode() throws Exception {
        //1.创建唯一标识
        String code = idWorker.nextId() + "";
        //2.生成二维码
        String content = url + "?code=" + code;
        String file = qrCodeUtil.crateQRCode(content);
        //3.存入当前二维码的状态(存入redis)
        FaceLoginResult result = new FaceLoginResult("-1");
        redisTemplate.boundValueOps(getCacheKey(code)).set(result , 10 , TimeUnit.MINUTES);
		return new QRCode(code , file);
    }

	//根据唯一标识，查询用户是否登录成功
    public FaceLoginResult checkQRCode(String code) {
        String key = getCacheKey(code);
        FaceLoginResult faceLoginResult = (FaceLoginResult) redisTemplate.opsForValue().get(key);
        return faceLoginResult;
    }

	//扫描二维码之后，使用拍摄照片进行登录
    public String loginByFace(String code, MultipartFile attachment) throws Exception {
        //1.调用百度云AI查询当前的用户
        String userId = baiduAiUtil.faceSearch(Base64Util.encode(attachment.getBytes()));
        //2.自动登录
        FaceLoginResult result = new FaceLoginResult("0");
        if (userId != null){
            //模拟登陆
            User user = userDao.findById(userId).get();
            if (user != null){
                //获取subject
                Subject subject = SecurityUtils.getSubject();
                //调用login方法登陆
                subject.login(new UsernamePasswordToken(user.getMobile() , user.getPassword()));
                //获取token
                String token = (String) subject.getSession().getId();
                result = new FaceLoginResult("1" , token , userId);
            }
        }
        //3.修改二维码的状态
        redisTemplate.boundValueOps(getCacheKey(code)).set(result , 10 , TimeUnit.MINUTES);
		return userId;
    }

	//构造缓存key
    private String getCacheKey(String code) {
        return "qrcode_" + code;
    }
}
