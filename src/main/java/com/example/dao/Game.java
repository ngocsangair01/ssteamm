package com.example.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.util.buf.UEncoder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGame")
    private Integer idGame;

//    @Column(unique = true)
    private String name;
    private String price;
    private String date;
    private String description;
    private String avatar;
    private String category;
    private String link;


    @ManyToOne()
    @JoinColumn(name = "idUser")
//    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "game",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Invoice> invoices;

    @OneToMany(mappedBy = "game",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Evaluate> evaluates;

    @ManyToMany(mappedBy = "gameFavorite")
    @JsonIgnore
    private Set<User> userLikeGame;

    @ManyToMany(mappedBy = "cartOfUser")
    @JsonIgnore
    private Set<User> gameOfUser;

    public Game() {
    }

    public Game(Integer idGame, String name, String price, String date, String description, String avatar, String category, String link, User user, Set<Invoice> invoices, Set<Evaluate> evaluates, Set<User> userLikeGame, Set<User> gameOfUser) {
        this.idGame = idGame;
        this.name = name;
        this.price = price;
        this.date = date;
        this.description = description;
        this.avatar = avatar;
        this.category = category;
        this.link = link;
        this.user = user;
        this.invoices = invoices;
        this.evaluates = evaluates;
        this.userLikeGame = userLikeGame;
        this.gameOfUser = gameOfUser;
    }

    public Game(String name, String price, String date, String description, String avatar, String category, String link, User user) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.description = description;
        this.avatar = avatar;
        this.category = category;
        this.link = link;
        this.user = user;
    }

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Set<Evaluate> getEvaluates() {
        return evaluates;
    }

    public void setEvaluates(Set<Evaluate> evaluates) {
        this.evaluates = evaluates;
    }

    public Set<User> getUserLikeGame() {
        return userLikeGame;
    }

    public void setUserLikeGame(Set<User> userLikeGame) {
        this.userLikeGame = userLikeGame;
    }

    public Set<User> getGameOfUser() {
        return gameOfUser;
    }

    public void setGameOfUser(Set<User> gameOfUser) {
        this.gameOfUser = gameOfUser;
    }
}
