package com.example.sparkcreditmanagementapp;

public class UserDetails {
    int id,flag,idr;
    String name,email;
    double amount,credit;

    public UserDetails(int id, String name, String email, double amount,int flag,double credit,int idr) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.amount = amount;
        this.flag = flag;
        this.credit = credit;
        this.idr = idr;
    }

    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
