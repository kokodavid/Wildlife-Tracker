import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public abstract class Animal {
    public int id;
    public String name;
    public String species;
    public boolean endangered;

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies(){
        return species;
    }

    public boolean isEndangered() {
        return endangered;
    }

    @Override
    public boolean equals(Object otherAnimal){
        if (!(otherAnimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal) otherAnimal;
            return this.id == newAnimal.id && this.name.equals(newAnimal.name) &&
                    this.species.equals(newAnimal.species);
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, endangered, species) VALUES (:name, :endangered, :species)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("endangered", this.endangered)
                    .addParameter("species", this.species)
                    .executeUpdate()
                    .getKey();
        }
    }

}
