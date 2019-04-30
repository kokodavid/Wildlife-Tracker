import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class EndangeredAnimal extends Animal {
    private String health;
    private String age;
    private String type;
    private boolean endangered;

    public static final String HEALTH_ILL = "ill";
    public static final String HEALTH_HEALTHY = "healthy";
    public static final String HEALTH_OKAY = "okay";

    public static final String AGE_NEWBORN = "newborn";
    public static final String AGE_YOUNG = "young";
    public static final String AGE_ADULT = "adult";

    public EndangeredAnimal(String name, String type, String health, String age) {
        this.name = name;
        endangered = true;
        this.health = health;
        this.age = age;
        this.type = type;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    public String getType() {
        return type;
    }


    @Override
    public boolean equals(Object otherEndangered) {
        if(!(otherEndangered instanceof EndangeredAnimal)) {
            return false;
        } else {
            EndangeredAnimal newEndangered = (EndangeredAnimal) otherEndangered;
            return this.getName().equals(newEndangered.getName()) && this.getHealth().equals(newEndangered.getHealth()) && this.getAge().equals(newEndangered.getAge());
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO endangered_animals (name, health, age) VALUES (:name, :health, :age);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<EndangeredAnimal> all() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM endangered_animals;";
            return con.createQuery(sql)
                    .executeAndFetch(EndangeredAnimal.class);
        }
    }


    public static EndangeredAnimal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM endangered_animals WHERE id=:id;";
            EndangeredAnimal Endangered = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(EndangeredAnimal.class);
            return Endangered;
        }
    }


    }

