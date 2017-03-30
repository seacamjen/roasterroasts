import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Hood {
  private int id;
  private String name;
  public Hood(int id,  String name) {
    this.id = id;
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public int getId(){
    return id;
  }

  public static List<Hood> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM hoods;";
      return con.createQuery(sql)
        .executeAndFetch(Hood.class);
    }
  }

  public static String find(int id){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT name FROM hoods WHERE id = :id;";
      String name = con.createQuery(sql)
        .addParameter("id", id)
        .executeScalar(String.class);
      return name;
    }
  }

}
