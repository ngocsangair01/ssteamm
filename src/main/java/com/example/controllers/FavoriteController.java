package com.example.controllers;


import com.example.dao.Game;
import com.example.dao.User;
import com.example.repositories.GameRepository;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/favorites")
@CrossOrigin(origins = "*")
public class FavoriteController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @PostMapping("/{idUser}/{idGame}")
    public ResponseEntity<?> createFavorite(@PathVariable("idUser")Integer idUser, @PathVariable("idGame")Integer idGame){
        try {
            User user = userRepository.findById(idUser).get();
            Game game = gameRepository.findById(idGame).get();
            Set<Game> favoriteGames = user.getGameFavorite();
            favoriteGames.add(game);
            Set<User> userLikeGame = game.getUserLikeGame();
            userLikeGame.add(user);
            userRepository.save(user);
            gameRepository.save(game);
            return ResponseEntity.status(200).body("add success");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("add fail");
        }
    }
//    @DeleteMapping("/{idUser}/{idGame}")
//    public ResponseEntity<?> deleteFavorite(@PathVariable("idUser")Integer idUser, @PathVariable("idGame")Integer idGame){
//        try {
//            User user = userRepository.findById(idUser).get();
//            Game game = gameRepository.findById(idGame).get();
//            Set<Game> favoriteGames = user.getGameFavorite();
//            favoriteGames.remove(game);
//            return ResponseEntity.status(200).body(favoriteGames);
////            Set<User> userLikeGame = game.getUserLikeGame();
////            userLikeGame.remove(user);
////            user.setGameFavorite(favoriteGames);
////            game.setUserLikeGame(userLikeGame);
////            userRepository.save(user);
////            gameRepository.save(game);
////            System.out.println(userRepository.save(user));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("remove fail");
//        }

//    }



}
