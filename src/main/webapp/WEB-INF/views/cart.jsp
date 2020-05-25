<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="parts/header.jsp"></jsp:include>
<div class="container">
    <div class="row d-flex justify-content-center mt-5">
        <div class="col-6">
            <table class="table">
                <thead class="orange white-text">
                <tr>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>
                            <c:out value ="${product.getId()}"/>
                        </td>
                        <td>
                            <c:out value ="${product.name}"/>
                        </td>
                        <td>
                            <c:out value ="${product.price}"/>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/cart/products/delete?id=${product.id}">
                                <button class="btn" type="submit">Delete</button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <a href="${pageContext.request.contextPath}/order/complete">
                <button class="btn" type="submit">Complete Order</button>
            </a>
            </tbody>
        </div>
    </div>
</div>
