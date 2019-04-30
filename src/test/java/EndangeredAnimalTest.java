import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class EndangeredAnimalTest {
    EndangeredAnimal testEndangeredAnimal;
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Before
    public void setUp() {
        testEndangeredAnimal = new EndangeredAnimal("koala","bear","okay","young");
    }

    @Test
    public void animals_instantiatesCorrectly_true() {
        assertTrue(testEndangeredAnimal instanceof EndangeredAnimal);
    }

    @Test
    public void name_instantiatesCorrectly_true() {
        assertEquals("koala", testEndangeredAnimal.getName());
    }

    @Test
    public void species_instantiatesCorrectly_true() {
        assertEquals("bear", testEndangeredAnimal.getType());
    }

    @Test
    public void equals_returnsTrueIfPropertiesAreSame_true(){
        EndangeredAnimal testEndangeredAnimal2 = new EndangeredAnimal("koala", "bear", "healthy", "young");
        assertTrue(testEndangeredAnimal.equals(testEndangeredAnimal2));
    }

    @Test
    public void save_insertsEndangeredAnimalIntoDatabase_EndangeredAnimal() {
        testEndangeredAnimal.save();
        EndangeredAnimal testEndangeredAnimal2 = null;
        try(Connection con = DB.sql2o.open()){
            testEndangeredAnimal2 = con.createQuery("SELECT * FROM animals WHERE name='koala'")
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimal.class);
        }
        assertTrue(testEndangeredAnimal2.equals(testEndangeredAnimal));
    }

    @Test
    public void all_returnsAllInstancesOfPerson_true() {
        testEndangeredAnimal.save();
        EndangeredAnimal testEndangeredAnimal2 = new EndangeredAnimal("scratchy", "red wolf", "sick", "old");
        testEndangeredAnimal2.save();
        assertEquals(true, EndangeredAnimal.all().get(0).equals(testEndangeredAnimal));
        assertEquals(true, EndangeredAnimal.all().get(1).equals(testEndangeredAnimal2));
    }

    @Test
    public void find_returnsEndangeredAnimalWithSameId_secondEndangeredAnimal() {
        testEndangeredAnimal.save();
        EndangeredAnimal testEndangeredAnimal2 = new EndangeredAnimal("scratchy","red wolf", "sick", "old");
        testEndangeredAnimal2.save();
        assertEquals(EndangeredAnimal.find(testEndangeredAnimal2.getId()), testEndangeredAnimal2);
    }

}
