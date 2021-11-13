<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

<style>
#container {
	width: 600px;
	margin: 10px auto;
}
</style>
<script type="text/javascript" src="../js/jquery.js"></script>


</head>
<body>

	<div id="container">
		<form class="form-horizontal" action="update">
			<input type="hidden" name="id" value="${emp.id}">
            <div class="form-group">
                <label class="col-sm-2 control-label">编号</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="请输入编号" name="number" value="${emp.number}">
                </div>
            </div>
			<div class="form-group">
				<label class="col-sm-2 control-label">名字</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" placeholder="请输入名字"
						name="name" value="${emp.name}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">性别</label>
				<div class="col-sm-10">
					<input type="radio" value="男" name="gender"
						<c:if test="${emp.gender=='男'}">checked</c:if> />男 <input
						type="radio" value="女" name="gender"
						<c:if test="${emp.gender=='女'}">checked</c:if> />女
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">年龄</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" placeholder="请输入年龄"
						name="age" value="${emp.age}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">部门</label>
				<div class="col-sm-10">
					<select class="form-control" name="dep.id">
						<c:forEach var="dep" items="${depList }">
							<option value="${dep.id }"  <c:if test="${emp.dep.id==dep.id}"> selected</c:if> >${dep.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">保存</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>