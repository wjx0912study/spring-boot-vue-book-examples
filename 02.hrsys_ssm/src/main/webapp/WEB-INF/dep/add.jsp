<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<html>
<head>
    <title>部门新增</title>

    <style>
        #container {
            width: 400px;
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
            <input type="text" placeholder="请输入编号" name="number">
        </div>
        <div class="align">
            <label>名称</label>
            <input type="text" placeholder="请输入名称" name="name">
        </div>
        <div class="align">
            <button type="submit">保存</button>
        </div>
    </form>
</div>
</body>
</html>