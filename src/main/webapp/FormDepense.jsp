<%@ page import="java.util.List" %>
<%@ page import="prevision.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire de depense</title>
</head>
<body>
    <h1>Page d'ajout d'une ligne de depense</h1>
    <form action="depenseServlet" method="post">
        <select name="idcredit">
            <option value=#>Libelle</option>
            <% List<Credit> credits = (List<Credit>) request.getAttribute("credits"); 
                for(Credit c : credits) {
            %>
                <option value="<%= c.getIdcredit() %>"><%= c.getLibelle() %></option>
            <% } %> 
        </select>
        <input type="number" name="montantdepense" placeholder="Montant depense sur ce libelle" width=200> 
        <p>Date de depense : <input type="date" name="datedepense"> </p>
        <button type="submit">Valider</button>
    </form>

    <a href="creditServlet">Retour</a>
</body>
</html>