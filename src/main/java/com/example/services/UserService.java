package com.example.services;

import com.example.dao.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> findAll() throws Exception{
        List<User> list = userRepository.findAll();
        if (list.size() == 0 ){
            throw new Exception("empty");
        }
        return list;
    }
}
