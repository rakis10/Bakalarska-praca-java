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
<<<<<<< HEAD
    public List<Money> money;


    public Withdrawal(boolean domestic, String id, String iban, State state, Long price, List<Money> money) {
=======
    List<Money> money;


    public Withdrawal(boolean domestic, String id, String iban, State state, Long price, List money) {
>>>>>>> a2d3a12789ba29657ba2891d845f967e34c492e9
        this.domestic = domestic;
        this.id = id;
        this.iban = iban;
        this.state = state;
        this.price = price;
        this.money = money;
    }

    public List<Money> getMoney() {
<<<<<<< HEAD
        return this.money;
=======
        return money;
>>>>>>> a2d3a12789ba29657ba2891d845f967e34c492e9
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
