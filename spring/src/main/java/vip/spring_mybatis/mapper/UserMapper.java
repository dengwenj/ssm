package vip.spring_mybatis.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import vip.spring_mybatis.pojo.User;

import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from user")
    List<User> selectAll();
}
