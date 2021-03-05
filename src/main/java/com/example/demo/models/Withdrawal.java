package com.example.demo.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Withdrawal  {
    boolean domestic;
    String id;
    String iban;
    State state;
    Long price;
    List<Money> money;


    public Withdrawal(boolean domestic, String id, String iban, State state, Long price, List money) {
        this.domestic = domestic;
        this.id = id;
        this.iban = iban;
        this.state = state;
        this.price = price;
        this.money = money;
    }

    public List<Money> getMoney() {
        return money;
    }

    public void setMoney( List<Money> money) {
        this.money = money;
    }



    public boolean isDomestic() {
        return domestic;
    }

    public void setDomestic(boolean domestic) {
        this.domestic = domestic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }




}
