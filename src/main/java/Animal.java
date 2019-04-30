import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public abstract class Animal {
    public int id;
    public String name;
    public String type;
    public String health;
    public boolean endangered;

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType(){
        return type;
    }

    public String getHealth(){
        return health;
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
                    this.type.equals(newAnimal.type);
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (id,name, health, age) VALUES (:id,:name, :health, :age)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("health", this.health)
                    .addParameter("age", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }

}
