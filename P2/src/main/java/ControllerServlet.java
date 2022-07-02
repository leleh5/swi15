//Bruna de Paula Silva
//Leticia Vitoria Rodrigues Rosa

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;
    private SalesmanDAO salesmanDAO;
    private OrdersDAO ordersDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        customerDAO = new CustomerDAO(jdbcURL, jdbcUsername, jdbcPassword);
        salesmanDAO = new SalesmanDAO(jdbcURL, jdbcUsername, jdbcPassword);
        ordersDAO = new OrdersDAO(jdbcURL, jdbcUsername, jdbcPassword);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new_c":
                    showNewForm_c(request, response);
                    break;
                case "/insert_c":
                    insertCustomer(request, response);
                    break;
                case "/delete_c":
                    deleteCustomer(request, response);
                    break;
                case "/edit_c":
                    showEditForm_c(request, response);
                    break;
                case "/update_c":
                    updateCustomer(request, response);
                    break;
                case "/new_s":
                    showNewForm_s(request, response);
                    break;
                case "/insert_s":
                    insertSalesman(request, response);
                    break;
                case "/delete_s":
                    deleteSalesman(request, response);
                    break;
                case "/edit_s":
                    showEditForm_s(request, response);
                    break;
                case "/update_s":
                    updateSalesman(request, response);
                    break;
                case "/new_o":
                    showNewForm_o(request, response);
                    break;
                case "/insert_o":
                    insertOrders(request, response);
                    break;
                case "/delete_o":
                    deleteOrders(request, response);
                    break;
                case "/edit_o":
                    showEditForm_o(request, response);
                    break;
                case "/update_o":
                    updateOrders(request, response);
                    break;
                case "/creditos":
                    creditos(request, response);
                    break;
                default:
                    listOrders(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void creditos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("CreditosDupla.jsp");
        dispatcher.forward(request, response);
    }
    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Orders> listOrders = ordersDAO.listAllOrders();
        request.setAttribute("listOrders", listOrders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("OrdersList.jsp");
        dispatcher.forward(request, response);
    }

    //Customer
    private void showNewForm_c(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm_c(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = customerDAO.getCustomer(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerForm.jsp");
        request.setAttribute("customer", existingCustomer);
        dispatcher.forward(request, response);

    }

    private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        int grade = Integer.parseInt(request.getParameter("grade"));
        int salesman_id = Integer.parseInt(request.getParameter("salesman_id"));

        Customer newCustomer = new Customer(name, city, grade, salesman_id);
        customerDAO.insertCustomer(newCustomer);
        //response.sendRedirect("list_o");
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        int grade = Integer.parseInt(request.getParameter("grade"));
        int salesman_id = Integer.parseInt(request.getParameter("salesman_id"));

        Customer customer = new Customer(id, name, city, grade, salesman_id);
        customerDAO.updateCustomer(customer);
        //response.sendRedirect("list_o");
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Customer customer = new Customer(id);
        customerDAO.deleteCustomer(customer);
        //response.sendRedirect("list_o");

    }

    //Salesman
    private void showNewForm_s(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("SalesmanForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm_s(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Salesman existingSalesman = salesmanDAO.getSalesman(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("SalesmanForm.jsp");
        request.setAttribute("salesman", existingSalesman);
        dispatcher.forward(request, response);

    }

    private void insertSalesman(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        int commission = Integer.parseInt(request.getParameter("commission"));

        Salesman newSalesman = new Salesman(name, city, commission);
        salesmanDAO.insertSalesman(newSalesman);
        //response.sendRedirect("list_o");
    }

    private void updateSalesman(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        int commission = Integer.parseInt(request.getParameter("commission"));

        Salesman salesman = new Salesman(id, name, city, commission);
        salesmanDAO.updateSalesman(salesman);
        //response.sendRedirect("list_o");
    }

    private void deleteSalesman(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Salesman salesman = new Salesman(id);
        salesmanDAO.deleteSalesman(salesman);
        //response.sendRedirect("list_o");

    }

    //Orders
    private void showNewForm_o(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("OrdersForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm_o(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Orders existingOrders = ordersDAO.getOrders(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("OrdersForm.jsp");
        request.setAttribute("orders", existingOrders);
        dispatcher.forward(request, response);

    }

    private void insertOrders(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int purch_amt = Integer.parseInt(request.getParameter("purch_amt"));
        String date = request.getParameter("date");
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        int salesman_id = Integer.parseInt(request.getParameter("salesman_id"));

        Orders newOrders = new Orders(purch_amt, date, customer_id, salesman_id);
        ordersDAO.insertOrders(newOrders);
        //response.sendRedirect("list_o");
    }

    private void updateOrders(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int purch_amt = Integer.parseInt(request.getParameter("purch_amt"));
        String date = request.getParameter("date");
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        int salesman_id = Integer.parseInt(request.getParameter("salesman_id"));

        Orders orders = new Orders(id, purch_amt, date, customer_id, salesman_id);
        OrdersDAO.updateOrders(orders);
        //response.sendRedirect("list_o");
    }

    private void deleteOrders(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Orders orders = new Orders(id);
        ordersDAO.deleteOrders(orders);
        //response.sendRedirect("list_o");

    }
}