<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="../../layout/app.jsp">
	<c:param name="content">
		<c:if test="${hasError==true}">
			<p>メールアドレスかパスワードが間違っています。</p>
		</c:if>


		<div class="login_form">
			<h2>cookingAppへログイン</h2>
			<form method="POST" action="<c:url value='/login'/>">
				<input type="text" name="email" class="login_input" value="${email}" placeholder="メールアドレスを入力" /><br> <br>
			    <input type="password" name="plain_pass" class="login_input" placeholder="パスワードを入力" /><br> <br>
			    <input type="hidden" name="_token" value="${_token}" />
				<input type="submit" value="ログイン" class="login_submit">
			</form>
			<h2>or</h2>
			<h2>ユーザー新規登録</h2>
			<form method="POST" action="<c:url value='/users/create'/>">
				<c:if test="${errors!=null}">
					<c:forEach var="error" items="${errors}">
						<p>
							<c:out value="${error}" />
						</p>
					</c:forEach>
				</c:if>

				<input type="text" name="name" value="${user.name}" placeholder="アカウント名を入力" class="login_input" /><br><br>
				<input type="email" name="email" value="${user.email}" placeholder="メールアドレスを入力" class="login_input" /><br><br>
				<input type="password" name="plain_pass" value="${user.password}" placeholder="パスワードを入力" class="login_input" /><br><br>
				<input type="password" name="plain_pass_confirm" placeholder="パスワードを再入力" class="login_input"><br><br>
				<input type="hidden" name="_token" value="${_token}" /><br>
			    <button type="submit" name="submit" class="login_submit" >新規登録</button>
			</form>
		</div>

	</c:param>
</c:import>