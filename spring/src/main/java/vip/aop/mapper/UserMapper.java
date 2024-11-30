package vip.aop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import vip.spring_mybatis.pojo.User;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user;")
    List<User> findAll();

    @Select("select * from user where username = #{username} and password = #{password}")
    User findByUsernamePassword(@Param("username") String username, @Param("password") String password);
}
