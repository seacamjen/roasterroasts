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

}
