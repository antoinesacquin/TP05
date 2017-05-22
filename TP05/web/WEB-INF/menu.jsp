<%-- 
    Document   : menu
    Created on : 19 mai 2017, 09:12:13
    Author     : stag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" href="/inc/form.css">
</head>
<html>
    <body>
        <form>
            <input type="submit" formaction="<c:out value="connexion"/>" value="CONNEXION"/>
            <input type="submit" formaction="<c:out value="deconnexion"/>" value="DECONNEXION"/>
        </form>

    </body>
</html>
