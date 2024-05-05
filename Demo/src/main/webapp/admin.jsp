<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="DAO.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<%
    List<User> userList = UserDAO.listUser();
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="mdb/css/bootstrap.min.css" rel="stylesheet">
    <script src="mdb/js/bootstrap.bundle.min.js"></script>
    <script src="mdb/js/jquery.min.js"></script>
    <link href="mdb/css/addons/datatables.min.css" rel="stylesheet">
    <script src="mdb/js/addons/datatables.min.js"></script>
</head>
<body>
<table id="example" class="display" style="width:100%">
    <thead>
    <tr>
        <th>Id</th>
        <th>name</th>
        <th>email</th>
        <th>action</th>
    </tr>
    </thead>
    <tbody>
        <% for(User user : userList) {%>
        <tr>
            <th><%=user.getId()%></th>
            <th><%=user.getName()%></th>
            <th><%=user.getEmail()%></th>
            <th><button class="btn btn-danger" onclick="deleteRow(this, <%=user.getId()%>)">Xóa</button> </th>
        </tr>
    <%}%>
    </tbody>
</table>
</body>

<script>


    $(document).ready(function () {
        $('#example').DataTable();
    })

    function deleteRow(btn, id) {
        $.ajax({
            url: "./delete",
            method: "POST",
            data:{
                id: id
            },
            success: function (response) {
                btn.closest("tr").remove();
            }
        })
    }
</script>
</html>
