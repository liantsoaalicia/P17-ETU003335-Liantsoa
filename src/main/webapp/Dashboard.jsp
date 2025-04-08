<%@ page import="java.util.List" %>
<%@ page import="prevision.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
</head>
<body>
    <h1>Dashboard</h1>
    <table width=700 border=1>
        <tr>
            <th>Ligne de credit</th>
            <th>Montant depense</th>
            <th>Reste</th>
        </tr>
        
        <% List<Credit> credits = (List<Credit>) request.getAttribute("credits");
           for(Credit c : credits) {  
        
            float depense = c.getMontantDepense((int)session.getAttribute("id"));
            float reste = c.getReste((int)session.getAttribute("id"));
        %>
            <tr> 
                <td><%= c.getLibelle() %></td>
                <td><%= depense %></td>
                <td><%= reste %></td>
            </tr>
        <% } %>

    </table>

    <a href="creditServlet">Retour</a>
</body>
</html>