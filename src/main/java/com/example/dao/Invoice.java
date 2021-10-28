package com.example.dao;

import javax.persistence.*;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInvoice")
    private Integer idInvoice;

    private String achivement;
    private String dayBought;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idGame")
    private Game game;

    public Invoice() {
    }

    public Invoice(Integer idInvoice, String achivement, String dayBought, User user, Game game) {
        this.idInvoice = idInvoice;
        this.achivement = achivement;
        this.dayBought = dayBought;
        this.user = user;
        this.game = game;
    }

    public Integer getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Integer idInvoice) {
        this.idInvoice = idInvoice;
    }

    public String getAchivement() {
        return achivement;
    }

    public void setAchivement(String achivement) {
        this.achivement = achivement;
    }

    public String getDayBought() {
        return dayBought;
    }

    public void setDayBought(String dayBought) {
        this.dayBought = dayBought;
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
