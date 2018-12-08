<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../../layout/app.jsp">
    <c:param name="content">
        <c:if test="${hasError==true}">
            <p>メールアドレスかパスワードが間違っています。</p>
        </c:if>

        <h2>ログイン画面</h2>
        <form method="POST" action="<c:url value='/login'/>" >
            <label for="email" >メールアドレス</label>
            <input type="text" name="email" value="${email}"/><br><br>
            <label for="plain_pass">パスワード</label>
            <input type="password" name="plain_pass"/><br><br>
            <input type="hidden" name="_token" value="${_token}"/>
            <input type="submit" value="送信">
        </form>
    </c:param>
</c:import>