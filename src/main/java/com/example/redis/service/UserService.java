package com.example.redis.service;


import com.example.redis.dao.UserDao;
import com.example.redis.domain.UserEntity;
import com.example.redis.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserEntity addNewUserService(UserDTO user){
        return userDao.addNewUser(user);
    }

    public UserDTO findUserByIdService(Long id) {
        return userDao.findUserByIdDao(id);
    }

    public UserDTO findUserByUsernameService(String username) {
        return userDao.findUserByUsernameDao(username);
    }
}
