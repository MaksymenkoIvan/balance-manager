package org.example;

import io.javalin.Javalin;

public class App {

    public static UserController userController = new UserController();

    public static void main( String[] args )
    {
        Javalin javalin = Javalin.create().start(3022);

        javalin.get("/userBalance", ctx ->{
            userController.userController(ctx);
        });
    }
}
