<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Utente"%>
<%@page import="model.Impasto"%>
<%@page import="model.Ingrediente"%>
<%@page import="model.Pizza"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica Pizza</title>
<style>
body {
  margin-left: 50px;
  font-family: sans-serif;
}

input, button, label {
  margin: 5px;
  padding:5px;
  cursor: pointer;
}
</style>
</head>
<body>
<% Utente utente = (Utente) session.getAttribute("utenteTrovato");%>
<% Pizza pizza = (Pizza) request.getAttribute("pizza");%>

  <h2>Modifica Pizza</h2>  
  <form method="POST" action="/PIZZERIA/UpdatePizza">
    <div>
      <h3>Modifica impasto</h3>
      <% ArrayList<Impasto> listaImpasti  = (ArrayList<Impasto>) request.getAttribute("listaImpasti");
      for(Impasto impasto: listaImpasti){ %>
        <div>
         <label>
         <% if(pizza.getImpasto().getId() == impasto.getId()){%>
          <input type="radio" name="idImpasto" value="<%=impasto.getId()%>" checked/>
         <% } else { %>
          <input type="radio" name="idImpasto" value="<%=impasto.getId()%>" />
         <%}%>
         <%= impasto.getNome()%></label>
        </div>
      <%} %>
    </div>
    <div>
      <h3>Modifica ingredienti</h3>
      <% 
      ArrayList<Ingrediente> listaIngredienti  = (ArrayList<Ingrediente>) request.getAttribute("listaIngredienti");
      for(Ingrediente ingrediente: listaIngredienti){ %>
        <div> 
        <label>
          <%if(pizza.getListaIngredienti().contains(ingrediente)){ %>
            <input type="checkbox" name="idIngredienti" value="<%= ingrediente.getId()%>" checked/>
          <%} else { %>
            <input type="checkbox" name="idIngredienti" value="<%= ingrediente.getId()%>"/>
          <%}%>
         <%= ingrediente.getNome()%></label>
        </div>
      <%} %>
    </div>
    <h3>Modifica nome pizza</h3>
    <input type="text" name="nuovoNome" value="<%=pizza.getNome() %>"/>
    <input type="hidden"  name="idPizza" value="<%=pizza.getId() %>"/>
    <button type="SUBMIT" name="idPizza" value="<%=pizza.getId()%>" style="background-color: #74ff94">Aggiorna Pizza</button> 

  </form>

</body>
</html>