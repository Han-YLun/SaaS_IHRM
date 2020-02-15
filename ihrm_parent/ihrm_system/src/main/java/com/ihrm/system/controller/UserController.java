package com.ihrm.system.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.PageResult;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import com.ihrm.common.poi.ExcelImportUtil;
import com.ihrm.common.utils.JwtUtils;
import com.ihrm.common.utils.PermissionConstants;
import com.ihrm.domain.system.Permission;
import com.ihrm.domain.system.Role;
import com.ihrm.domain.system.User;
import com.ihrm.domain.system.response.ProfileResult;
import com.ihrm.domain.system.response.UserResult;
import com.ihrm.system.service.PermissionService;
import com.ihrm.system.service.UserService;
import com.mysql.jdbc.profiler.ProfilerEvent;
import io.jsonwebtoken.Claims;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hyl
 * @date: 2020/01/09
 **/
//解决跨域
@CrossOrigin
@RestController
@RequestMapping(value = "/sys")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private JwtUtils jwtUtils;

    @RequestMapping("/user/upload/{id}")
    public Result upload(@PathVariable String id , @RequestParam(name = "file") MultipartFile file) throws Exception {
        //1.调用service保存图片
        String imgUrl = userService.uploadImage(id , file);
        //2.返回数据
        return new Result(ResultCode.SUCCESS , imgUrl);
        
    }


    /**
     * 导入Excel,添加用户
     */
    @RequestMapping(value = "/user/import" , method = RequestMethod.POST)
    public Result importUser(@RequestParam(name = "file") MultipartFile file) throws Exception {
        //1.解析excel
        //1.1根据Excel文件创建工作簿
/*        Workbook wb = new XSSFWorkbook(file.getInputStream());
        //1.2获取sheet
        Sheet sheet = wb.getSheetAt(0);
        //1.3获取sheet中的每一行和每一个单元格
        //2.获取用户数据列表
        List<User> list = new ArrayList<>();
        for (int rowNumber = 1; rowNumber <= sheet.getLastRowNum(); rowNumber++) {
            Row row = sheet.getRow(rowNumber);//根据索引获取每一行
            Object[] values = new Object[row.getLastCellNum()];
            for (int celNum = 1; celNum < row.getLastCellNum(); celNum++){
                Cell cell = row.getCell(celNum);
                Object value = getCellValue(cell);
                values[celNum] = value;
            }
            User user = new User(values);
            list.add(user);

        }*/
        List<User> list = new ExcelImportUtil(User.class).readExcel(file.getInputStream(), 1, 1);
        //3.批量保存用户
        userService.saveAll(list , companyId , companyName);
        return new Result(ResultCode.SUCCESS);
    }


    public static Object getCellValue(Cell cell){
        //获取到单元格的属性类型
        CellType cellType = cell.getCellType();
        //根据单元格数据类型获取数据
        Object value = null;
        switch (cellType){
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BLANK:
                value = cell.getBooleanCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)){
                    //日期格式
                    value = cell.getDateCellValue();
                }else{
                    //数字
                    value = cell.getNumericCellValue();
                }
                break;
            case FORMULA:   //公式
                value = cell.getCellFormula();
                break;
            default:
                break;
        }
        return value;
    }



    /**
     * 分配角色
     */
    @RequestMapping(value = "/user/assignRoles" , method = RequestMethod.PUT)
    public Result save(@RequestBody Map<String,Object> map){

        //获取被分配的用户id
        String userId = (String) map.get("id");
        //获取到角色的id列表
        List<String> roleIds = (List<String>) map.get("roleIds");
        //调用service完成角色分配
        userService.assignRoles(userId , roleIds);

        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 保存
     * @return
     */
    @RequestMapping(value = "/user" , method = RequestMethod.POST)
    public Result save(@RequestBody User user){
        //设置保存的用户id
        user.setCompanyId(companyId);
        user.setCompanyName(companyName);
        //调用service完成保存用户
        userService.save(user);
        //构造返回结果
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询用户列表
     * @return
     */
    @RequestMapping(value = "/user" , method = RequestMethod.GET)
    public Result findAll(int page, int size, @RequestParam() Map map){

        //获取当前的企业id
        map.put("companyId" , companyId);

        Page<User> pageUser = userService.findAll(map, page, size);
        //构造返回结果
        PageResult<User> pageResult = new PageResult<>(pageUser.getTotalElements(),pageUser.getContent());
        return new Result(ResultCode.SUCCESS , pageResult);
    }


    /**
     * 根据Id查询
     */
    @RequestMapping(value = "/user/{id}" , method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "id") String id){
        //添加roleIds(用户已经具有的角色id数组)
        User user = userService.findById(id);
        UserResult userResult = new UserResult(user);
        return new Result(ResultCode.SUCCESS , userResult);
    }

    /**
     * 修改User
     */
    @RequestMapping(value = "/user/{id}" , method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id , @RequestBody User user){
        //设置修改的用户Id
        user.setId(id);
        //调用Service更新
        userService.update(user);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据Id删除
     */
    @RequiresPermissions(value = "API-USER-DELETE")
    @RequestMapping(value = "/user/{id}" , method = RequestMethod.DELETE , name = "API-USER-DELETE")
    public Result delete(@PathVariable(value = "id") String id){
        userService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public Result login(@RequestBody Map<String,Object> loginMap){
        String mobile = (String) loginMap.get("mobile");
        String password = (String) loginMap.get("password");

        try {
            //构造登录令牌
            password = new Md5Hash(password , mobile , 3).toString();
            UsernamePasswordToken upToken = new UsernamePasswordToken(mobile , password);
            //获取subject
            Subject subject = SecurityUtils.getSubject();
            //调用login方法,进入realm完成认证
            subject.login(upToken);
            //获取sessionId
            String sessionId = (String) subject.getSession().getId();
            //构造返回结果
            return new Result(ResultCode.SUCCESS , sessionId);
        }catch (Exception e){
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        }



  /*      User user = userService.findByMobile(mobile);

        //登录失败
        if (user == null || !user.getPassword().equals(password)){
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        }else {
            //登录成功
            //API权限字符串
            StringBuilder sb = new StringBuilder();
            //获取到所有的可访问API权限
            for (Role role : user.getRoles()) {
                for (Permission perm : role.getPermissions()) {
                    if (perm.getType() == PermissionConstants.PY_API){
                        sb.append(perm.getCode()).append(",");
                    }
                }
            }
            Map<String, Object> map = new HashMap<>();
            map.put("apis" , sb.toString());//可访问的API权限字符串
            map.put("companyId" , user.getCompanyId());
            map.put("companyName" , user.getCompanyName());
            String token = jwtUtils.createJwt(user.getId(), user.getUsername(), map);
            return new Result(ResultCode.SUCCESS,token);
        }*/
    }

    /**
     * 用户登录成功之后,获取用户信息
     */
    @RequestMapping(value = "/profile" , method = RequestMethod.POST)
    public Result profile(HttpServletRequest request) throws Exception {

        //获取session中的安全数据
        Subject subject = SecurityUtils.getSubject();
        //subject获取所有的安全集合
        PrincipalCollection principals = subject.getPrincipals();
        //获取安全数据
        ProfileResult result = (ProfileResult) principals.getPrimaryPrincipal();

/*
        String userId = claims.getId();
        //获取用户信息
        User user = userService.findById(userId);
        //根据不同的用户级别获取用户权限
        ProfileResult result = null;

        if ("user".equals(user.getLevel())){
            result = new ProfileResult(user);
        }else{
            Map map = new HashMap();
            if ("coAdmin".equals(user.getLevel())){
                map.put("enVisible" , "1");
            }
            List<Permission> list = permissionService.findAll(map);
            result = new ProfileResult(user , list);
        }
*/

        return new Result(ResultCode.SUCCESS,result);
    }

}
