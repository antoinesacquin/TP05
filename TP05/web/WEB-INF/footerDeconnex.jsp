<%-- 
    Document   : footerDeconnex
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
        <c:if test="${!empty sessionScope.sessionUtilisateur}">
                <form> 
                    <input type="submit" formaction="<c:out value="deconnexion"/>" value="DECONNEXION"/>
                </form>
        </c:if>
    </body>
</html>
