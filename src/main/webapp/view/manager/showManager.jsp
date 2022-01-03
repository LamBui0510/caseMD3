<%--
  Created by IntelliJ IDEA.
  User: LAM
  Date: 1/2/2022
  Time: 10:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manager Menu</title>
</head>
<body>

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
    <th>  <a href="/manager?action=create&id=${p.id}" class="btn btn-success">create</a></th>
    <c:forEach items="${manager}" var="m" varStatus="loop">
        <tr>
            <td>${loop.count}</td>
            <td>${m.user_name}</td>
            <td>${m.passwords}</td>
            <td>${m.email}</td>
            <td>${m.phone}</td>
            <td>${m.address}</td>
            <td>${m.img}</td>
            <td>${m.salary}</td>
            <td>${m.status}</td>
            <td>${m.create_date}</td>
            <td>${m.modify_date}</td>
            <td>${m.name_role}</td>

            <td><a href="/manager?action=edit&id=${p.id}" class="btn btn-success">edit</a></td>
            <td><a href="/manager?action=delete&id=${p.id}" class="btn btn-danger">delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
    </thead>

 </table>
</body>
</html>
