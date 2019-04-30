import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class OtherAnimals extends Animal {

    public OtherAnimals(String name) {
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object otherAnimal) {
        if (!(otherAnimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal) otherAnimal;
            return this.getName().equals(newAnimal.getName());
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name) VALUES (:name);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<OtherAnimals> all() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals;";
            return con.createQuery(sql)
                    .executeAndFetch(OtherAnimals.class);
        }
    }

    public static OtherAnimals find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id=:id;";
            OtherAnimals OtherAnimals = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(OtherAnimals.class);
            return OtherAnimals;
        }
    }

}