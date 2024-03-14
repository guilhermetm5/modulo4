<%-- Criado por Gui --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exemplo Insert</title>
    </head>
    <body>
        <h1>Exemplos Insert!</h1>
        
        <<form action="Controle" method="post">
            <input type="hidden" name="flag" value="salvarcli"/>
            <p>Nome: <input type="text" name="nome" size="30"/></p>
            <p>Telefone: <input type="text" name="tel" size="15"/></p>
            <p>Idade: <input type="text" name="idade" size="8"/></p>
            <p>E-mail: <input type="text" name="email" size="15"/></p>
            <p><input type="submit" value="Enviar"/><input type="reset" value="Reset"/> </p>
        </form>
        <p>
            <a href="consultar_cliente.jsp">Consulte aqui os Clientes cadastrados!!!</a>
        </p>
    </body>
</html>
