<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Orders Application</title>
</head>
<body>
    <center>
        <h1>Orders Management</h1>
        <h2>
            <a href="./new_c">Add New Customer</a>
            &nbsp;&nbsp;&nbsp;
            <a href="./new_s">Add New Salesman</a>
            &nbsp;&nbsp;&nbsp;
            <a href="./new_o">Add New Orders</a>
            &nbsp;&nbsp;&nbsp;
            <a href="./list_o">List All Orders</a>
            &nbsp;&nbsp;&nbsp;
            <a href="./creditos">Creditos da Dupla</a>

        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Orders</h2></caption>
            <tr>
                <th>ID</th>
                <th>Purch_amt</th>
                <th>Date</th>
                <th>Customer_id</th>
                <th>Salesman_id</th>
            </tr>
            <c:forEach var="Orders" items="${listOrders}">
                <tr>
                    <td><c:out value="${Orders.id}" /></td>
                    <td><c:out value="${Orders.purch_amt}" /></td>
                    <td><c:out value="${orders.date}" /></td>
                    <td><c:out value="${orders.customer_id}" /></td>
                    <td><c:out value="${orders.salesman_id}" /></td>
                    <td>
                        <a href="./edit?id=<c:out value='${orders.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="./delete?id=<c:out value='${orders.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>