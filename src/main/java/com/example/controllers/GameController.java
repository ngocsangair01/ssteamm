package com.example.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.dao.Game;
import com.example.dao.User;
import com.example.dto.GameDTO;
import com.example.exceptions.DuplicateException;
import com.example.exceptions.NotFoundException;
import com.example.repositories.GameRepository;
import com.example.repositories.UserRepository;
import com.example.services.GameService;
import com.example.services.UserService;
import com.example.utils.ConvertObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*")
public class GameController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    //get all game
    @GetMapping()
    public ResponseEntity<?> findAllGame() {
        return ResponseEntity.status(200).body(gameRepository.findAll());
    }

    //find game of creator
    @GetMapping("/created/{idUser}")
    public ResponseEntity<?> getAllGameByIdCreator(@PathVariable(name = "idUser")Integer idUser){
        Optional<User> user = userRepository.findById(idUser);
        if (user.isEmpty()) {
            throw new NotFoundException("Does not have any account");
        }
        System.out.println(user.get().toString());
        Set<Game> games = gameRepository.findAllByUser(user.get());
        if (games.size() == 0) {
            throw new NotFoundException("Does not have any game of creator");
        }
        return ResponseEntity.status(200).body(games);
    }

    @GetMapping("/{idGame}")
    public ResponseEntity<?> getGameById(@PathVariable(name = "idGame")Integer idGame) {
        Optional<Game> game = gameRepository.findById(idGame);
        if (game.isEmpty()){
            throw new NotFoundException("Game does not exist");
        }
        return ResponseEntity.status(200).body(game.get());
    }

    //post game
    @PostMapping("/{idUser}")
    public ResponseEntity<?> createGame(@RequestBody GameDTO gameDTO,@PathVariable(name = "idUser")Integer idUser) throws IOException {
        Optional<User> user0 = userRepository.findById(idUser);
        if (user0.isEmpty()){
            throw new NotFoundException("Does not have any account");
        }
        if (gameRepository.findByName(gameDTO.getName()) != null) {
            throw new DuplicateException("Game is already exists");
        }

        Game game = new Game(gameDTO.getName(),gameDTO.getPrice(),gameDTO.getDate(),gameDTO.getDescription(), gameDTO.getAvatar(), gameDTO.getCategory(),gameDTO.getLink(),user0.get());
        user0.get().getGames().add(game);
        Game game1 = gameRepository.save(game);

        return ResponseEntity.status(201).body(game1);
    }

    //edit game by admin
    @PatchMapping("/{idUser}/{idGame}")
    public ResponseEntity<?> editGame(@RequestBody GameDTO gameDTO,@PathVariable("idUser")Integer idUser, @PathVariable("idGame")Integer idGame){
        Optional<Game> game = gameRepository.findById(idGame);
        if (game.isEmpty()) {
            throw new NotFoundException("Have no game");
         }
        if (game.get().getUser().getIdUser() != idUser){
            throw new NotFoundException("Have no user");
        }
        ConvertObject.fromGameDTOToGame(game.get(),gameDTO);
        return ResponseEntity.status(201).body(gameRepository.save(game.get()));
    }

    @DeleteMapping("/{idUser}/{idGame}")
    public ResponseEntity<?> deleteGame(@PathVariable("idUser")Integer idUser,@PathVariable("idGame") Integer idGame){
        Optional<Game> game = gameRepository.findById(idGame);
        if (game.isEmpty()){
            throw new NotFoundException("Have no game");
        }
        if (game.get().getUser().getIdUser() != idUser){
            throw new NotFoundException("Have no user");
        }
        gameRepository.deleteById(idGame);
        return ResponseEntity.status(200).body("Success");
    }
}
