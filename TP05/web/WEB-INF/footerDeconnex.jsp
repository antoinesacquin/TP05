<%-- 
    Document   : footerDisconnection
    Created on : 19 mai 2017, 09:12:13
    Author     : Antoine SACQUIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" href="/inc/form.css">
</head>
<html>
    <body>
        <!-- Footer disconnection is displayed only if user is connected -->
        <c:if test="${!empty sessionScope.sessionUtilisateur}">
                <form> 
                    <input type="submit" formaction="<c:out value="deconnexion"/>" value="DECONNEXION"/>
                </form>
        </c:if>
    </body>
</html>
