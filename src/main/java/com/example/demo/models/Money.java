package com.example.demo.models;

public class Money {
    CashType cashType;
    int pieces;
    int value;

<<<<<<< HEAD

=======
>>>>>>> a2d3a12789ba29657ba2891d845f967e34c492e9
    public Money(CashType cashType, int pieces, int value) {
        this.cashType = cashType;
        this.pieces = pieces;
        this.value = value;
    }

    public CashType getCashType() {
        return cashType;
    }

    public void setCashType(CashType cashType) {
        this.cashType = cashType;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
