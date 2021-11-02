package org.example;

import io.javalin.Javalin;

public class App {
    public static Data data = new Data();

    public static void main( String[] args )
    {
        Javalin javalin = Javalin.create().start(3022);
        javalin.get("/", ctx ->{
            System.out.println(ctx.status());
            ctx.render("login.jte");
        });
        javalin.get("/registration", ctx ->{
            System.out.println(ctx.status());
            ctx.render("regist.jte");
        });
        javalin.post("/api/auth/", ctx ->{
            System.out.println(ctx.formParam("login"));
            System.out.println(ctx.formParam("pass"));
            String login = ctx.formParam("login");
            String pass = ctx.formParam("pass");
            data.connect(login);
            if (data.auth(login, pass)){
                System.out.println("GOOD");
                ctx.cookie("login", login);
                ctx.redirect("/home");
            }else {
                System.out.println("BAD");
                ctx.redirect("/incorrectpass");
            }
        });
        javalin.get("/incorrectpass", ctx ->{
            ctx.render("incorrect_password.jte");
        });
        javalin.post("/api/register/", ctx ->{
            System.out.println(ctx.formParam("login"));
            System.out.println(ctx.formParam("pass"));
            System.out.println(ctx.formParam("repass"));
            String login = ctx.formParam("login").replaceAll("\\s","");
            String pass = ctx.formParam("pass").replaceAll("\\s","");
            String repass = ctx.formParam("repass");
            data.connect(login);
            if (data.register(login, pass, repass) == true){
                System.out.println("GOOD");
                ctx.cookie("login", login);
                ctx.redirect("/home");
            }else {
                ctx.redirect("/", 400);
                System.out.println("BAD");
            }
        });
        javalin.get("/userBalance", ctx ->{
            try {
                int id = Integer.parseInt(ctx.queryParam("id"));
                User user = new User(id,
                    data.getUserName(id),
                    data.getBalance(id));
                if (user.userName == ""){
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
