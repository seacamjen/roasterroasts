import org.sql2o.*;

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

}
