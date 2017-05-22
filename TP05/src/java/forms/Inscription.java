/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import beans.Utilisateur;
import forms.InscriptionForm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stag
 */
@WebServlet(name = "Inscription", urlPatterns = {"/inscription"})
public class Inscription extends HttpServlet {

    /* Des constantes */
    private static final String VUE = "/WEB-INF/inscription.jsp";
    private static final String ATT_FORM="form";
    private static final String ATT_USER="utilisateur";
   

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Affichage de la page d'inscription */
        this.getServletContext()
                .getRequestDispatcher(VUE)
                .forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InscriptionForm form=new InscriptionForm();
        
        Utilisateur utilisateur=form.inscrireUtilisateur(request);
        
        request.setAttribute(ATT_FORM, form);
        request.setAttribute(ATT_USER, utilisateur);
        
        /* Transmission de la paire d'objets request/response à notre JSP */
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

}
