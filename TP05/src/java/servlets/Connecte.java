/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Vol;
import forms.VolForm;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antoine SACQUIN
 */
@WebServlet(name = "Connecte", urlPatterns = {"/connecte"})
public class Connecte extends HttpServlet {

    private static final String VUE="/WEB-INF/connecte.jsp";
    private static final String ATT_VILLES="villesDepart";
    private static final String ATT_VOLS="vols";

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        VolForm form=new VolForm();
        
        Set<String> villesDepart=form.villesDepart();
        
        request.setAttribute(ATT_VILLES, villesDepart);
        
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        VolForm form=new VolForm();
        
        Set<String> villesDepart=form.villesDepart();
        
        
        
        Set<Vol> vols=form.vols(request);
        request.setAttribute(ATT_VOLS, vols);
        request.setAttribute(ATT_VILLES, villesDepart);
        
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

}
