import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DateFormat;
import java.util.Date;

public class SightingTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void sighting_instantiatesCorrectly_true() {
        OtherAnimals testOtherAnimals = new OtherAnimals("Leopard");
        testOtherAnimals.save();
        Sighting testSighting = new Sighting(testOtherAnimals.getId(), "area A", "Ranger Odids");
        assertEquals(true, testSighting instanceof Sighting);
    }

    @Test
    public void equals_returnsTrueIfLocationAndDescriptionAreSame_true() {
        OtherAnimals testOtherAnimals = new OtherAnimals("Leopard");
        testOtherAnimals.save();
        Sighting testSighting = new Sighting(testOtherAnimals.getId(), "area A", "Ranger Odids");
        Sighting anotherSighting = new Sighting(testOtherAnimals.getId(), "area A", "Ranger Odids");
        assertTrue(testSighting.equals(anotherSighting));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Sighting() {
        OtherAnimals testOtherAnimals = new OtherAnimals("Leopard");
        testOtherAnimals.save();
        Sighting testSighting = new Sighting (testOtherAnimals.getId(), "area A", "Ranger Odids");
        testSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(testSighting));
    }

    @Test
    public void all_returnsAllInstancesOfSighting_true() {
        OtherAnimals testOtherAnimals = new OtherAnimals("Leopard");
        testOtherAnimals.save();
        Sighting testSighting = new Sighting (testOtherAnimals.getId(), "area A", "Ranger Odids");
        testSighting.save();
        OtherAnimals secondTestOtherAnimals = new OtherAnimals("Panther");
        secondTestOtherAnimals.save();
        Sighting secondTestSighting = new Sighting (testOtherAnimals.getId(), "area A", "Ranger Odids");
        secondTestSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(testSighting));
        assertEquals(true, Sighting.all().get(1).equals(secondTestSighting));
    }

    @Test
    public void find_returnsSightingWithSameId_secondSighting() {
        OtherAnimals testOtherAnimals = new OtherAnimals("Leopard");
        testOtherAnimals.save();
        Sighting testSighting = new Sighting (testOtherAnimals.getId(), "area A", "Ranger Odids");
        testSighting.save();
        OtherAnimals secondTestOtherAnimals = new OtherAnimals("kish");
        secondTestOtherAnimals.save();
        Sighting secondTestSighting = new Sighting (testOtherAnimals.getId(), "area A", "Ranger Odids");
        secondTestSighting.save();
        assertEquals(Sighting.find(secondTestSighting.getId()), secondTestSighting);
    }


}
