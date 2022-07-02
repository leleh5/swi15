<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Orders Store Application</title>
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
        <c:if test="${orders != null}">
            <form action="update_o" method="post">
        </c:if>
        <c:if test="${orders == null}">
            <form action="insert_o" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${orders != null}">
                        Edit Orders
                    </c:if>
                    <c:if test="${orders == null}">
                        Add New Orders
                    </c:if>
                </h2>
            </caption>
                <c:if test="${orders != null}">
                    <input type="hidden" name="id" value="<c:out value='${orders.id}' />" />
                </c:if>
            <tr>
                <th>Purch_Amt: </th>
                <td>
                    <input type="text" name="purch_amt" size="45"
                            value="<c:out value='${orders.purch_amt}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Data: </th>
                <td>
                    <input type="text" name="date" size="45"
                            value="<c:out value='${orders.date}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Customer_id: </th>
                <td>
                    <input type="text" name="customer_id" size="5"
                            value="<c:out value='${orders.customer_id}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Salesman_id: </th>
                <td>
                    <input type="text" name="salesman_id" size="5"
                            value="<c:out value='${orders.salesman_id}' />"
                    />
                </td>
            </tr>
            <tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>
</body>
</html>