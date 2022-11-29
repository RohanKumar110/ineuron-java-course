import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {

    private int id;
    private String name;
    private String gender;
    private String course;
    private String joiningDate;

    public Student() {

    }

    public Student(int id, String name, String gender, String course, String joiningDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.course = course;
        this.joiningDate = joiningDate;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", course='" + course + '\'' +
                ", joining_date='" + joiningDate + '\'' +
                '}';
    }
}

interface Database<T> {
    int create(T t);

    List<T> get();

    List<T> getByCourse(String name);

    int update(int id, T t);

    int delete(int id);

    int count();

    void close();
}

class StudentDB implements Database<Student> {

    private final String user = "root";
    private final String password = "moneyheist";
    private final String url = "jdbc:mysql://localhost:3306/ineuron";
    private final Connection connection;

    public StudentDB() {

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int create(Student student) {

        int affectedRows = 0;
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
               if(statement != null){
                   SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                   java.util.Date uJoining_Date = dateFormat.parse(student.getJoiningDate());
                   java.sql.Date sJoining_date = new java.sql.Date(uJoining_Date.getTime());
                   String name = "'" + student.getName() + "'";
                   String gender = "'" + student.getGender() + "'";
                   String course = "'" + student.getCourse() + "'";
                   String joiningDate = "'" + sJoining_date + "'";
                   String insertQuery =
                           "INSERT INTO STUDENTS(name,gender,course,joining_date) " +
                                   "VALUES(" + name + "," + gender + "," + course + "," + joiningDate + ")";
                   affectedRows = statement.executeUpdate(insertQuery);
                   if (affectedRows > 0) {
                       System.out.println("Student created successfully");
                   } else {
                       System.out.println("No student created");
                   }
                   statement.close();
               }
            }
        } catch (SQLException e) {
            System.out.println("Error while created student");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return affectedRows;
    }

    public List<Student> get() {

        List<Student> students = new ArrayList<>();
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
               if(statement != null){
                   String selectQuery = "SELECT * FROM students";
                   ResultSet result = statement.executeQuery(selectQuery);
                   if (result != null) {
                       while (result.next()) {

                           Student student = new Student();
                           student.setId(result.getInt("id"));
                           student.setName(result.getString("name"));
                           student.setGender(result.getString("gender"));
                           student.setCourse(result.getString("course"));
                           java.sql.Date joining_date = result.getDate("joining_date");
                           SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                           student.setJoiningDate(dateFormat.format(joining_date));
                           students.add(student);
                       }
                       result.close();
                       statement.close();
                   }
               }
            }
        } catch (SQLException e) {
            System.out.println("Error while reading students");
        }
        return students;
    }

    public List<Student> getByCourse(String course) {

        List<Student> students = new ArrayList<>();
        if (connection != null) {

            try {
                Statement statement = connection.createStatement();
                if(statement != null){
                    String selectQuery = String.format("SELECT * FROM students WHERE course='%s'", course);
                    ResultSet result = statement.executeQuery(selectQuery);
                    if (result != null) {
                        while (result.next()) {

                            Student student = new Student();
                            student.setId(result.getInt("id"));
                            student.setName(result.getString("name"));
                            student.setGender(result.getString("gender"));
                            student.setCourse(result.getString("course"));
                            java.sql.Date joining_date = result.getDate("joining_date");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            student.setJoiningDate(dateFormat.format(joining_date));
                            students.add(student);
                        }
                        result.close();
                        statement.close();
                    } else {
                        System.out.println("No Students enrolled in " + course);
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error while reading course students");
            }
        }
        return students;
    }

    @Override
    public int update(int id, Student student) {
        int affectedRows = 0;

        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
               if(statement != null){
                   SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                   java.util.Date uJoining_Date = dateFormat.parse(student.getJoiningDate());
                   java.sql.Date sJoining_date = new java.sql.Date(uJoining_Date.getTime());
                   String name = "'" + student.getName() + "'";
                   String gender = "'" + student.getGender() + "'";
                   String course = "'" + student.getCourse() + "'";
                   String joiningDate = "'" + sJoining_date + "'";
                   String updateQuery = "UPDATE STUDENTS SET name=" + name + ",gender=" + gender + ",course=" + course +
                           ",joining_date=" + joiningDate + " WHERE id=" + id;
                   affectedRows = statement.executeUpdate(updateQuery);
                   if (affectedRows > 0) {
                       System.out.println("Student updated successfully with id: " + id);
                   } else {
                       System.out.println("No student updated with id: " + id);
                   }
                   statement.close();
               }
            }
        } catch (SQLException e) {
            System.out.println("Error while updating student");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return affectedRows;
    }

    @Override
    public int delete(int id)  {

        int affectedRows = 0;
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                String deleteQuery = "DELETE FROM STUDENTS WHERE ID=" + id;
                affectedRows = statement.executeUpdate(deleteQuery);
                if (affectedRows > 0) {
                    System.out.println("Student deleted successfully with id: " + id);
                } else {
                    System.out.println("No student deleted with id: " + id);
                }
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting student");
        }
        return affectedRows;
    }

    public int count() {
        int count = 0;
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                String countQuery = "SELECT COUNT(*) FROM STUDENTS";
                ResultSet resultSet = statement.executeQuery(countQuery);
                if (resultSet != null) {
                    resultSet.next();
                    count = resultSet.getInt(1);
                }
                System.out.println("Total Students: " + count);
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println("Error while calculating student");
        }
        return count;
    }

    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.out.println("Error while closing database connection");
        }
    }
}

public class Program1 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Database<Student> studentDB = new StudentDB();
        System.out.println("************************");
        System.out.println("Welcome to Student App");
        System.out.println("************************");

        while (true) {

            System.out.println("Menu");
            System.out.println("1.Add Student");
            System.out.println("2.Update Student");
            System.out.println("3.Delete Student");
            System.out.println("4.Total Students");
            System.out.println("5.Display All Students");
            System.out.println("6.Display All Students By Course");
            System.out.println("7.Exit Student App");
            System.out.println("Enter Choice: ");
            int choice = in.nextInt();

            switch (choice) {

                case 1 -> {
                    Student student = takeStudentDetails(in);
                    System.out.println("\n****************************************");
                    studentDB.create(student);
                    System.out.println("****************************************\n");
                }

                case 2 -> {
                    System.out.println("Enter Student Id: ");
                    int id = in.nextInt();
                    Student student = takeStudentDetails(in);
                    System.out.println("\n****************************************");
                    studentDB.update(id, student);
                    System.out.println("****************************************\n");
                }

                case 3 -> {
                    System.out.println("Enter Student Id: ");
                    int id = in.nextInt();
                    System.out.println("\n****************************************");
                    studentDB.delete(id);
                    System.out.println("****************************************\n");
                }

                case 4 -> {
                    System.out.println("****************************************");
                    studentDB.count();
                    System.out.println("****************************************\n");
                }

                case 5 -> {
                    System.out.println("All Students");
                    System.out.println("****************************************");
                    studentDB.get().forEach(System.out::println);
                    System.out.println("****************************************\n");
                }

                case 6 -> {
                    System.out.println("Enter course name: ");
                    in.nextLine();
                    String course = in.nextLine();
                    System.out.println("All Students By Course");
                    System.out.println("****************************************");
                    studentDB.getByCourse(course).forEach(System.out::println);
                    System.out.println("****************************************\n");
                }

                case 7 -> {
                    in.close();
                    studentDB.close();
                    System.out.println("\n****************************************");
                    System.out.println("Thank You for using our application");
                    System.out.println("****************************************\n");
                    System.exit(0);
                }

                default -> System.out.println("Invalid Choice");
            }
        }
    }

    private static Student takeStudentDetails(Scanner in) {

        Student student = new Student();
        System.out.println("Enter Student Name: ");
        student.setName(in.nextLine());
        System.out.println("Enter Student Gender: ");
        student.setGender(in.nextLine());
        System.out.println("Enter Student Course: ");
        student.setCourse(in.nextLine());
        System.out.println("Enter Student Joining Date(dd-MM-yyyy): ");
        student.setJoiningDate(in.nextLine());
        return student;
    }
}