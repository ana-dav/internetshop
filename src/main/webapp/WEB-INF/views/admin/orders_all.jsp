<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../parts/header.jsp"></jsp:include>
<div class="container">
    <div class="row d-flex justify-content-center mt-5">
        <div class="col-6">
            <table class="table">
                <thead class="orange white-text">
                <tr>
                    <th>Order ID</th>
                    <th>Details</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>
                            <c:out value ="${order.getId()}"/>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/order/details?id=${order.getId()}">Details</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/order/delete?id=${order.getId()}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
