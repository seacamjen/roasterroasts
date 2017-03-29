import org.sql2o.*;
import java.util.List;

public class Rating {
  private int id;
  private int roaster_id;
  private String rater;
  private int rating;
  private String comment;

  public Rating(int roaster_id, String rater, int rating, String comment) {
    this.roaster_id = roaster_id;
    this.rater = rater;
    this.rating = rating;
    this.comment = comment;
  }

  public int getRoasterId(){
    return roaster_id;
  }

  public String getRater(){
    return rater;
  }

  public int getRating(){
    return rating;
  }

  public String getComment(){
    return comment;
  }

  public int getId() {
    return id;
  }

  public static List<Rating> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM ratings;";
      return con.createQuery(sql)
        .executeAndFetch(Rating.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO ratings (roaster_id, rater, rating, comment) VALUES (:roaster_id, :rater, :rating, :comment);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("roaster_id", roaster_id)
        .addParameter("rater", rater)
        .addParameter("rating", rating)
        .addParameter("comment", comment)
        .executeUpdate()
        .getKey();
    }
  }

  @Override
  public boolean equals(Object otherRating) {
    if (!(otherRating instanceof Rating)) {
      return false;
    } else {
      Rating newRating = (Rating) otherRating;
      return this.getRoasterId() == newRating.getRoasterId();
    }
  }

  public static Rating find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM ratings WHERE id = :id;";
      Rating rating = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Rating.class);
      return rating;
    }
  }
}
