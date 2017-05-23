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
    <body class="connect">
        <h1>Bonjour <c:out value="${sessionScope.sessionUtilisateur.nom}"/></h1>

        <p>Vous êtes maintenant connecté!!!<br/></p>   
        
        <h3> Veuilllez choisir votre destination </h3>

        <form method="post" action="<c:url value="pagePrincipale"/>">
            <select name="depart" >
                <c:forEach items="${requestScope.villesDepart}" var="ville">
                    <option><c:out value="${ville}"/></option>  
                </c:forEach>
            </select>
            <input type="submit" formaction="<c:url value="pagePrincipale"/>" value="Valider"/>
        </form>
        <c:if test="${!empty param.depart}"> <table>
                <tr>
                    <th>Arrivée</th>
                    <th>Jour de départ</th>
                    <th>Heure de départ</th>
                    <th>Jour d'arrivée</th>
                    <th>Heure d'arrivée</th>
                </tr>

                <c:forEach items="${requestScope.vols}" var="vol">
                    <tr>  
                        <th><c:out value="${vol.arrivee}"/></th>
                        <th><c:out value="${vol.jourDepart}"/></th>
                        <th><c:out value="${vol.heureDepart}"/></th>
                        <th><c:out value="${vol.jourArrivee}"/></th>
                        <th><c:out value="${vol.heureArrivee}"/></th>
                    </tr> 
                </c:forEach>
            </table></c:if>
    </body>
</html>
