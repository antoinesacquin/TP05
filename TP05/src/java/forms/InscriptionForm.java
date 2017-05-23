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
public class InscriptionForm {

    //Paramètres statiques de la classe
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "motdepasse";
    private static final String CHAMP_CONF = "confirmation";
    private static final String CHAMP_NOM = "nom";

    private static final Integer TAILLE_PWD = 3;
    private static final Integer TAILLE_NOM = 2;

    private static final String MESSAGE_OK = "Succès de l'inscription. Vous êtes maintenant connecté";
    private static final String MESSAGE_NOT_OK = "Échec de l'inscription.";

    //message de succès/echec de la connexion
    private String resultat;

    //liste des erreurs levées lors de la soumission du formulaire de connexion
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur inscrireUtilisateur(HttpServletRequest request) {

        //récupération des paramètres de la requête en inscription
        String email = getValeurChamp(request, CHAMP_EMAIL);
        String motDePasse = getValeurChamp(request, CHAMP_PASS);
        String confirmation = getValeurChamp(request, CHAMP_CONF);
        String nom = getValeurChamp(request, CHAMP_NOM);

        //instanciation d'un objet utilisateur
        Utilisateur utilisateur = new Utilisateur();
        //instanciation d'un objet DAO pour interroger la base de donnée
        UtilisateurDAO utilisateurdao = new UtilisateurDAO();

        //tests de validation des champs et hydratation du bean avec paramètres de requête
        //pour afficher dans le formulaire malgré échec inscription
        try {
            validationEmail(email);
        } catch (Exception e) {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }
        utilisateur.setEmail(email);

        try {
            validationMotsDePasse(motDePasse, confirmation);
        } catch (Exception e) {
            setErreur(CHAMP_PASS, e.getMessage());
        }
        String encryptedPwd= EncryptionForm.getSecurePassword(motDePasse);
        utilisateur.setPassword(encryptedPwd);

        try {
            validationNom(nom);
        } catch (Exception e) {
            setErreur(CHAMP_NOM, e.getMessage());
        }
        utilisateur.setNom(nom);

        if (!erreurs.isEmpty()) {
            resultat = MESSAGE_NOT_OK;
        } else {
            utilisateur = utilisateurdao.create(utilisateur);
            resultat = MESSAGE_OK;
        }


        return utilisateur;
    }

    /**
     * Valide le format de l'Email et vérifie s'il existe déjà dans la base de
     * donnée
     *
     * @param email email à vérifier
     * @throws Exception
     */
    private void validationEmail(String email) throws Exception {

        UtilisateurDAO utilisateurDao = new UtilisateurDAO();

        if (email != null && email.length() != 0) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Merci de saisir une adresse mail valide.");
            } else if (utilisateurDao.find(email) != null) {
                throw new Exception("E-Mail déjà pris!");
            }
        } else {
            throw new Exception("Merci de saisir une adresse mail.");
        }
    }

    /**Vérifie le format du mot de passe et la correspondance avec la confirmation
     * 
     * @param motDePasse
     * @param confirmation
     * @throws Exception 
     */
    private void validationMotsDePasse(String motDePasse, String confirmation) throws Exception {
        if (motDePasse != null && motDePasse.length() != 0 && confirmation != null && confirmation.length() != 0) {
            if (!motDePasse.equals(confirmation)) {
                throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
            } else if (motDePasse.length() < TAILLE_PWD) {
                throw new Exception("Les mots de passe doivent contenir au moins " + TAILLE_PWD + " caractères.");
            }
        } else {
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }
    }

    /**Vérifie le nom d'utilisateur: s'il existe ou qu'il fait la bonne taille minimum
     * 
     * @param nom
     * @throws Exception 
     */
    private void validationNom(String nom) throws Exception {
        if (nom.length() != 0 && nom.length() < TAILLE_NOM) {
            throw new Exception("Le nom d'utilisateur doit contenir au moins " + TAILLE_NOM + " caractères.");
        }
    }

/**Ajoute un message correspondant au champ spécifié à la map des erreurs.
 * 
 * @param champ
 * @param message 
 */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

/**Méthode utilitaire qui retourne null si un champ est vide, et son contenu
 * débarassé des espaces devant et derrière
 * 
 * @param request
 * @param nomChamp
 * @return 
 */
    private String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }
    }

}
