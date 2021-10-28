package com.example.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.dao.User;
import com.example.dto.UserDTO;
import com.example.exceptions.DuplicateException;
import com.example.exceptions.NotFoundException;
import com.example.repositories.GameRepository;
import com.example.repositories.UserRepository;
import com.example.services.UserService;
import com.example.utils.ConvertObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public ResponseEntity<?> findAllUser() throws Exception{
        return ResponseEntity.status(200).body(userService.findAll());
    }
    @GetMapping("/{idUser}")
    public ResponseEntity<?> findUserById(@PathVariable("idUser")Integer idUser){
        Optional<User> user = userRepository.findById(idUser);
        if (user.isEmpty()){
            throw new NotFoundException("Does not have any account");
        }
        return ResponseEntity.status(200).body(user.get());
    }

    // create new user
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        User oldUser = userRepository.findByName(userDTO.getName());
        if (oldUser != null) {
            throw new DuplicateException("User already exists");
        }
        User user = new User();
        user.setAccount(userDTO.getAccount());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setGmail(userDTO.getGmail());
        user.setRole(userDTO.getRole());
        User newUser = userRepository.save(user);
        return ResponseEntity.status(201).body(newUser);
    }
    // sign in
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestParam("account")String account,
                                    @RequestParam("password")String password) {
        User user = userRepository.findByAccount(account);
        if (user == null){
            throw new NotFoundException("User does not exist");
        }
        if (!(user.getPassword().equals(password))){
            throw new NotFoundException("wrong password");
        }
        return ResponseEntity.status(200).body(user);
    }
    //Sử dụng state của reacjs( Redux)

    //update avt
    @PatchMapping("/avt/{idUser}")
    public ResponseEntity<?> updateAvt(@PathVariable(name = "idUser")Integer idUser, @RequestParam("avatar")MultipartFile file) throws IOException {
        Optional<User> user = userRepository.findById(idUser);
        if (user == null) {
            throw new NotFoundException("Id does not exists");
        }
        Map<?, ?> map = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String link = map.get("secure_url").toString();
        user.get().setAvatar(link);
        User newUser = userRepository.save(user.get());
        return ResponseEntity.status(201).body(newUser);
    }
    // edit user by id
    @PatchMapping("/{idUser}")
    public ResponseEntity<?> editUserById(@RequestBody UserDTO userDTO, @PathVariable(name = "idUser")Integer idUser){
        Optional<User> user = userRepository.findById(idUser);
        if (user.isEmpty()){
            throw new NotFoundException("have no user");
        }
        User newUser = ConvertObject.fromUserDTOToUser(userDTO);
        newUser.setIdUser(user.get().getIdUser());
        return ResponseEntity.status(201).body(userRepository.save(newUser));
    }

    // delete User
    @DeleteMapping("/{idUser}")
    public ResponseEntity<?> deleteUserById(@PathVariable(name = "idUser")Integer idUser){
        Optional<User> user = userRepository.findById(idUser);
        if (user.isEmpty()){
            throw new NotFoundException("User does not exists");
        }
        userRepository.deleteById(idUser);
        return ResponseEntity.status(200).body("delete success");
    }
}
