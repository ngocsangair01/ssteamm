package com.example.controllers;


import com.example.dao.Evaluate;
import com.example.dto.EvaluateDTO;
import com.example.exceptions.NotFoundException;
import com.example.repositories.EvaluateRepository;
import com.example.repositories.GameRepository;
import com.example.repositories.UserRepository;
import com.example.utils.ConvertObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/evaluates")
@CrossOrigin(origins = "*")
public class EvaluateController {
    @Autowired
    private EvaluateRepository evaluateRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    //get all evaluate
    @GetMapping("/{idGame}")
    public ResponseEntity<?> getEvaluateByIdGame(@PathVariable("idGame")Integer idGame){
        List<Evaluate> evaluates = evaluateRepository.findAll();
        List<Evaluate> evaluateList = evaluates.stream().filter(item -> {
            return Objects.equals(item.getGame().getIdGame(), idGame);
        }).collect(Collectors.toList());
        return ResponseEntity.status(200).body(evaluateList);
    }

    @PostMapping("/{idGame}/{idUser}")
    public ResponseEntity<?> createNewEvaluate(@PathVariable("idGame")Integer idGame, @PathVariable("idUser")Integer idUser, @RequestBody EvaluateDTO evaluateDTO){
        Evaluate evaluate = new Evaluate();
        evaluate.setContent(evaluateDTO.getContent());
        evaluate.setGame(gameRepository.findById(idGame).get());
        evaluate.setUser(userRepository.findById(idUser).get());
        return ResponseEntity.status(201).body(evaluateRepository.save(evaluate));
    }

    @PatchMapping("/{idEvaluate}")
    public ResponseEntity<?> editEvaluateById(@PathVariable("idEvaluate")Integer idEvaluate,@RequestBody EvaluateDTO evaluateDTO){
        Optional<Evaluate> evaluate = evaluateRepository.findById(idEvaluate);
        Evaluate newEvaluate = ConvertObject.fromEvaluateDTOToEvaluate(evaluateDTO);
        newEvaluate.setIdEvaluate(evaluate.get().getIdEvaluate());
        newEvaluate.setGame(evaluate.get().getGame());
        newEvaluate.setUser(evaluate.get().getUser());
        return ResponseEntity.status(200).body(evaluateRepository.save(newEvaluate));
    }

    @DeleteMapping("/{idEvaluate}")
    public ResponseEntity<?> deleteEvaluateById(@PathVariable("idEvaluate")Integer idEvaluate){
        Optional<Evaluate> evaluate = evaluateRepository.findById(idEvaluate);
        if (evaluate.isEmpty()){
            throw new NotFoundException("Evaluate does not exists");
        }
        evaluateRepository.deleteById(idEvaluate);
        return ResponseEntity.status(200).body(evaluateRepository.findAll());
    }
}
