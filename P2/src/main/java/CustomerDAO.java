//Bruna de Paula Silva
//Leticia Vitoria Rodrigues Rosa

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public CustomerDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (CUST_NAME, CITY, GRADE, SALESMAN_ID) VALUES (?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getCity());
        statement.setInt(3, customer.getGrade());
        statement.setInt(4, customer.getSalesman_id());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Customer> listAllCustomer() throws SQLException {
        List<Customer> listCustomer = new ArrayList<>();

        String sql = "SELECT * FROM customer";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("CUSTOMER_ID");
            String name = resultSet.getString("CUST_NAME");
            String city = resultSet.getString("CITY");
            int grade = resultSet.getInt("GRADE");
            int salesman_id = resultSet.getInt("SALESMAN_ID");

            Customer customer = new Customer(id, name, city, grade, salesman_id);
            listCustomer.add(customer);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listCustomer;
    }

    public boolean deleteCustomer(Customer customer) throws SQLException {
        String sql = "DELETE FROM customer where CUSTOMER_ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, customer.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET CUST_NAME = ?, CITY = ?, GRADE = ?, SALESMAN_ID = ?";
        sql += " WHERE CUSTOMER_ID = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getCity());
        statement.setInt(3, customer.getGrade());
        statement.setInt(3, customer.getSalesman_id());
        statement.setInt(4, customer.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public Customer getCustomer(int id) throws SQLException {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE CUSTOMER_ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("NAME");
            String city = resultSet.getString("CITY");
            int grade = resultSet.getInt("GRADE");
            int salesman_id = resultSet.getInt("SALESMAN_ID");

            customer = new Customer(id, name, city, grade, salesman_id);
        }

        resultSet.close();
        statement.close();

        return customer;
    }
}
