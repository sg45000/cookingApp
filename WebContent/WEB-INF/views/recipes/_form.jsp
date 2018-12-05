<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="form_recipe_name">
    <label for="name">レシピ名</label>
    <input type="text" name="name" value="${name}">
</div>
<div class="form_recipe_image">
    <label for="file">写真</label>
    <input type="file" name="file">
</div>
<div class="form_recipe_time">
    <label for="time">調理時間</label>
    <input type="text" name="time" value="${time}">
</div>
<div class="form_recipe_how_many">
    <label for="how_many">何人分</label>
    <input type="text" name="how_many" value="${how_many}">
</div>
<div class="form_recipe_how_to">
    <label for="how_to">レシピ詳細</label>
    <textarea name="how_to">${how_to}</textarea>
</div>


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
