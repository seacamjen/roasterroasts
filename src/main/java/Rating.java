import org.sql2o.*;

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

}
