package cn.johnnyzen.hiyusite.user.mapper;

import cn.johnnyzen.hiyusite.user.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {
/*    User getUserById(Integer id);*/

   /**
    * 为避免出现本异常：
    *       SqlSession[...] was not registered for synchronization because synchronization is not active错误
    *   解决方案: 变量(userId)的类型不能用原始类型，必须用封装类型
    * */
    @Select({"SELECT * FROM tb_user WHERE user_id = #{userId}"})
    User findUserById(@Param("userId")Integer userId);

/*    int saveUser(User user);*/

    List<Map<String, User>> getAllUsers();

 /*    List<User> getUsersByFuzzyQuery(User user);

    List<User> selectUserByWhere(User user);

    int updateUserBySet(User user);

    List<User> selectUsersByForeach(List<Integer> idList);

    List<User> selectUsersByBind(String email);*/

    int countTotalListUsers();
    List<User> listUsers(int offset, int pageSize);

}
