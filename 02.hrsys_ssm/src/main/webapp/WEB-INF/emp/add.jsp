<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
    <style>
        #container {
            width: 700px;
            margin: 10px auto;
        }

        #container form .align {
            margin: 8px 0;
        }
    </style>
</head>
<body>

<div id="container">
    <form action="add">
        <div class="align">
            <label>编号</label>
            <input type="text" placeholder="请输入编号"
                   name="number">
        </div>
        <div class="align">
            <label>名字</label>
            <input type="text" placeholder="请输入名字" name="name">
        </div>
        <div class="align">
            <label>性别</label>
            <input type="radio" value="男" name="gender"/>男
            <input type="radio" value="女" name="gender"/>女
        </div>
        <div class="align">
            <label>年龄</label>
            <input type="text" placeholder="请输入年龄" name="age">
        </div>
        <div class="align">
            <label>部门</label>
            <select name="dep.id">
                <c:forEach var="dep" items="${depList }">
                    <option value="${dep.id }">${dep.name }</option>
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