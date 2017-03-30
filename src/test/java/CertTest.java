import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class CertTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/roaster_ratings_test", null, null);
  }

  @Test
  public void find_findsNameGivenID_true() {
    assertEquals("organic", Cert.find(1));
  }



}
