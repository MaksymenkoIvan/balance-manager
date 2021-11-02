package org.example;

import io.javalin.Javalin;

public class App {

    public static Data data = new Data();

    public static void main( String[] args )
    {
        Javalin javalin = Javalin.create().start(3022);

        javalin.get("/userBalance", ctx ->{
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
        });
    }
}
