<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>cookingApp</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css'/>" />
        <link rel="stylesheet" href="<c:url value='/css/stylesheet.css'/>" />
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header_menu">
                    <div id="menu_btn">
                        <a href="<c:url value='/top/index'/>" class="btn">トップページ</a>
                        <a href="<c:url value='/box/index'/>" class="btn">冷蔵庫</a>
                        <a href="<c:url value='/recipes/index'/>" class="btn">レシピ一覧</a>
                        <a href="<c:url value='/materials/new'/>" class="btn">食材登録</a>
                        <a href="<c:url value='/logout'/>" class="btn">ログアウト</a>
                    </div>
                </div>
            </div>
            <c:if test="${flush!=null}">
                <div class="flush">
                    <p>${flush}</p>
                </div>
            </c:if>
            <c:if test="${errors!=null}">
                <div class="errors">
                    <c:forEach var="error" items="${errors}">
                        <p>${error}</p>
                    </c:forEach>
                </div>
            </c:if>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">by kyo</div>
        </div>

    </body>
</html>