<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${errors!=null}">
    <c:forEach var="error" items="${errors}">
        <p><c:out value="${error}"/></p>
    </c:forEach>
</c:if>

<label for="name">氏名</label>
<input type="text" name="name" value="${user.name}" /><br>
<label for="email">メールアドレス</label>
<input type="email" name="email" value="${user.email}"/><br>
<label for="password">パスワード</label>
<input type="password" name="password" value="${user.password}" /><br>

<input type="hidden" name="_token" value="${_token}" />
<input type="submit" name="submit" value="送信" />