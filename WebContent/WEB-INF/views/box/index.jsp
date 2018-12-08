<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../../layout/app.jsp">
    <c:param name="content">
        <h2>冷蔵庫の中身一覧</h2>
            <table>
                <tbody>
				    <c:forEach var="bm" items="${bmList}">
					   <tr><td>${bm.m.name}</td><td>${bm.b.quantity} ${bm.m.unit}</td></tr>
				    </c:forEach>
			     </tbody>
            </table>
            <a href="<c:url value='/box/new'/>">冷蔵庫の編集</a>
    </c:param>
</c:import>