<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:import url="../../layout/app.jsp">
    <c:param name="content">
    <div class="recomend_wrapper">
        <div class="#">
        <c:forEach var="rr" items="${rrList}">
            <table>
                <tr><td>${rr.r.name}</td><td>材料 &nbsp; 材料保有率${rr.ratio}%</td></tr>
                <c:forEach var="mq" items="${rr.mqList}" varStatus="row">
                    <c:choose>
                        <c:when test="${row.first}">
                            <tr><td rowspan="${fn:length(rr.mqList)}"><img src="#"></td>
                            <td>${mq.m.name} &nbsp;&nbsp; ${mq.b_quan}&nbsp; / &nbsp; ${mq.rm_quan}</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr><td>${mq.m.name} &nbsp;&nbsp; ${mq.b_quan}&nbsp; / &nbsp; ${mq.rm_quan}</td></tr>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </table>
        </c:forEach>
        </div>
    </div>

    <div class="box_wrapper">
        <div class="title">
            <h2>あなたの冷蔵庫</h2>
        </div>
        <table>
             <tbody>
               <c:forEach var="bm" items="${bmList}">
                  <tr><td>${bm.m.name}</td><td>${bm.b.quantity} ${bm.m.unit}</td></tr>
               </c:forEach>
             </tbody>
        </table>
    </div>


    </c:param>
</c:import>