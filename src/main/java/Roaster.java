import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Roaster {
  private int id;
  private String name;
  private String location;
  private int cert_id;
  private int hood_id;
  private String website;
  private String description;
  private Double average;

  public Roaster(String name, String location, int cert_id, int hood_id, String website, String description) {
    this.name = name;
    this.location = location;
    this.cert_id = cert_id;
    this.hood_id = hood_id;
    this.website = website;
    this.description = description;
    average = 0.0;
  }

  public String getName() {
    return name;
  }

  public String getLocation() {
    return location;
  }

  public int getCertId() {
    return cert_id;
  }

  public int getHoodId() {
    return hood_id;
  }

  public String getWebsite() {
    return website;
  }

  public String getDescription() {
    return description;
  }

  public int getId(){
    return id;
  }

  public static List<Roaster> all(){
    String sql = "SELECT * FROM roasters;";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
      .executeAndFetch(Roaster.class);
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO roasters(name, location, cert_id, hood_id, website, description, average) VALUES (:name, :location, :cert_id, :hood_id, :website, :description, :average);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", name)
      .addParameter("location", location)
      .addParameter("cert_id", cert_id)
      .addParameter("hood_id", hood_id)
      .addParameter("website", website)
      .addParameter("description", description)
      .addParameter("average", average)
      .executeUpdate()
      .getKey();
    }
  }

  public static Roaster find(int id) {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM roasters where id=:id;";
      Roaster roaster = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Roaster.class);
      return roaster;
    }
  }

  public List<Rating> getRatings() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "SELECT * FROM ratings WHERE roaster_id=:id;";
     return con.createQuery(sql)
       .addParameter("id", this.id)
       .executeAndFetch(Rating.class);
   }
 }

 public Double getAverage() {
   try(Connection con = DB.sql2o.open()) {
     String sql1 = "SELECT AVG(rating) FROM ratings WHERE roaster_id=:roaster_id;";
     this.average = con.createQuery(sql1)
       .addParameter("roaster_id", this.id)
       .executeScalar(Double.class);
   } return average;
 }

  @Override
  public boolean equals(Object otherRoaster) {
    if(!(otherRoaster instanceof Roaster)){
      return false;
    } else {
      Roaster newRoaster = (Roaster) otherRoaster;
      return this.getName().equals(newRoaster.getName());
      // && this.getId() == newDoctor.getId();
    }
  }
}
