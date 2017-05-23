/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import DAO.UtilisateurDAO;
import beans.Utilisateur;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author stag
 */
public final class ConnexionForm {

    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "motdepasse";
    private String resultat;
    private Map<String, String> erreurs = new HashMap<>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur connecterUtilisateur(HttpServletRequest request) {
        
        /* Récupération des champs du formulaire */
        String email = getValeurChamp(request, CHAMP_EMAIL);
        String motDePasse = getValeurChamp(request, CHAMP_PASS);
        
        
        Utilisateur utilisateur = new Utilisateur();
        UtilisateurDAO utilisateurDao = new UtilisateurDAO();
        
        
        /* Validation du champ email. */
        try {
            validationEmail(email);
        } catch (Exception e) {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }
        utilisateur.setEmail(email);

        /* Validation du champ mot de passe. */
        try {
            validationMotDePasse(email, motDePasse);

        } catch (Exception e) {
            setErreur(CHAMP_PASS, e.getMessage());
        }

        /* Initialisation du résultat global de la validation. */
        if (erreurs.isEmpty()) {
            utilisateur = utilisateurDao.find(email);
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }
        return utilisateur;
    }

    /**
     * Valide si l'Email existe déjà dans la base de donnée
     *
     * @param email email à vérifier
     * @throws Exception
     */
    private void validationEmail(String email) throws Exception {

        UtilisateurDAO utilisateurDao = new UtilisateurDAO();

        if (email == null) {
            throw new Exception("Merci de saisir une adresse mail.");
        } else if (utilisateurDao.find(email) == null) {
            throw new Exception("E-Mail inconnu");
        }
    }

    /**Vérifie le couple email/motDePasse avec le couple email/password 
     * correpondant dans la base de donnée
     * 
     * @param email
     * @param motDePasse
     * @throws Exception 
     */
    private void validationMotDePasse(String email, String motDePasse) throws Exception {

        UtilisateurDAO utilisateurDao = new UtilisateurDAO();

        String tablePwd = utilisateurDao.find(email).getPassword();

        if (motDePasse == null) {
            throw new Exception("Merci de saisir votre mot de passe.");
        } else if (!tablePwd.equals(motDePasse)) {
            throw new Exception("Mot de Passe invalide");
        }
    }

    /**
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     *
     * @param champ
     * @param message
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    /**
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * débarassé des espaces devant et derrière
     *
     * @param request
     * @param nomChamp
     * @return
     */
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
    }
}
