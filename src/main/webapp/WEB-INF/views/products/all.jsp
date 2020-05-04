<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../parts/header.jsp"></jsp:include>
<div class="container">
    <div class="row d-flex justify-content-center mt-5">
        <div class="col-6">
            <table class="table">
                <thead class="orange white-text">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Add to cart</th>
                </tr>
                </thead>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>
                            <c:out value ="${product.id}"/>
                        </td>
                        <td>
                            <c:out value ="${product.name}"/>
                        </td>
                        <td>
                            <c:out value ="${product.price}"/>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/products/addToCart?id=${product.getId()}">
                                <button class="btn" type="submit">Add to cart</button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
