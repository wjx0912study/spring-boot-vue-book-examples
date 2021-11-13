<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>员工展示</title>
    <style>
        #container {
            width: 700px;
            margin: 10px auto;
        }
        #container #search{
            overflow: hidden;
        }
        #container  #search .align{
            float:left;
            margin-right:8px;
        }
        #container #search input{
            width:160px;
        }
        #container #data {
            clear: both;
            width: 700px;
            margin:10px 0;
        }


    </style>
</head>
<body>
<div id="container">
    <form id="search" action="search" method="post">
        <div class="align">
            <input type="text" name="number"
                   placeholder="编号" value=${c.number}>
        </div>
        <div class="align">
            <input type="text" name="name"
                   placeholder="姓名" value=${c.name}>
        </div>
        <div class="align">
            <select name="gender">
                <option value="">性别</option>
                <option value="男" <c:if test="${c.gender =='男'}"> selected</c:if>>男</option>
                <option value="女" <c:if test="${c.gender =='女'}"> selected</c:if>>女</option>
            </select>
        </div>
        <div class="align">
            <input type="text" name="age" placeholder="年龄"
                   value=${c.age!=null?c.age:''}>
        </div>
        <div class="align">
            <select name="dep.id">
                <option value="">部门</option>
                <c:forEach items="${depList}" var="dep">
                    <option value="${dep.id }"
                            <c:if test="${dep.id ==c.dep.id}"> selected</c:if>>${dep.name }</option>
                </c:forEach>
            </select>
        </div>
        <div class="align">
            <button type="submit">搜索</button>
        </div>

    </form>
    <table id="data" border="1" >
        <tr>
            <th>编号</th>
            <th>名字</th>
            <th>性别</th>
            <th>年龄</th>
            <th>部门</th>

        </tr>
        <c:forEach items="${list}" var="data">
            <tr>
                <td>${data.number }</td>
                <td>${data.name }</td>
                <td>${data.gender }</td>
                <td>${data.age }</td>
                <td>${data.dep.name }</td>
            </tr>
        </c:forEach>
    </table>
    <button type="button" id="add">新增</button>
    <button type="button" id="update">修改</button>
    <button type="button" id="delete">删除</button>
</div>
</body>
</html>