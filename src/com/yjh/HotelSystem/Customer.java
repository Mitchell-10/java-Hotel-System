package com.yjh.HotelSystem;

class Customer extends User {
    double balance;
    boolean isMember;

    public Customer(String username, String password, boolean isMember, double balance) {
        super(username, password, "customer");
        this.isMember = isMember;
        this.balance = balance;
    }
}

