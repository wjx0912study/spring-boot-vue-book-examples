<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <link rel="stylesheet"
          href="../bootstrap/css/bootstrap.min.css">

    <style>
        #container {
            width: 600px;
            margin: 10px auto;
        }

        #container .selected {
            background: #337ab7
        }
    </style>
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script>
        $(document).ready(function () {

            $("#add").click(function () {

                location.href = "showAdd";
            })
            var id = -1;
            $(".data").click(function () {
                $(".data").removeClass("selected");
                $(this).addClass("selected");
                id = $(this).data("id");
            })
            $("#update").click(function () {
                if (id > -1) {
                    location.href = "showUpdate?id=" + id;
                } else {
                    alert("请选中数据");
                }
            })
            $("#delete").click(function () {
                if (id > -1) {
                    location.href = "delete?id=" + id;
                } else {
                    alert("请选中数据");
                }
            })
        })
    </script>
</head>
<body>

<div id="container">
    <table class="table table-striped table-bordered table-hover">
        <tr>
            <th>编号</th>
            <th>名称</th>
        </tr>
        <c:forEach items="${list}" var="data">
            <tr class="data" data-id="${data.id }">
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