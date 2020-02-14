package cn.itcast.shiro.controller;

import cn.itcast.shiro.domain.User;
import cn.itcast.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.swing.event.TreeWillExpandListener;
import java.io.Serializable;
import java.util.Enumeration;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //添加
    //使用shiro注解鉴权
    @RequiresPermissions("user-home")
    @RequestMapping(value = "/user/home")
    public String home() {
        return "访问个人首页成功";
    }

    //添加
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String add() {
        return "添加用户成功";
    }
	
    //查询
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String find() {
        return "查询用户成功";
    }
	
    //更新
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public String update(String id) {
        return "更新用户成功";
    }
	
    //删除
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public String delete() {
        return "删除用户成功";
    }
	
	//用户登录
	@RequestMapping(value="/login")
    public String login(String username,String password) {
        try{
            //md5密码加密
            password = new Md5Hash(password , username , 3).toString();
            //构造登录令牌
            UsernamePasswordToken upToken = new UsernamePasswordToken(username , password);
            //1.获取subject
            Subject subject = SecurityUtils.getSubject();


            //获取session
            String sid = (String) subject.getSession().getId();

            //2.调用subject进行登录
            subject.login(upToken);
            return "登录成功:" + sid;
        }catch (Exception e){
            return "用户名或密码错误";
        }

    }

    //登录成功后,打印所有session内容
    @RequestMapping(value = "/show")
    public String show(HttpSession session){
        //获取session中所有的键值
        Enumeration<?> enumeration = session.getAttributeNames();
        //遍历enumeration
        while (enumeration.hasMoreElements()){
            //获取session键值
            String name = enumeration.nextElement().toString();
            //根据键值取session中的值
            Object value = session.getAttribute(name);
            //打印结果
            System.out.println("<B>" + name + "</B>=" + value + "<br>/n");
        }
        return "查看session成功";
    }

    @RequestMapping(value = "/autherror")
    public String autherror(int code){
        return code == 1 ? "未登录" : "未授权";
    }

    public static void main(String[] args) {
        System.out.println(new Md5Hash("123456" , "wangwu" , 3).toString());
    }
}
