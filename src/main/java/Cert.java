import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Cert {
  private int id;
  private String name;

  public Cert(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static List<Cert> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM certs;";
      return con.createQuery(sql)
        .executeAndFetch(Cert.class);
    }
  }

    public static String find(int id){
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT name FROM certs WHERE id = :id;";
        String name = con.createQuery(sql)
          .addParameter("id", id)
          .executeScalar(String.class);
        return name;
      }
    }

}
