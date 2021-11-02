package org.example;

public class User {

    private Data data = new Data();
    public String userName;
    public int id;
    public double balance;

    public User(int id, String userName, double balance){
        this.id = id;
        this.userName = userName;
        this.balance = balance;
    }
}
