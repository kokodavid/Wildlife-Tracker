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
        OtherAnimals testOtherAnimals = new OtherAnimals("koala");
        assertEquals(true, testOtherAnimals instanceof OtherAnimals);
    }

    @Test
    public void getName_OtherAnimalsInstantiatesWithName_koala() {
        OtherAnimals testOtherAnimals = new OtherAnimals("koala");
        assertEquals("koala", testOtherAnimals.getName());
    }

    @Test
    public void equals_returnsTrueIfNameIsTheSame_false() {
        OtherAnimals firstOtherAnimals = new OtherAnimals("koala");
        OtherAnimals anotherOtherAnimals = new OtherAnimals("koala");
        assertTrue(firstOtherAnimals.equals(anotherOtherAnimals));
    }

    @Test
    public void save_assignsIdToObjectAndSavesObjectToDatabase() {
        OtherAnimals testOtherAnimals = new OtherAnimals("koala");
        testOtherAnimals.save();
        OtherAnimals savedOtherAnimals = OtherAnimals.all().get(0);
        assertEquals(testOtherAnimals.getId(), savedOtherAnimals.getId());
    }

    @Test
    public void all_returnsAllInstancesOfOtherAnimals_false() {
        OtherAnimals firstOtherAnimals = new OtherAnimals("koala");
        firstOtherAnimals.save();
        OtherAnimals secondOtherAnimals = new OtherAnimals("koala");
        secondOtherAnimals.save();
        assertEquals(true, OtherAnimals.all().get(0).equals(firstOtherAnimals));
        assertEquals(true, OtherAnimals.all().get(1).equals(secondOtherAnimals));
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        OtherAnimals firstOtherAnimals = new OtherAnimals("Koala");
        firstOtherAnimals.save();
        OtherAnimals secondOtherAnimals = new OtherAnimals("koala");
        secondOtherAnimals.save();
        assertEquals(OtherAnimals.find(secondOtherAnimals.getId()), secondOtherAnimals);
    }

}
