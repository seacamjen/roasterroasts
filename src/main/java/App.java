import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/roasters/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/roasters-form.vtl");
      model.put("hoods", Hood.all());
      model.put("certs", Cert.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/roasters", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String location = request.queryParams("location");
      String website = request.queryParams("website");
      String description = request.queryParams("description");
      int hood = Integer.parseInt(request.queryParams("hoodId"));
      int cert = Integer.parseInt(request.queryParams("certId"));
      Roaster newRoaster = new Roaster(name, location, cert, hood, website, description);
      newRoaster.save();
      String url = String.format("/roasters");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/roasters", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/roasters.vtl");
      model.put("hoods", Hood.all());
      model.put("certs", Cert.all());
      model.put("roasters", Roaster.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/roasters/:id",(request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Roaster roaster = Roaster.find(Integer.parseInt(request.params(":id")));
      model.put("ratings", Rating.all());
      model.put("roaster", roaster);
      model.put("template", "templates/roaster.vtl");
      model.put("certs", Cert.all());
      model.put("roasters", Roaster.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/roasters/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Roaster roaster = Roaster.find(Integer.parseInt(request.params(":id")));
      String name = request.queryParams("name");
      int rating = Integer.parseInt(request.queryParams("rating"));
      String comment = request.queryParams("comment");
      int roasterId = Integer.parseInt(request.queryParams("roasterId"));
      Rating newRating = new Rating(roasterId, name, rating, comment);
      newRating.save();
      String url = String.format("/roasters/"+roaster.getId());
      response.redirect(url);
      model.put("roaster", roaster);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/roasters/:id/update/:id2", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Roaster roaster = Roaster.find(Integer.parseInt(request.params(":id")));
      String comment = request.queryParams("comment");
      Rating rating = Rating.find(Integer.parseInt(request.params(":id2")));
      rating.update(comment);
      String url = String.format("/roasters/"+roaster.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get("/roasters/:id/update", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Roaster roaster = Roaster.find(Integer.parseInt(request.params(":id")));
    //   model.put("roaster", roaster);
    //   model.put("template", "templates/update-comment-success.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
  }
}
