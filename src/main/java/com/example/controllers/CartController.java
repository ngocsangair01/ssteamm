package com.example.controllers;


import com.example.dao.Game;
import com.example.dao.User;
import com.example.repositories.GameRepository;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*")
public class CartController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    GameRepository gameRepository;


    @PostMapping("/{idUser}/{idGame}")
    public ResponseEntity<?> createCart(@PathVariable("idUser")Integer idUser, @PathVariable("idGame")Integer idGame){
        try {
            Game game = gameRepository.findById(idGame).get();
            User user = userRepository.findById(idUser).get();
            Set<Game> cartGame = user.getCartOfUser();
            cartGame.add(game);
            Set<User> gameOfUser = game.getGameOfUser();
            gameOfUser.add(user);
            gameRepository.save(game);
            userRepository.save(user);
            return ResponseEntity.status(200).body("create cart success");
        }catch (Exception e){
            return ResponseEntity.status(200).body("create cart fail");
        }

    }

}
