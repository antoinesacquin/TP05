<%-- 
    Document   : header
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
        <!-- Header is displayed only if user is not connected -->
            <form>   
                <c:if test="${empty sessionScope.sessionUtilisateur}">
                    <input type="submit" formaction="<c:out value="inscription"/>" value="INSCRIPTION"/>
                    <input type="submit" formaction="<c:out value="connexion"/>" value="CONNEXION"/>
                </c:if>
            </form>
    </body>
</html>
