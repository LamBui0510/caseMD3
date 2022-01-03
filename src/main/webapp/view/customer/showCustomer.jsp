<%--
  Created by IntelliJ IDEA.
  User: LAM
  Date: 1/2/2022
  Time: 10:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer Menu</title>
</head>
<body>
<div class="container" style="margin-top:30px">
    <div class="row">
        <div class="col-sm-6">
            <h2><i>customer</i></h2>
            <form action="/customer?action=search" method="post">
                <input type="search" name="search">
                <button type="submit">search</button>
            </form>
            <br>
            <table class="table table-striped">
                <thead>
                <tbody>

                <th>stt       </th>
                <td>name       </td>
                <th>ngay_sinh  </th>
                <th>dia_chi   </th>
                <th>sdt        </th>
                <th>email      </th>
                <th>tenlop      </th>
                <th>  <a href="/customer?action=create&id=${p.id}" class="btn btn-success">create</a></th>
                <c:forEach items="${customer}" var="c" varStatus="loop">
                    <tr>
                        <td>${loop.count}</td>
                        <td>${c.name_role}</td>
                        <td>${c.full_name}</td>
                        <td>${c.passwords}</td>
                        <td>${c.email}</td>
                        <td>${c.phone}</td>
                        <td>${c.address}</td>
                        <td>${c.img}</td>
                        <td>${c.create_date}</td>
                        <td>${c.modify_date}</td>


                        <td><a href="/customer?action=edit&id=${p.id}" class="btn btn-success">edit</a></td>
                        <td><a href="/customer?action=delete&id=${p.id}" class="btn btn-danger">delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
                </thead>
            </table>

        </div>
    </div>
</div>

<div class="jumbotron text-center" style="margin-bottom:0">
    <p>Footer</p>
</div>
</body>
</html>
