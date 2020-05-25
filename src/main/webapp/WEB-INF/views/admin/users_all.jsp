<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../parts/header.jsp"></jsp:include>
<div class="container">
    <div class="row d-flex justify-content-center mt-5">
        <div class="col-6">
            <table class="table">
                <thead class="orange white-text">
                <tr>
                    <th>User ID</th>
                    <th>Name</th>
                    <th>Login</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>
                            <c:out value ="${user.id}"/>
                        </td>
                        <td>
                            <c:out value ="${user.name}"/>
                        </td>
                        <td>
                            <c:out value ="${user.login}"/>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/users/delete?id=${user.getId()}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
