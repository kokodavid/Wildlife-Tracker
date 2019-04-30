import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        setPort(port);

        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("endangeredAnimals", EndangeredAnimal.all());
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("endangeredAnimals", EndangeredAnimal.all());
            model.put("template", "templates/animal-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        post("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            boolean endangered = request.queryParamsValues("endangered")!=null;
            if (endangered) {
                String name = request.queryParams("name");
                String health = request.queryParams("health");
                String age = request.queryParams("age");
                String type = request.queryParams("type");
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name,type, health, age);
                endangeredAnimal.save();
                model.put("endangeredAnimals", EndangeredAnimal.all());
            } else {
                String name = request.queryParams("name");
                OtherAnimals OtherAnimals = new OtherAnimals(name);
                OtherAnimals.save();
                model.put("OtherAnimals", OtherAnimals.all());
                model.put("endangeredAnimals", EndangeredAnimal.all());
            }
            response.redirect("/");
            return null;
        });
        get("/sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", OtherAnimals.all());
            model.put("endangeredAnimals", EndangeredAnimal.all());
            model.put("template", "templates/sighting-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }
}