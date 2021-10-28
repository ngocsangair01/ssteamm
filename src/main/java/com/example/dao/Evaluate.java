package com.example.dao;

import javax.persistence.*;

@Entity
@Table(name = "evaluates")
public class Evaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEvaluate")
    private Integer idEvaluate;
    private String content;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idGame")
    private Game game;

    public Evaluate() {
    }

    public Evaluate(Integer idEvaluate, String content, User user, Game game) {
        this.idEvaluate = idEvaluate;
        this.content = content;
        this.user = user;
        this.game = game;
    }



    public Integer getIdEvaluate() {
        return idEvaluate;
    }

    public void setIdEvaluate(Integer idEvaluate) {
        this.idEvaluate = idEvaluate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
