package com.example.controllers;


import com.example.dao.Invoice;
import com.example.dto.InvoiceDTO;
import com.example.repositories.GameRepository;
import com.example.repositories.InvoiceRepository;
import com.example.repositories.UserRepository;
import com.example.utils.ConvertObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invoices")
@CrossOrigin(origins = "*")
public class InvoiceController {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameRepository gameRepository;

    @GetMapping("/{idUser}")
    public ResponseEntity<?> getAllInvoiceByIdUser(@PathVariable("idUser")Integer idUser) {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<Invoice> invoiceList = invoices.stream().filter(invoice ->{
            return Objects.equals(invoice.getUser().getIdUser(), idUser);
        }).collect(Collectors.toList());
        return ResponseEntity.status(200).body(invoiceList);
    }

    @PostMapping("/{idGame}/{idUser}")
    public ResponseEntity<?> createNewInvoice(@PathVariable("idGame")Integer idGame, @PathVariable("idUser")Integer idUser,@RequestBody InvoiceDTO invoiceDTO){
        Invoice invoice = ConvertObject.fromInvoiceDTOToInvoice(invoiceDTO);
        invoice.setGame(gameRepository.findById(idGame).get());
        invoice.setUser(userRepository.findById(idUser).get());
        return ResponseEntity.status(201).body(invoiceRepository.save(invoice));
    }
}
