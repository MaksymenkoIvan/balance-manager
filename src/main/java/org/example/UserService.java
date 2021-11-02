package org.example;

public class UserService {

    Data data = new Data();

    public double getUserBalance(int id){
        User user = new User(id, data.getUserName(id), data.getBalance(id));
        return user.balance;
    }

    public String getName(int id){
        User user = new User(id, data.getUserName(id), data.getBalance(id));
        return user.userName;
    }
}
