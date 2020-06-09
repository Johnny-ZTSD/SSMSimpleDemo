package cn.johnnyzen.hiyusite.user.service;

import cn.johnnyzen.hiyusite.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface UserService {
    User findUserById(Integer userId);

    List<Map<String, User>> findAll() throws Exception;
}
