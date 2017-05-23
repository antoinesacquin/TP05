/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Utilisateur;
import forms.InscriptionForm;
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
@WebServlet(name = "Inscription", urlPatterns = {"/inscription"})
public class Inscription extends HttpServlet {

    /* Des constantes */
    private static final String VUE = "/WEB-INF/inscription.jsp";
    private static final String LIEN_CONNECTE = "/pagePrincipale";
    private static final String ATT_FORM = "form";
    private static final String ATT_USER = "utilisateur";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        if (null !=session.getAttribute(ATT_SESSION_USER)) {
            response.sendRedirect(getServletContext().getContextPath() + LIEN_CONNECTE);
        }else {
            session.setAttribute(ATT_SESSION_USER, null);
        
        this.getServletContext()
                .getRequestDispatcher(VUE)
                .forward(request, response);}
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InscriptionForm form = new InscriptionForm();

        Utilisateur utilisateur = form.inscrireUtilisateur(request);

        HttpSession session = request.getSession();

        /*
         * Si aucune erreur de validation n'a eu lieu, alors l'utilisateur est 
        *connecté, le bean Utilisateur issu de la base de donnée est ajouté à la session 
        *et l'utilisateur est transféré vers une page spécifique
        *
        *sinon suppression du bean de la session.
         */
        if ((form.getErreurs().isEmpty()) || (null !=session.getAttribute(ATT_SESSION_USER))) {
            session.setAttribute(ATT_SESSION_USER, utilisateur);
            response.sendRedirect(getServletContext().getContextPath() + LIEN_CONNECTE);
        } else {
            session.setAttribute(ATT_SESSION_USER, null);

            request.setAttribute(ATT_FORM, form);
            request.setAttribute(ATT_USER, utilisateur);

            /* Transmission de la paire d'objets request/response à notre JSP */
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }

    }

}
