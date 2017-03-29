import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class RatingTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/roaster_ratings_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM ratings *;";
      con.createQuery(sql).executeUpdate();
    }
  }
  @Test
  public void Rating_instantiatesCorrectly_true(){
    Rating testRating = new Rating(1, "name", 3, "super okay coffee");
    assertTrue(testRating instanceof Rating);
  }

  @Test
  public void getRoasterId_instantiatesWithRoasterId_true(){
    Rating testRating = new Rating(1, "name", 3, "super okay coffee");
    assertEquals(1, testRating.getRoasterId());
  }

  @Test
  public void getRater_instantiatesWithRater_true(){
    Rating testRating = new Rating(1, "name", 3, "super okay coffee");
    assertEquals("name", testRating.getRater());
  }

  @Test
  public void getRating_instantiatesWithRating_true(){
    Rating testRating = new Rating(1, "name", 3, "super okay coffee");
    assertEquals(3, testRating.getRating());
  }

  @Test
  public void getComment_instantiatesWithComment_true(){
    Rating testRating = new Rating(1, "name", 3, "super okay coffee");
    assertEquals("super okay coffee", testRating.getComment());
  }
}
