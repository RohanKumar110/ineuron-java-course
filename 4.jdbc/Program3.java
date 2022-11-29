import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Mentor {

    private int id;
    private String name;
    private String course;

    public Mentor(){

    }

    public Mentor(int id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}

interface Database<T> {
    int create(T t);

    List<T> get();

    int update(int id, T t);

    int delete(int id);

    void close();
}

class MentorDB implements Database<Mentor> {

    private final String user = "root";
    private final String password = "moneyheist";
    private final String url = "jdbc:mysql://localhost:3306/ineuron";
    private final Connection connection;

    public MentorDB() {

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int create(Mentor mentor) {
        int affectedRows = 0;
        try {
            if(connection != null){
                String query = "INSERT INTO MENTOR(name,course) VALUES(?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                if(preparedStatement != null){
                    preparedStatement.setString(1,mentor.getName());
                    preparedStatement.setString(2,mentor.getCourse());
                    affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("Mentor created successfully");
                    } else {
                        System.out.println("No mentor created");
                    }
                    preparedStatement.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while creating mentor");
        }
        return affectedRows;
    }

    @Override
    public List<Mentor> get() {
        List<Mentor> mentors = new ArrayList<>();
        if (connection != null) {

            try {
                String selectQuery = "SELECT * FROM mentor";
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                if(preparedStatement != null){
                    ResultSet result = preparedStatement.executeQuery();
                    if (result != null) {
                        while (result.next()) {
                            Mentor mentor = new Mentor();
                            mentor.setId(result.getInt("id"));
                            mentor.setName(result.getString("name"));
                            mentor.setCourse(result.getString("course"));
                            mentors.add(mentor);
                        }
                        result.close();
                        preparedStatement.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error while reading mentor");
            }
        }
        return mentors;
    }

    @Override
    public int update(int id, Mentor mentor) {
        int affectedRows = 0;
        try {
            if(connection != null){
                String query = "UPDATE MENTOR SET name= ?, course=? where id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                if(preparedStatement != null){
                    preparedStatement.setString(1,mentor.getName());
                    preparedStatement.setString(2,mentor.getCourse());
                    preparedStatement.setInt(3,mentor.getId());
                    affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("Mentor updated successfully with id: " + id);
                    } else {
                        System.out.println("No mentor updated with id: " + id);
                    }
                    preparedStatement.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while updating mentor");
        }
        return affectedRows;
    }

    @Override
    public int delete(int id) {
        int affectedRows = 0;
        try {
            if(connection != null){
                String query = "DELETE FROM MENTOR WHERE ID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                if(preparedStatement != null){
                    preparedStatement.setInt(1,id);
                    affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("Mentor deleted successfully with id: " + id);
                    } else {
                        System.out.println("No mentor deleted with id: " + id);
                    }
                    preparedStatement.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting mentor");
        }
        return affectedRows;
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.out.println("Error while closing database connection");
        }   
    }
}


public class Program3 {

    public static void main(String[] args) throws SQLException {

        System.out.println("***INSERTING RECORDS***");
        Mentor hitesh = new Mentor(0,"Hitesh","Javascript");
        Mentor telusko = new Mentor(0,"Navin","Java");
        Mentor mysirg = new Mentor(0,"Shukla","Python");

        Database<Mentor> mentorDB = new MentorDB();
        mentorDB.create(hitesh);
        mentorDB.create(telusko);
        mentorDB.create(mysirg);

        System.out.println("***UPDATING RECORD***");
        mysirg.setCourse("CPP");
        mentorDB.update(3,mysirg);

        System.out.println("***DELETING RECORD***");
        mentorDB.delete(1);

        System.out.println("***SELECTING RECORDS");
        mentorDB.get().forEach(System.out::println);

        mentorDB.close();
    }
}