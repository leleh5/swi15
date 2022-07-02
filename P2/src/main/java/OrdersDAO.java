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

public class OrdersDAO {
    private static String jdbcURL;
    private static String jdbcUsername;
    private static String jdbcPassword;
    private static Connection jdbcConnection;

    public OrdersDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected static void connect() throws SQLException {
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

    protected static void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertOrders(Orders orders) throws SQLException {
        String sql = "INSERT INTO orders (PURCH_AMT, ORD_DATE, CUSTOMER_ID, SALESMAN_ID) VALUES (?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, orders.getPurchAmt());
        statement.setString(2, orders.getDate());
        statement.setInt(3, orders.getCustomerId());
        statement.setInt(4, orders.getSalesmanId());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Orders> listAllOrders() throws SQLException {
        List<Orders> listOrders = new ArrayList<>();

        String sql = "SELECT * FROM orders";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("ORD_NO");
            int purch_amt = resultSet.getInt("PURCH_AMT");
            String date = resultSet.getString("ORD_DATE");
            int customer_id = resultSet.getInt("CUSTOMER_ID");
            int salesman_id = resultSet.getInt("SALESMAN_ID");

            Orders orders = new Orders(id, purch_amt, date, customer_id, salesman_id);
            listOrders.add(orders);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listOrders;
    }

    public boolean deleteOrders(Orders orders) throws SQLException {
        String sql = "DELETE FROM orders where ORD_NO = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, orders.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public static boolean updateOrders(Orders orders) throws SQLException {
        String sql = "UPDATE orders SET PURCH_AMT = ?, ORD_DATE = ?, CUSTOMER_ID = ?, SALESMAN_ID = ?";
        sql += " WHERE ORD_NO = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, orders.getPurchAmt());
        statement.setString(2, orders.getDate());
        statement.setInt(3, orders.getCustomerId());
        statement.setInt(4, orders.getSalesmanId());
        statement.setInt(5, orders.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public Orders getOrders(int id) throws SQLException {
        Orders orders = null;
        String sql = "SELECT * FROM orders WHERE ORD_NO = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int purch_amt = resultSet.getInt("PURCH_AMT");
            String date = resultSet.getString("ORD_DATE");
            int customer_id = resultSet.getInt("CUSTOMER_ID");
            int salesman_id = resultSet.getInt("SALESMAN_ID");

            orders = new Orders(id, purch_amt, date, customer_id, salesman_id);
        }

        resultSet.close();
        statement.close();

        return orders;
    }
}
