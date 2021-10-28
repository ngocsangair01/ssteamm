package com.example.dto;

public class InvoiceDTO {
    private String achivement;
    private String dayBought;

    public InvoiceDTO() {
    }

    public InvoiceDTO(String achivement, String dayBought) {
        this.achivement = achivement;
        this.dayBought = dayBought;
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
}
