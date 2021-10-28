package com.example.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Integer idUser;

    @Column(unique = true)
    private String account;
    private String name;
    private String password;
    private String phone;
    private String dateOfBirth;
    private String avatar;
    private String gmail;
    private String role;

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Game> games;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Evaluate> evaluates;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Invoice> invoices;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "favorite",
    joinColumns = @JoinColumn(name = "idUser", referencedColumnName = "idUser"),
    inverseJoinColumns = @JoinColumn(name = "idGame",referencedColumnName = "idGame"))
    private Set<Game> gameFavorite;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "cart",
    joinColumns = @JoinColumn(name = "idUser",referencedColumnName = "idUser"),
    inverseJoinColumns = @JoinColumn(name = "idGame",referencedColumnName = "idGame"))
    private Set<Game> cartOfUser;

    public User() {
    }

    public User(Integer idUser, String account, String name, String password, String phone, String dateOfBirth, String avatar, String gmail, String role, Set<Game> games, Set<Evaluate> evaluates, Set<Invoice> invoices, Set<Game> gameFavorite, Set<Game> cartOfUser) {
        this.idUser = idUser;
        this.account = account;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.avatar = avatar;
        this.gmail = gmail;
        this.role = role;
        this.games = games;
        this.evaluates = evaluates;
        this.invoices = invoices;
        this.gameFavorite = gameFavorite;
        this.cartOfUser = cartOfUser;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Set<Evaluate> getEvaluates() {
        return evaluates;
    }

    public void setEvaluates(Set<Evaluate> evaluates) {
        this.evaluates = evaluates;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Set<Game> getGameFavorite() {
        return gameFavorite;
    }

    public void setGameFavorite(Set<Game> gameFavorite) {
        this.gameFavorite = gameFavorite;
    }

    public Set<Game> getCartOfUser() {
        return cartOfUser;
    }

    public void setCartOfUser(Set<Game> cartOfUser) {
        this.cartOfUser = cartOfUser;
    }
}
