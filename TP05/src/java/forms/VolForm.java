/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import DAO.DAOFactory;
import DAO.VolDAO;
import beans.Vol;
import static java.lang.reflect.Array.set;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Severine Andraud <severine@prechel.fr>
 */
public class VolForm {

    private static final String CHAMP_DEPART = "depart";
//    private Set<String> villesDepart = new

    /* Récupération de la liste des villes de départ de la BD */
    public Set<String> villesDepart() {
        
        VolDAO volDAO = DAOFactory.getVolDAO();
        Set<String> villesDep = new HashSet<>();
        villesDep = volDAO.findAllVillesDeparts();
        return villesDep;
    }

    public Set<Vol> vols(HttpServletRequest request) {
        
        VolDAO volDAO = DAOFactory.getVolDAO();
        Set<Vol> volsDepChoisi = new HashSet<>();
        
        String depart=(String)request.getParameter(CHAMP_DEPART);
        
        volsDepChoisi = volDAO.findAllFromDepart(depart);
        
        return volsDepChoisi;
    }
}
