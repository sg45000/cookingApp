<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:import url="../../layout/app.jsp">
	<c:param name="content">
	   <div class="content_heading">
            <h2>レシピ一覧</h2>
            <h4>${count}件</h4>
        </div>
        <div class="recipes_menu">
            <div class="recipe_table" >
                <table>
                    <c:forEach var="index" items="${indexList}" varStatus="recipe">
                        <tr><th>${index.r.name}</th><td>材料</td><td>操作</td></tr>

                        <c:forEach var="material" items="${index.morList}" varStatus="row">
                            <c:choose>
                                <c:when test="${row.first}">
                                    <tr>
                                        <td rowspan="${fn:length(index.morList)}" class="recipe_img" class="img-td"><img src="${pageContext.request.contextPath}/recipes_image/${index.r.image_name}"></td>
                                        <td>${material.name}&nbsp;&nbsp;${material.quantity}&nbsp;${material.unit}</td>
                                        <td>
                                            <a href="#" onClick="confirmCooking(document.forms[${recipe.index}]);">料理する</a>
                                            <form method="post" action="<c:url value='/recipes/cook'/>">
                                                    <input type="hidden" value="${_token}" name="_token"/>
                                                    <input type="hidden" name="recipe_id" value="${index.r.recipe_id}">
                                            </form>

                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td>${material.name}&nbsp;&nbsp;${material.quantity}&nbsp;${material.unit}</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    <tr></tr>
                    </c:forEach>
                </table>
                 <a href="<c:url value='/recipes/new'/>">レシピを登録する</a>
            </div>
        </div>
        <script>
        function confirmCooking(frm){
            if(confirm("本当に料理しますか？")){
                frm.submit();
            }
        }
        </script>




	</c:param>
</c:import>
