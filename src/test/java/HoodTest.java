import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class HoodTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/roaster_ratings_test", null, null);
  }

  @Test
  public void find_findsNameGivenID_true() {
    assertEquals("North Portland", Hood.find(1));
  }


}
