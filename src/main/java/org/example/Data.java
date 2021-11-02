package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Data extends  Config {
    private Connection connection = null;

    public Data() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(HOST, USER, PASS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getBalance(int id){
        double balance = 0;
        try{
            PreparedStatement select = connection.prepareStatement(
                    "SELECT balance FROM users WHERE id = ?");
            select.setString(1, String.valueOf(id));
            ResultSet set = select.executeQuery();
            while (set.next()){
                 balance = set.getDouble(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return balance;
    }

    public String getUserName(int id){
        String login = "";
        try{
            PreparedStatement select = connection.prepareStatement("SELECT login FROM users WHERE id = ?");
            select.setInt(1, id);
            ResultSet set = select.executeQuery();
            while (set.next()){
                login = set.getString(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return login;
    }
}