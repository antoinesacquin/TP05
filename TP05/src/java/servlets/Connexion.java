/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Utilisateur;
import forms.ConnexionForm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author stag
 */
@WebServlet(name = "Connexion", urlPatterns = {"/connexion"})
public class Connexion extends HttpServlet {

    public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE = "/WEB-INF/connexion.jsp";
    public static final String LIEN_CONNECTE = "/connecte";

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        if (null !=session.getAttribute(ATT_SESSION_USER)) {
            response.sendRedirect(getServletContext().getContextPath() + LIEN_CONNECTE);
        }else {
            session.setAttribute(ATT_SESSION_USER, null);
        
        /* Affichage de la page de connexion */
        this.getServletContext()
                .getRequestDispatcher(VUE)
                .forward(request, response);}
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        /* Préparation de l'objet formulaire */
        ConnexionForm form = new ConnexionForm();

        /* Traitement de la requête et récupération du bean en résultant */
        Utilisateur utilisateur = form.connecterUtilisateur(request);
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si aucune erreur de validation n'a eu lieu ou que l'on est déjà en session, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        if ((form.getErreurs().isEmpty()) || (null !=session.getAttribute(ATT_SESSION_USER))) {
            session.setAttribute(ATT_SESSION_USER, utilisateur);
            response.sendRedirect(getServletContext().getContextPath() + LIEN_CONNECTE);
        } else {
            session.setAttribute(ATT_SESSION_USER, null);

            /* Stockage du formulaire et du bean dans l'objet request */
            request.setAttribute(ATT_FORM, form);
            request.setAttribute(ATT_USER, utilisateur);
            this.getServletContext()
                    .getRequestDispatcher(VUE).
                    forward(request, response);
        }
    }
}
