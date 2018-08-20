<%-- 
    Document   : errorpage
    Created on : Apr 17, 2018, 11:20:05 AM
    Author     : ekaranja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Block Chain App Errors</title>
    </head>
    <body>
        <%
         String loginMessage = (String) (session.getAttribute("loginMessage"));
            if (loginMessage !=null) {
                out.print(loginMessage);
            }
        %>
      
    </body>
</html>
