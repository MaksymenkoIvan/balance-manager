package org.example;

import io.javalin.http.Context;

public class UserController {

    public UserService userService = new UserService();

    public void userController(Context ctx){
        try {
            int id = Integer.parseInt(ctx.queryParam("id"));
            if (userService.getName(id).equals("")){
                ctx.json("Wrong id");
            }else {
                ctx.result(String.format("{\"balance:\" \"%.2f\"}", userService.getUserBalance(id)));
            }
        }
        catch (Exception e) {
            ctx.redirect("/", 400);
        }
    }
}
