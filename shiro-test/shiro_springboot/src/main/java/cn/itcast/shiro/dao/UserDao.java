package cn.itcast.shiro.dao;



import cn.itcast.shiro.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
  * 用户数据访问接口
  */
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    //根据手机号获取用户信息
    User findByUsername(String name);
}