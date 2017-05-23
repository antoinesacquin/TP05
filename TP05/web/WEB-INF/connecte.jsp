<%-- 
    Document   : connecté
    Created on : 22 mai 2017, 22:33:01
    Author     : Antoine SACQUIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenue</title>
        <link type="text/css" rel="stylesheet" href="inc/form.css" />
    </head>
    <body>
        <h1>Bonjour <c:out value="${sessionScope.sessionUtilisateur.nom}"/></h1>
        
        <p>VOUS ÊTES MAINTENANT CONNECTE!!!<br/>
        PASSEZ UNE AGREABLE SESSION AVEC NOUS!</p>     
            
    </body>
</html>
