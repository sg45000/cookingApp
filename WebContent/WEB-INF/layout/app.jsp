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
                        <a href="#" class="btn">トップページ</a>
                        <a href="#" class="btn">冷蔵庫</a>
                        <a href="#" class="btn">レシピ一覧</a>
                        <a href="#" class="btn">レシピ登録</a>
                        <a href="#" class="btn">ログアウト</a>
                    </div>
                </div>
            </div>

            <div id="content">
                ${param.content}
            </div>
            <div id="footer">by kyo</div>
        </div>

    </body>
</html>