<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Salesman Store Application</title>
</head>
<body>
    <center>
        <h1>Salesmans Management</h1>
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
        <c:if test="${salesman != null}">
            <form action="update_s" method="post">
        </c:if>
        <c:if test="${salesman == null}">
            <form action="insert_s" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${salesman != null}">
                        Edit Salesman
                    </c:if>
                    <c:if test="${salesman == null}">
                        Add New Salesman
                    </c:if>
                </h2>
            </caption>
                <c:if test="${salesman != null}">
                    <input type="hidden" name="id" value="<c:out value='${salesman.id}' />" />
                </c:if>
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${salesman.name}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>City: </th>
                <td>
                    <input type="text" name="city" size="45"
                            value="<c:out value='${salesman.city}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Commission: </th>
                <td>
                    <input type="text" name="commission" size="5"
                            value="<c:out value='${salesman.commission}' />"
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