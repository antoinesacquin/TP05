<%-- 
    Document   : index
    Created on : 22 mai 2017, 15:04:30
    Author     : Antoine SACQUIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="inc/form.css" />
        <title>Bienvenue</title>
    </head>
    <body>
        <h1>Bienvenue sur notre site!</h1>
        <p>Si vous n'avez pas encore de compte utilisateur,
            veuillez vous inscrire</p>
        <form> 
            <p><input type="submit" formaction="<c:out value="inscription"/>" value="INSCRIPTION"/></p>

            <p>Sinon, veuillez vous connecter</p>
            <p><input type="submit" formaction="<c:out value="connexion"/>" value="CONNEXION"/></p>
        </form>
    </body>
</html>
