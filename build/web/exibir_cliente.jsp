<%@page import="model.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exibir Clientes</title>
        
        <style>
            table {
                border-collapse: collapse;
            }
            table,td,th {
                border: 1px solid black;
            } 
        </style>
    </head>
    <body>
        <div>
            <div id="conteudo">
                <%
                ArrayList lista = (ArrayList) request.getAttribute("lista");
                %>
                <table id="tabela">
                    <td>Nome: </td>
                    <td>Telefone: </td>
                    <td>Email: </td>
                    <td>Idade: </td>
                    
                    <%
                    for (int indice = 0; indice < lista.size(); indice++) {
                        Cliente cliente = (Cliente) lista.get(indice);
                        
                    %>
                    <tr id="tabela">
                        <td><%=cliente.getNome()%></td>
                        <td><%=cliente.getTel()%></td>
                        <td><%=cliente.getEmail()%></td>
                        <td><%=cliente.getIdade()%></td>
                    </tr>
                    <%
                        }
                    %>    
                </table>
            </div>
        </div>
    </body>
</html>
