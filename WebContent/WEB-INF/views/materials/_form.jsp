<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="materials_form">
    <label for="name">食材名</label>
    <input type="text" name="name" value="${name}">

    <label for="unit">単位名</label>
    <select name="unit">
        <c:forEach var="unit" items="${unit_names}">
            <option value="${unit}">${unit}</option>
        </c:forEach>
    </select>

    <label for="type">種類</label>
    <select name="type">
        <c:forEach var="type" items="${types}">
            <option value="${type}">${type}</option>
        </c:forEach>
    </select>
    <label for="use_limit">消費期限</label>
     <select name="use_limit">
        <c:forEach var="i" begin="1" step="1" end="60" >
            <option value="${i}">${i}日間</option>
        </c:forEach>
        <option value="90">90日間</option>
        <option value="180">180日間</option>
        <option value="360">360日間</option>
    </select>
    <input type="hidden" name="_token" value="${_token}">
    <button type="submit">登録</button>
</div>

