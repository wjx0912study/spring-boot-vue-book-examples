<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>修改员工</title>
    <style>
        #container {
            width: 700px;
            margin: 10px auto;
        }
        #container form .align{
            margin:8px 0;
        }
    </style>
</head>
<body>

<div id="container">
    <form  action="update">
        <input type="hidden" name="id" value="${emp.id}">
        <div class="align">
            <label>编号</label>
            <input type="text" placeholder="请输入编号"
                   name="number" value="${emp.number}">
        </div>
        <div class="align">
            <label>名字</label>
                <input type="text" placeholder="请输入名字"
                       name="name" value="${emp.name}">
        </div>
        <div class="align">
            <label>性别</label>
                <input type="radio" value="男" name="gender"
                       <c:if test="${emp.gender=='男'}">checked</c:if> />男
                <input type="radio" value="女" name="gender"
                    <c:if test="${emp.gender=='女'}">checked</c:if> />女
        </div>
        <div class="align">
            <label>年龄</label>
                <input type="text"  placeholder="请输入年龄" name="age" value="${emp.age}">
        </div>
        <div class="align">
            <label>部门</label>
                <select name="dep.id">
                    <c:forEach var="dep" items="${depList }">
                        <option value="${dep.id }"
                               <c:if test="${emp.dep.id==dep.id}"> selected</c:if> >${dep.name }</option>
                    </c:forEach>
                </select>
        </div>
            <div class="align">
                <button type="submit">保存</button>
            </div>
    </form>
</div>
</body>
</html>