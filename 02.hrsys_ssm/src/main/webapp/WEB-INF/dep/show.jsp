<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>部门展示</title>
    <style>
        #container {
            width: 400px;
            margin: 10px auto;
        }
        #container #data {
            clear: both;
            width: 400px;
            margin: 10px 0;
        }
    </style>
</head>
<body>

<div id="container">
    <table id="data" border="1px">
        <tr>
            <th>编号</th>
            <th>名称</th>
        </tr>
        <c:forEach items="${list}" var="data">
            <tr>
                <td>${data.number }</td>
                <td>${data.name }</td>
            </tr>
        </c:forEach>
    </table>
    <button type="button" class="btn btn-primary" id="add">新增</button>
    <button type="button" class="btn btn-primary" id="update">修改</button>
    <button type="button" class="btn btn-danger" id="delete">删除</button>
</div>
</body>
</html>