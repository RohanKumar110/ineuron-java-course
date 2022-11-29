import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class ConnectionManager {

    private static Connection connection = null;

    private ConnectionManager() {

    }

    public static Connection getConnection() throws SQLException {

        String user = "root";
        String password = "moneyheist";
        String url = "jdbc:mysql://localhost:3306/ineuron";
        if (connection == null) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }
}

class User {

    private String name;
    private String address;
    private String gender;
    private String dob;
    private String doj;
    private String dom;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getDom() {
        return dom;
    }

    public void setDom(String dom) {
        this.dom = dom;
    }
}

public class Program2 {

    public static void main(String[] args) {

        SimpleDateFormat dateFormat = null;
        try (Connection connection = ConnectionManager.getConnection()) {
            if (connection != null) {

                String insertQuery = "INSERT INTO USERS(name,address,gender,dob,doj,dom) VALUES(?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

                if (preparedStatement != null) {
                    User user = takeInput();

                    dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    java.util.Date uDOB = dateFormat.parse(user.getDob());
                    dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                    java.util.Date uDOJ = dateFormat.parse(user.getDoj());
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date uDOM = dateFormat.parse(user.getDom());

                    java.sql.Date dob = new java.sql.Date(uDOB.getTime());
                    java.sql.Date doj = new java.sql.Date(uDOJ.getTime());
                    java.sql.Date dom = new java.sql.Date(uDOM.getTime());

                    preparedStatement.setString(1,user.getName());
                    preparedStatement.setString(2,user.getAddress());
                    preparedStatement.setString(3,user.getGender());
                    preparedStatement.setDate(4,dob);
                    preparedStatement.setDate(5,doj);
                    preparedStatement.setDate(6,dom);
                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println("No of rows affected: "+rowsAffected);
                    preparedStatement.close();
                }

                System.out.println("*****************USERS*****************");

                String selectQuery = "SELECT * FROM users";
                preparedStatement = connection.prepareStatement(selectQuery);
                if(preparedStatement != null){

                    ResultSet result = preparedStatement.executeQuery();
                    while(result.next()){
                        String name = result.getString("name");
                        String address = result.getString("address");
                        String gender = result.getString("Gender");
                        java.sql.Date dob = result.getDate("dob");
                        java.sql.Date doj = result.getDate("doj");
                        java.sql.Date dom = result.getDate("dom");

                        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        String strDOB = dateFormat.format(dob);
                        String strDOJ = dateFormat.format(doj);
                        String strDOM = dateFormat.format(dom);

                        System.out.println(name+"\t\t"+address+"\t\t"+gender+"\t\t"
                                +strDOB+"\t\t"+strDOJ+"\t\t"+strDOM);
                    }
                    result.close();
                    preparedStatement.close();
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static User takeInput() {
        Scanner in = new Scanner(System.in);
        User user = new User();
        System.out.println("Enter Name");
        user.setName(in.nextLine());
        System.out.println("Enter Address");
        user.setAddress(in.nextLine());
        System.out.println("Enter Gender");
        user.setGender(in.nextLine());
        System.out.println("Enter DOB(dd-MM-yyyy)");
        user.setDob(in.nextLine());
        System.out.println("Enter DOJ(MM-dd-yyyy)");
        user.setDoj(in.nextLine());
        System.out.println("Enter DOM(yyyy-MM-dd)");
        user.setDom(in.nextLine());
        in.close();
        return user;
    }
}