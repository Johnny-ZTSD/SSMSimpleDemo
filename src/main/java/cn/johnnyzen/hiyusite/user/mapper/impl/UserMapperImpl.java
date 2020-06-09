package cn.johnnyzen.hiyusite.user.mapper.impl;

import cn.johnnyzen.hiyusite.user.domain.User;
import cn.johnnyzen.hiyusite.user.mapper.UserMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @orgnization: 成都四方伟业软件股份有限公司
 * @project: SSMDemo2
 * @author: 千千寰宇
 * @date: 2020/6/5  17:12:26
 * @description: ...
 */

public class UserMapperImpl implements UserMapper {
    @Autowired
    private SqlSessionTemplate sqlSession;

/*    @Override
    public int saveUser(User user) {
        return sqlSession.insert("cn.johnnyzen.hiyusite.user.mapper.IUserMapper.saveUser", user);
    }*/

    @Override
    public User findUserById(Integer userId) {
        return sqlSession.getMapper(UserMapper.class).findUserById(userId);
    }

    @Override
    public List<Map<String, User>> getAllUsers() {
        return sqlSession.getMapper(UserMapper.class).getAllUsers();
    }

    @Override
    public int countTotalListUsers() {
        return sqlSession.getMapper(UserMapper.class).countTotalListUsers();
    }

    @Override
    public List<User> listUsers(int offset, int pageSize) {
        return sqlSession.getMapper(UserMapper.class).listUsers(offset, pageSize);
    }

    public SqlSessionTemplate getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }
}
