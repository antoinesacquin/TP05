<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Inscription</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/form.css"/>" />
    </head>
    <body>
        <form method="post" action="inscription">
            <fieldset>
                <legend>Inscription</legend>
                <p>Vous pouvez vous inscrire via ce formulaire.</p>
                <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="text" value="<c:out value="${requestScope.utilisateur.email}"/>" id="email" name="email" value="" size="20" maxlength="60" />
                <span class="erreur"><c:out value="${requestScope.form.erreurs.email}"/></span>
                <br />
                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value=""size="20" maxlength="20" />
                <span class="erreur"><c:out value="${requestScope.form.erreurs.motdepasse}"/></span>
                <br />
                <label for="confirmation">Confirmation <span class="requis">*</span></label>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                <br />
                <label for="nom">Nom d'utilisateur</label>
                <input type="text" value="<c:out value="${requestScope.utilisateur.nom}"/>" id="nom" name="nom" value="" size="20" maxlength="20" />
                <span class="erreur"><c:out value="${requestScope.form.erreurs.nom}"/></span>
                <br />
                <input type="submit" value="Inscription" class="sansLabel" />
                <p>Les champs marqués d'un <span class="requis">*</span> sont obligatoires.</p>

                <p class="${empty requestScope.form.erreurs ? 'succes' : 'erreur'}"><c:out value="${requestScope.form.resultat}"/></p>
            </fieldset>
        </fieldset>
    </form>
</body>
</html>