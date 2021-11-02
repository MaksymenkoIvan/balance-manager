package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Data extends  Config {
    private Scanner scanner = new Scanner(System.in);
    private Connection connection = null;
    private List<String> messages = new ArrayList<>();
    private StringBuilder string = new StringBuilder();

    public Data() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(HOST, USER, PASS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String connect(String login) {
        String result = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            PreparedStatement select =  connection.prepareStatement("SELECT pass FROM users WHERE login=? ");
            select.setString(1,login);
            ResultSet list = select.executeQuery();
            while (list.next()){
                result = list.getString(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;

    }

    public boolean register(String login, String pass, String repass){
        if (pass.equals(repass)){
            try {
                PreparedStatement insert  = connection.prepareStatement("INSERT INTO users (login, pass)" + "VALUES(?,?)");
                insert.setString(1,login);
                insert.setString(2,pass);
                insert.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }else{
            System.out.println("pass != re-pass");
            return false;
        }
    }

    public boolean auth(String username, String pass) {
        try {
            PreparedStatement select = connection.prepareStatement("SELECT pass FROM users WHERE login=?");
            select.setString(1, username);
            ResultSet set = select.executeQuery();
            while (set.next()){
                if (set.getString(1).equals(pass)){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public int getId(String login){
        int myid = 0;
        try{
            PreparedStatement select = connection.prepareStatement(
                    "SELECT id FROM users WHERE login = ?");
            select.setString(1, login);
            ResultSet set = select.executeQuery();
            while (set.next()){
                myid = set.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return myid;
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