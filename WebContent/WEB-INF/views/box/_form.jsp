<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:forEach var="type" items="${typeMate}">
<div class="${type.typeMate}">
<span>${type.typeName}</span>
    <c:forEach var="mate" items="${type.mateList}">


            <div class="material">
                <input type="checkbox" name="${type.typeId}" value="${mate.material_id}"
                    onclick='connecttext("${mate.material_id}",this.checked);'>
                <span>${mate.name}</span>
                <span>量<input type="text" name="${type.typeQuantity}" id="${mate.material_id}" value=""  disabled="disabled"></span>
                <span>${mate.unit}</span>
            </div>

    </c:forEach>
    </div>
</c:forEach>
<script>
    function connecttext(id, ischecked ) {
        if( ischecked == true ) {
            // チェックが入っていたら有効化
            document.getElementById(id).disabled = false;
        } else {
            // チェックが入っていなかったら無効化
            document.getElementById(id).disabled = true;
        }
    }
</script>


<input type="hidden" name="_token" value="${_token}">
<button type="submit" >登録</button>
