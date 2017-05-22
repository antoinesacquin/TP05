/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

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
@WebServlet(name = "Deconnexion", urlPatterns = {"/deconnexion"})
public class Deconnexion extends HttpServlet {

    public static final String URL_REDIRECTION = "/connexion";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Récupération et destruction de la sess
ion en cours */
        HttpSession session = request.getSession();
        session.invalidate();
        /* Redirection vers le formulaire de connexion */

        response.sendRedirect(getServletContext().getContextPath() + URL_REDIRECTION);
    }
}
