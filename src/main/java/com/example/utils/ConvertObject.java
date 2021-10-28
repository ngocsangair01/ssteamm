package com.example.utils;

import com.example.dao.Evaluate;
import com.example.dao.Game;
import com.example.dao.Invoice;
import com.example.dao.User;
import com.example.dto.EvaluateDTO;
import com.example.dto.GameDTO;
import com.example.dto.InvoiceDTO;
import com.example.dto.UserDTO;

public class ConvertObject {
    public static  void fromGameDTOToGame(Game game,GameDTO gameDTO) {
        if (gameDTO.getName() !=null) {
            game.setName(gameDTO.getName());
        }
        if (gameDTO.getAvatar() != null){
            game.setAvatar(gameDTO.getAvatar());
        }
        if (gameDTO.getCategory() != null) {
            game.setCategory(gameDTO.getCategory());
        }
        if (gameDTO.getDate() != null){
            game.setDate(gameDTO.getDate());
        }
        if (gameDTO.getDescription() != null){
            game.setDescription(gameDTO.getDescription());
        }
        if (gameDTO.getLink() != null) {
            game.setLink(gameDTO.getLink());
        }
        if (gameDTO.getPrice() != null){
            game.setPrice(gameDTO.getPrice());
        }
    }
    public static User fromUserDTOToUser(UserDTO userDTO){
        User user = new User();
        if (userDTO.getAccount() != null) {
            user.setAccount(userDTO.getAccount());
        }
        if (userDTO.getName() != null){
            user.setName(userDTO.getName());
        }
        if (userDTO.getPassword() != null) {
            user.setPassword(userDTO.getPassword());
        }
        if (userDTO.getAvatar() != null) {
            user.setAvatar(userDTO.getAvatar());
        }
        if (userDTO.getGmail() != null) {
            user.setGmail(userDTO.getGmail());
        }
        if (userDTO.getDateOfBirth() != null){
            user.setDateOfBirth(userDTO.getDateOfBirth());
        }
        if (userDTO.getPhone() != null) {
            user.setPhone(userDTO.getPhone());
        }
        if (userDTO.getRole() != null) {
            user.setRole(userDTO.getRole());
        }
        return user;
    }
    public static Evaluate fromEvaluateDTOToEvaluate (EvaluateDTO evaluateDTO) {
        Evaluate evaluate = new Evaluate();
        if (evaluateDTO.getContent() != null) {
            evaluate.setContent(evaluateDTO.getContent());
        }
        return evaluate;
    }
    public static Invoice fromInvoiceDTOToInvoice(InvoiceDTO invoiceDTO){
        Invoice invoice = new Invoice();
        if (invoiceDTO.getAchivement() != null){
            invoice.setAchivement(invoiceDTO.getAchivement());
        }
        if (invoiceDTO.getDayBought() != null) {
            invoice.setDayBought(invoiceDTO.getDayBought());
        }
        return invoice;
    }
}
