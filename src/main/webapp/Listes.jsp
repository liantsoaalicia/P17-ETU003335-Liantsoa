<%@ page import="java.util.List" %>
<%@ page import="prevision.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des depenses</title>
</head>
<body>

    <h1>Liste de tous les credits</h1>
        <table width=700 border=1>
        <tr>
            <th>Libelle</th>
            <th>Montant</th>
            <th>Date de debut</th>
            <th>Date de fin</th>
        </tr>
        
        <% List<Credit> credits = (List<Credit>) request.getAttribute("credits");
           for(Credit c : credits) {  
        
        %>
            <tr> 
                <td><%= c.getLibelle() %></td>
                <td><%= c.getMontant() %></td>
                <td><%= c.getDatedebut() %></td>
                <td><%= c.getDatefin() %></td>
            </tr>
        <% } %>

    </table>

    <h1>Liste de toutes les depenses</h1>
        <table width=700 border=1>
        <tr>
            <th>Credit</th>
            <th>Montant depense</th>
            <th>Date de depense</th>
        </tr>
        
        <% List<Depense> depenses = (List<Depense>) request.getAttribute("depenses");
           for(Depense d : depenses) {  
        
        %>
            <tr> 
                <td><%= Credit.findById(d.getIdcredit()).getLibelle() %></td>
                <td><%= d.getMontantdepense() %></td>
                <td><%= d.getDatedepense() %></td>
            </tr>
        <% } %>

    </table>

    <a href="creditServlet">Retour</a>
</body>
</html>