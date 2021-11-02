package org.example;

import io.javalin.http.Context;

public class UserController {

    public Data data = new Data();

    public double getUserBalance(Context ctx){
        try {
            int id = Integer.parseInt(ctx.queryParam("id"));
            User user = new User(id,
                    data.getUserName(id),
                    data.getBalance(id));
            if (user.userName.equals("")){
                ctx.json("Wrong id");
            }else {
                ctx.result(String.format("{\"balance:\" \"%.2f\"}", user.balance));
            }
        }
        catch (NumberFormatException e) {
            ctx.redirect("/", 400);
        }
        return 0;
    }
}
