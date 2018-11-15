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
            <c:forEach var="recipe" items="${recipes}">
                <div class="recipe_image">
                    <a href="<c:url value='/recipes/show?${recipe.recipe_id}'/>">
                        <img src="${recipe.image_name}">
                    </a>
                </div>
            </c:forEach>
        </div>

		<div id="pagination">
			<c:if test="${page}!=1">
				<a class="prev_page" href="<c:url value='/recipes/index?${page-1}'/>">&lt;&lt;前へ</a>&nbsp;
            </c:if>

			<c:forEach var="i" begin="1" end="((${count}-1)/10)+1" step="1">
				<c:choose>
					<c:when test="i==${page}">
						<div class="page_btn"><c:out value="${i}"></c:out></div>&nbsp;
                    </c:when>
					<c:otherwise>
						<div class="page_btn"><a href="<c:url value='/recipes/index?${i}'/>">${i}</a></div>&nbsp;
                    </c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${page}!=((${count}-1)/10)+1">
				<a class="next_page" href="<c:url value='/recipes/index?${page+1}'/>">次へ&gt;&gt;</a>
			</c:if>
		</div>

	</c:param>
</c:import>