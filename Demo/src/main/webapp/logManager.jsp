<%@ page import="model.Log" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.LogDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log Manager</title>

    <!-- Bootstrap CSS -->
    <link href="mdb/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery -->
    <script src="mdb/js/jquery.min.js"></script>

    <!-- DataTables CSS -->
    <link href="mdb/css/addons/datatables.min.css" rel="stylesheet">

    <!-- DataTables JS -->
    <script src="mdb/js/addons/datatables.min.js"></script>


</head>
<body>
<% List<Log> logList = LogDao.getAllLogs(); %>
<div class="container mt-4">
    <h2>Log Manager</h2>
    <table id="logTable" class="display" style="width:100%">
        <thead>
        <tr>
            <th>ID</th>
            <th>Level</th>
            <th>Table</th>
            <th>Previous Value</th>
            <th>Current Value</th>
            <th>Date/Time</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>

        <% for(Log log : logList) { %>
        <tr id="logRow_<%=log.getId()%>">
            <td><%=log.getId()%></td>
            <td><%=log.getLevel()%></td>
            <td><%=log.getTable()%></td>
            <td><%=log.getPreValue()%></td>
            <td><%=log.getValue()%></td>
            <td><%=log.getDateTime()%></td>
            <td>
                <button class="btn btn-danger" onclick="deleteLog(<%=log.getId()%>)">Delete</button>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
<script>
    $(document).ready(function() {
        $('#logTable').DataTable();
    });

    function deleteLog(logId) {
        $.ajax({
            url: "./deleteLog", // Assuming the servlet or controller to handle deletion
            method: "POST",
            data: { id: logId },
            success: function(response) {
                $('#logRow_' + logId).remove(); // Remove the corresponding row from the table
            },
            error: function(xhr, status, error) {
                console.log("Error deleting log: " + error);
            }
        });
    }
</script>
</body>
</html>
