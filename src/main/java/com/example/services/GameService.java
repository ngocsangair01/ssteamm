package com.example.services;

import com.example.dao.Game;
import com.example.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    public List<Game> findAll() throws Exception{
        List<Game> list = gameRepository.findAll();
        if (list.size() == 0){
            throw new Exception("empty");
        }

        return list;
    }
}
