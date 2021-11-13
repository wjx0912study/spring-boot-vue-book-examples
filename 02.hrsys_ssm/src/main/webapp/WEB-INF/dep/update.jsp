<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>部门修改</title>
    <style>
        #container {
            width: 600px;
            margin: 10px auto;
        }
        #container form .align {
            margin: 8px 0;
        }
    </style>
</head>
<body>
<div id="container">
    <form  action="update">
        <input type="hidden" name="id" value="${dep.id}">
        <div class="align">
            <label>编号</label>
            <input type="text" placeholder="请输入编号" name="number" value="${dep.number}">
        </div>
        <div class="align">
            <label>名称</label>
            <input type="text" placeholder="请输入名称"
                   name="name" value="${dep.name}">
        </div>
        <div class="align">
            <button type="submit">保存</button>
        </div>
    </form>
</div>
</body>
</html>