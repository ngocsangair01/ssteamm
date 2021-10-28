package com.example.repositories;

import com.example.dao.Game;
import com.example.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Set<Game> findAllByUser(User user);
    Game findByName(String name);
}
