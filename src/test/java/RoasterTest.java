import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class RoasterTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/roaster_ratings_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM roasters *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void Roaster_instantiatesCorrectly_true() {
    Roaster myRoaster = new Roaster ("Coffee Stains", "Main St", 1, 1, "coffiestains.com", "delicious coffee");
    assertTrue(myRoaster instanceof Roaster);
  }

  @Test
  public void getName_instantiatesWithName_String() {
    Roaster myRoaster = new Roaster ("Coffee Stains", "Main St", 1, 1, "coffiestains.com", "delicious coffee");
    assertEquals("Coffee Stains", myRoaster.getName());
  }

  @Test
  public void getLocation_instantiatesWithLocation_String() {
    Roaster myRoaster = new Roaster ("Coffee Stains", "Main St", 1, 1, "coffiestains.com", "delicious coffee");
    assertEquals("Main St", myRoaster.getLocation());
  }

  @Test
  public void getCertId_instantiatesWithCertId_int() {
    Roaster myRoaster = new Roaster ("Coffee Stains", "Main St", 1, 1, "coffiestains.com", "delicious coffee");
    assertEquals(1, myRoaster.getCertId());
  }

  @Test
  public void getHoodId_instantiatesWithHoodId_int() {
    Roaster myRoaster = new Roaster ("Coffee Stains", "Main St", 1, 1, "coffiestains.com", "delicious coffee");
    assertEquals(1, myRoaster.getHoodId());
  }

  @Test
  public void getWebsite_instantiatesWithWebsite_String() {
    Roaster myRoaster = new Roaster ("Coffee Stains", "Main St", 1, 1, "coffiestains.com", "delicious coffee");
    assertEquals("coffiestains.com", myRoaster.getWebsite());
  }

  @Test
  public void getDescription_instantiatesWithDescription_String() {
    Roaster myRoaster = new Roaster ("Coffee Stains", "Main St", 1, 1, "coffiestains.com", "delicious coffee");
    assertEquals("delicious coffee", myRoaster.getDescription());
  }

  @Test
  public void getAverage_instantiatesWithAverage_int() {
    Roaster myRoaster = new Roaster ("Coffee Stains", "Main St", 1, 1, "coffiestains.com", "delicious coffee");
    myRoaster.save();
    Rating ratingOne = new Rating(myRoaster.getId(), "Bobby", 2, "super okay coffee");
    ratingOne.save();
    Rating ratingTwo = new Rating(myRoaster.getId(), "Billy", 1, "super coffee");
    ratingTwo.save();
    assertEquals((Double)1.5, (Double)myRoaster.getAverage());
  }

  @Test
  public void all_allReturnsAllInstancesOfRoasters_true() {
    Roaster myRoasterOne = new Roaster ("Coffee Stains", "Main St", 1, 1, "coffiestains.com", "delicious coffee");
    Roaster myRoasterTwo = new Roaster ("Coffee Stains", "Main St", 1, 1, "coffiestains.com", "delicious coffee");
    myRoasterOne.save();
    myRoasterTwo.save();
    assertTrue(Roaster.all().get(0).equals(myRoasterOne));
    assertTrue(Roaster.all().get(1).equals(myRoasterTwo));
  }

  @Test
  public void save_assignsIdToRoaster_true() {
    Roaster myRoaster = new Roaster ("Coffee Stains", "Main St", 1, 1, "coffiestains.com", "delicious coffee");
    myRoaster.save();
    Roaster savedRoaster = Roaster.all().get(0);
    assertEquals(myRoaster.getId(), savedRoaster.getId());
  }

  @Test
    public void getId_getRoasterId_1() {
    Roaster myRoaster = new Roaster ("Coffee Stains", "Main St", 1, 1, "coffiestains.com", "delicious coffee");
    myRoaster.save();
    assertTrue(myRoaster.getId() > 0);
  }

  @Test
  public void find_findReturnsRoasterWithSameId_true() {
    Roaster myRoasterOne = new Roaster ("Coffee Stains", "Main St", 1, 1, "coffiestains.com", "delicious coffee");
    Roaster myRoasterTwo = new Roaster ("Coffee Stains", "Main St", 1, 1, "coffiestains.com", "delicious coffee");
    myRoasterOne.save();
    myRoasterTwo.save();
    assertEquals(Roaster.find(myRoasterTwo.getId()), myRoasterTwo);
  }

  @Test
  public void getRatings_retrievesAllRatingsFromDatabase_list(){
    Roaster myRoasterOne = new Roaster ("Coffee Stains", "Main St", 1, 1, "coffiestains.com", "delicious coffee");
    myRoasterOne.save();
    Rating ratingOne = new Rating(myRoasterOne.getId(), "Bobby", 3, "super okay coffee");
    ratingOne.save();
    Rating ratingTwo = new Rating(myRoasterOne.getId(), "Billy", 4, "super coffee");
    ratingTwo.save();
    Rating[] ratings = new Rating[] {ratingOne, ratingTwo};
    assertTrue(myRoasterOne.getRatings().containsAll(Arrays.asList(ratings)));
  }

  @Test
  public void delete_deleteRoasterWithSameId_true(){
    Roaster myRoasterOne = new Roaster ("Coffee Stains", "Main St", 1, 1, "coffiestains.com", "delicious coffee");
    myRoasterOne.save();
    int myRoasterId = myRoasterOne.getId();
    myRoasterOne.delete();
    assertEquals(null, Roaster.find(myRoasterId));
  }

}
