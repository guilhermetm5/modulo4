<%-- 
    Document   : mensagens
    Created on : 13/03/2024, 18:44:14
    Author     : RYZEN 3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <!--${mensagem}-->
            
            <&
                String msg = (String) request.getAttribute("mensagem");
                out.println(msg);
            &>
        </h1>
    </body>
</html>
