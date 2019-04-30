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
            model.put("animals", OtherAnimals.all());
            model.put("endangeredAnimals", EndangeredAnimal.all());
            model.put("sightings", Sighting.all());
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", OtherAnimals.all());
            model.put("endangeredAnimals", EndangeredAnimal.all());
            model.put("template", "templates/animal-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        post("/animals/new", (request, response) -> {
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
            model.put("sightings", Sighting.all());
            model.put("template", "templates/sightings.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/animal/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            OtherAnimals animal = OtherAnimals.find(Integer.parseInt(request.params("id")));
            model.put("animal", animal);
            model.put("template", "templates/animal.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/endangered_animal/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            EndangeredAnimal endangeredAnimal = EndangeredAnimal.find(Integer.parseInt(request.params("id")));
            model.put("endangeredAnimal", endangeredAnimal);
            model.put("template", "templates/endangered-animal.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/endangered_sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangerName = request.queryParams("rangerName");
            int animalIdSelected = Integer.parseInt(request.queryParams("endangeredAnimalSelected"));
            String location = request.queryParams("location");
            Sighting sighting = new Sighting(animalIdSelected, location, rangerName);
            sighting.save();
            model.put("sighting", sighting);
            model.put("animals", EndangeredAnimal.all());
            String animal = EndangeredAnimal.find(animalIdSelected).getName();
            model.put("animal", animal);
            model.put("template", "templates/success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangerName = request.queryParams("rangerName");
            int animalIdSelected = Integer.parseInt(request.queryParams("animalSelected"));
            String location = request.queryParams("location");
            Sighting sighting = new Sighting(animalIdSelected, location, rangerName);
            sighting.save();
            model.put("sighting", sighting);
            model.put("animals", OtherAnimals.all());
            String animal = OtherAnimals.find(animalIdSelected).getName();
            model.put("animal", animal);
            model.put("template", "templates/success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }
}