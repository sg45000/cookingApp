<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../../layout/app.jsp">
    <c:param name="content">
    <div class="recomend_wrapper">
        <div class="title">
        </div>
        <div>
        <form method="post" action="<c:url value='/recipes/destroy'/>">
            <input type="text" name="recipe_id">
            <button type="submit"></button>
        </form>

        </div>
    </div>
    <div class="box_wrapper">
        <div class="title"></div>
    </div>


    </c:param>
</c:import>