package com.ihrm.system.service;

import com.ihrm.common.utils.IdWorker;
import com.ihrm.common.utils.QiniuUploadUtil;
import com.ihrm.domain.company.Department;
import com.ihrm.domain.system.Role;
import com.ihrm.domain.system.User;
import com.ihrm.system.client.CompanyFeignClient;
import com.ihrm.system.dao.UserDao;
import com.ihrm.system.dao.RoleDao;
import com.ihrm.system.utils.BaiduAiUtil;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * @author: hyl
 * @date: 2020/01/09
 **/
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CompanyFeignClient companyFeignClient;

    @Autowired
    private BaiduAiUtil baiduAiUtil;


    /**
     * 根据mobile查询用户
     */
    public User findByMobile(String mobile){
        return userDao.findByMobile(mobile);
    }

    /**
     * 保存用户
     */
    public void save(User user){
        //设置主键
        String id = idWorker.nextId() + "";
        //md5加密密码
        String password = new Md5Hash("123456", user.getMobile(), 3).toString();
        user.setLevel("user");
        user.setPassword(password);//设置初始密码
        user.setEnableState(1);
        user.setId(id);
        //调用dao保存用户
        userDao.save(user);
    }

    /**
     * 更新用户
     */
    public void update(String id , User user){
        User tempUser = userDao.findById(id).get();
        if (!ObjectUtils.isEmpty(tempUser) && !ObjectUtils.isEmpty(user)){
            tempUser.setUsername(user.getUsername());
            System.out.println(user.getPassword());
            tempUser.setPassword(user.getPassword());
            tempUser.setWorkNumber(user.getWorkNumber());
            tempUser.setTimeOfEntry(user.getTimeOfEntry());
        }
        //更新用户
        userDao.save(tempUser);
    }


    /**
     * 根据id查询用户
     */
    public User findById(String id){
        return userDao.findById(id).get();
    }


    /**
     * 查询全部用户列表
     */
    public Page<User> findAll(Map<String,Object> map, int page, int size){

        //查询条件
        Specification<User> spec = new Specification<User>() {

            /**
             * 动态拼接查询条件
             * @param root
             * @param query
             * @param cb
             * @return
             */
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();

                //根据请求的companyId是否为空构造查询条件
                if (!StringUtils.isEmpty(map.get("companyId"))){
                     list.add(cb.equal(root.get("companyId").as(String.class) , (String)map.get("companyId")));
                }

                //根据请求的部门id构造查询条件
                if (!StringUtils.isEmpty(map.get("departmentId"))){
                    list.add(cb.equal(root.get("departmentId").as(String.class) , (String)map.get("departmentId")));
                }

                //根据请求的hasDept进行判断
                if (!StringUtils.isEmpty(map.get("hasDept"))){
                    if ("0".equals((String) map.get("hasDept")))   {
                        list.add(cb.isNull(root.get("departmentId")));
                    }else{
                        list.add(cb.isNotNull(root.get("departmentId")));
                    }
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };

        //分页
        Page<User> pageUser = userDao.findAll(spec, new PageRequest(page-1, size));
        
        return pageUser;
    }

    /**
     * 根据id删除用户
     */
    public void deleteById(String id){
        userDao.deleteById(id);
    }

    /**
     * 分配角色
     * @param userId    用户id
     * @param roleIds   要分配的角色id
     */
    public void assignRoles(String userId, List<String> roleIds) {
        //根据id查询用户
        User user = userDao.findById(userId).get();
        //设置用户的角色集合
        Set<Role> roles = new HashSet<>();
        for (String roleId : roleIds) {
            Role role = roleDao.findById(roleId).get();
            roles.add(role);
        }
        //设置用户和角色集合的关系
        user.setRoles(roles);
        //更新用户
        userDao.save(user);
    }

    /**
     *  批量用户保存
     * @param list  用户list
     * @param companyId 用户所属公司id
     * @param companyName   用户所属公司名称
     */
    @Transactional
    public void saveAll(List<User> list, String companyId, String companyName) {
        for (User user : list) {
            //默认密码
            user.setPassword(new Md5Hash("123456" , user.getMobile() , 3).toString());
            //id
            user.setId(idWorker.nextId() + "");
            //基本属性
            user.setCompanyId(companyId);
            user.setCompanyName(companyName);
            user.setInServiceStatus(1);
            user.setEnableState(1);
            user.setLevel("user");

            //填充部门的属性
            Department department = companyFeignClient.findByCode(user.getDepartmentId(), companyId);
            if (department != null){
                user.setDepartmentId(department.getId());
                user.setDepartmentName(department.getName());
            }

            userDao.save(user);
        }
    }

    /**
     *  完成图片处理 (上传到七牛云存储并且注册到百度云AI人脸库中)
     * @param id    用户id
     * @param file  用户上传的头像文件
     * @return      请求路径
     */
    public String uploadImage(String id, MultipartFile file) throws Exception {
        //1.根据id查询用户
        User user = userDao.findById(id).get();
        //2.根据图片上传到七牛云存储,获取到请求路径
        String imgUrl = new QiniuUploadUtil().upload(user.getId(), file.getBytes());
        //3.更新用户头像地址
        user.setStaffPhoto(imgUrl);
        userDao.save(user);
        //判断是否已经注册面部信息
        Boolean faceExist = baiduAiUtil.faceExist(id);
        String imgBase64 = Base64.encode(file.getBytes());
        if (faceExist){
            //更新
            baiduAiUtil.faceUpdate(id , imgBase64);
        }else{
            //注册
            baiduAiUtil.faceRegister(id , imgBase64);
        }
        //4.返回路径
        return imgUrl;
    }
}

