<%-- 
    Document   : registerAsset
    Created on : Jan 25, 2018, 7:25:44 AM
    Author     : ekaranja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Asset</title>
    </head>
    <body>
        <form action="assetController" method="post">
            Asset Name:<br/>
            <input type="text" name="assetName"><br/>
            <input type="submit" value="addAsset" >
        </form>
    </body>
</html>
