<%@page import="model.Recipes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../../layout/app.jsp">
	<c:param name="content">
	   <div class="content_heading">
            <h2>レシピ一覧</h2>
            <h4>${count}件</h4>
        </div>
        <div class="recipes_menu">


                <div class="recipe_table" >
                    <table>
                    <c:forEach var="recipe" items="${recipes}">
                    <tr><th>${recipe.name}</th><td>材料</td><td>操作</td></tr>
                    <% Recipes recipe=(Recipes)pageContext.getAttribute("recipe"); %>
                    <% String recipe_id=recipe.getRecipe_id().toString();%>


                    <c:forEach var="material" items="<%= request.getAttribute("materials_"+recipe_id) %>" varStatus="status">
                        <c:choose>
                            <c:when test="${status.first}">
                                <tr>
                                    <td rowspan="<%= (long)request.getAttribute("count_"+recipe_id) %>"><img src="#"></td>
                                    <td>${material.name}${material.unit}</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td>${material.name}${material.unit}</td>                                </tr>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>


                    <tr></tr>


                    </c:forEach>


                    </table>
                </div>

        </div>




        <a href="<c:url value='/recipes/new'/>">レシピを登録する</a>
	</c:param>
</c:import>