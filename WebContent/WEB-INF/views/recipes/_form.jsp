<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="form_recipe_name">
    <label for="レシピ名"></label>
    <input type="text" name="name" value="${name}">
</div>
<div class="form_recipe_image">
    <label for="写真"></label>
    <input type="file" name="file">
</div>
<div class="form_recipe_time">
    <label for="調理時間"></label>
    <input type="text" name="time" value="${time}">
</div>
<div class="form_recipe_how_many">
    <label for="何人分"></label>
    <input type="text" name="how_many" value="${how_many}">
</div>
<div class="form_recipe_how_to">
    <label for="レシピ詳細"></label>
    <textarea name="how_to">${how_to}</textarea>
</div>

<div class="meat">
<span>肉</span>
<c:forEach var="meat_material" items="${meat_materials}">
<div class="material">
    <input type="checkbox" name="meat_id" value="${meat_material.material_id}">
    <span>${meat_material.name}</span>
    <span>量<input type="text" name="meat_quantity"></span>
    <span>${meat_material.unit}</span>
</div>
</c:forEach>
</div>

<div class="fish">
<span>魚</span>
<c:forEach var="fish_material" items="${fish_materials}">
<div class="material">
    <input type="checkbox" name="fish_id" value="${fish_material.material_id}">
    <span>${fish_material.name}</span>
    <span>量<input type="text" name="fish_quantity"></span>
    <span>${fish_material.unit}</span>
</div>
</c:forEach>
</div>

<div class="vegetable">
   <span>野菜</span>
   <c:forEach var="vegetable_material" items="${vegetable_materials}">
<div class="material">
    <input type="checkbox" name="vegetable_id" value="${vegetable_material.material_id}">
    <span>${vegetable_material.name}</span>
    <span>量<input type="text" name="vegetable_quantity"></span>
    <span>${vegetable_material.unit}</span>
</div>
</c:forEach>
</div>

<div class="condiment">
    <span>調味料</span>
    <c:forEach var="condiment_material" items="${condiment_materials}">
<div class="material">
    <input type="checkbox" name="condiment_id" value="${condiment_material.material_id}">
    <span>${condiment_material.name}</span>
    <span>量<input type="text" name="condiment_quantity"></span>
    <span>${condiment_material.unit}</span>
</div>
</c:forEach>
</div>

<input type="hidden" name="_token" value="$[_token]">