<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<style>
body {
	font-family: sans-serif;
}

table {
	display: inline-block;
	margin-left: 50px;
	font-size: 20px;
	margin-bottom: 20px;
}

input, button {
	cursor: pointer;
}
.save {
	margin-left: 50px;
	margin-bottom: 20px;
}
</style>
</head>
<body>
  <form method="POST" action="/PIZZERIA/PizzaServlet">
    <% 
      Utente utente = (Utente) session.getAttribute("utenteTrovato");
      List<Impasto> listaImpasti = (List<Impasto>) request.getAttribute("listaImpasti");
      List<Ingrediente> listaIngredienti = (List<Ingrediente>) request.getAttribute("listaIngredienti");
      List<Pizza> listaPizze = (List<Pizza>) request.getAttribute("listaPizze");
    %>
    <h2 align="center">Bentornato <%=utente.getUsername()%>!</h2>
    <table border="1" align="center" cellpadding="3" cellspacing="4">
      <thead>
        <tr bgcolor="eab676">
          <th></th>
          <th>Seleziona l'impasto</th>
        </tr>
      </thead>
      <tbody>
        <%
          for (Impasto impasto : listaImpasti) {
        %>
        <tr>
          <td><input type="radio" name="idImpasto"
            style="transform: scale(1.5);" value="<%=impasto.getId()%>"></td>
          <td><%=impasto.getNome()%></td>
        </tr>
        <%}%>

      </tbody>
    </table>

    <table border="1" align="center" cellpadding="3" cellspacing="4">
      <thead>
        <tr bgcolor="1874a5">
          <th></th>
          <th>Seleziona ingredienti</th>
        </tr>
      </thead>
      <tbody>
        <%
          for (Ingrediente ingrediente : listaIngredienti) {
        %>
        <tr>
          <td><input type="checkbox" name="idIngredienti"
            style="transform: scale(1.5);"
            value="<%=ingrediente.getId()%>"></td>
          <td><%=ingrediente.getNome()%></td>
        </tr>
        <%}%>
      </tbody>
    </table>
    <div class="save">
      <label for="nomePizza" style="font-size: 16px">Nome pizza:</label>
      <input type="text" id="nomePizza" name="nomePizza">
      <button type="SUBMIT" name="CreaPizza" value="DiversoDaNull" style="font-size: 16px;">Salva</button>
    </div>
  </form>

  <form method="POST" action="/PIZZERIA/PizzaServlet">
    <table border="1" align="center" cellpadding="3" cellspacing="4">
      <thead>
        <tr bgcolor="efc000">
          <th>Nome Pizza</th>
          <th>Impasto</th>
          <th>Ingredienti</th>
          <th>Azioni</th>
        </tr>
      </thead>
      <tbody>
        <%
          for (Pizza pizza : listaPizze) {
        %>
        <tr>
          <td><%=pizza.getNome()%></td>
          <td><%=pizza.getImpasto().getNome()%></td>
          <td>
            <%List<Ingrediente> listaIngr = (List<Ingrediente>) pizza.getListaIngredienti();
            for (Ingrediente ingr : listaIngr) { %> 
              <%=ingr.getNome()%> 
            <%}%>
          </td>
          <td>
              <a style="background-color: #84e2ff" href="<%=request.getContextPath() %>/EditPizza?idPizza=<%=pizza.getId()%>">
                Modifica
              </a>
            <button type="SUBMIT" name="CancellaPizza" style="background-color: #ff7474"
              value="<%=pizza.getId()%>">Cancella</button>
          </td>
        </tr>
        <%
          }
        %>
      </tbody>
    </table>
  </form>

</body>
</html>