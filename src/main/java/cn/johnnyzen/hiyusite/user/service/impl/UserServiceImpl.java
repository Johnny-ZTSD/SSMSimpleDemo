package cn.johnnyzen.hiyusite.user.service.impl;

import cn.johnnyzen.hiyusite.user.domain.User;
import cn.johnnyzen.hiyusite.user.mapper.UserMapper;
import cn.johnnyzen.hiyusite.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;


/**
 * @orgnization: 成都四方伟业软件股份有限公司
 * @project: SSMDemo2
 * @author: 千千寰宇
 * @date: 2020/6/5  17:22:37
 * @description: ...
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(Integer userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public List<Map<String, User>> findAll() throws Exception {
        return userMapper.getAllUsers();
    }
}
