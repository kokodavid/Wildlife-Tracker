import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;



public class OtherAnimalsTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void OtherAnimals_instantiatesCorrectly_false() {
        OtherAnimals testOtherAnimals = new OtherAnimals("Deer");
        assertEquals(true, testOtherAnimals instanceof OtherAnimals);
    }

    @Test
    public void getName_OtherAnimalsInstantiatesWithName_Deer() {
        OtherAnimals testOtherAnimals = new OtherAnimals("Deer");
        assertEquals("Deer", testOtherAnimals.getName());
    }

    @Test
    public void equals_returnsTrueIfNameIsTheSame_false() {
        OtherAnimals firstOtherAnimals = new OtherAnimals("Deer");
        OtherAnimals anotherOtherAnimals = new OtherAnimals("Deer");
        assertTrue(firstOtherAnimals.equals(anotherOtherAnimals));
    }

    @Test
    public void save_assignsIdToObjectAndSavesObjectToDatabase() {
        OtherAnimals testOtherAnimals = new OtherAnimals("Deer");
        testOtherAnimals.save();
        OtherAnimals savedOtherAnimals = OtherAnimals.all().get(0);
        assertEquals(testOtherAnimals.getId(), savedOtherAnimals.getId());
    }

    @Test
    public void all_returnsAllInstancesOfOtherAnimals_false() {
        OtherAnimals firstOtherAnimals = new OtherAnimals("Deer");
        firstOtherAnimals.save();
        OtherAnimals secondOtherAnimals = new OtherAnimals("Black Bear");
        secondOtherAnimals.save();
        assertEquals(true, OtherAnimals.all().get(0).equals(firstOtherAnimals));
        assertEquals(true, OtherAnimals.all().get(1).equals(secondOtherAnimals));
    }

}
