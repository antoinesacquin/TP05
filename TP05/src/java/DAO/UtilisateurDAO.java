/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Utilisateur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Severine Andraud <severine@prechel.fr>
 */
public class UtilisateurDAO {

    private final String TABLE = "inscrits";

    @Override
    public Utilisateur find(Long id) {
        Utilisateur user = null;
        try {
            String req = "SELECT * FROM " + TABLE + " WHERE id = ?";
//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setLong(1, id);
            ResultSet result = pstmt.executeQuery();
            if (result.first()) {
                user = new Utilisateur(id,
                        result.getString("email"),
                        result.getInt("password"),
                        result.getInt("nom"),
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return user;
    }

    @Override
    public Utilisateur create(Utilisateur obj) {
        try {
            String req = "INSERT INTO " + TABLE + " (email, password, nom) VALUES(?, ?, ?)";
//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getType());
            pstmt.setInt(2, obj.getCapacity());
// On soumet la requête et on récupère le nombre d'id créés
            int id = pstmt.executeUpdate();
// On pourrait s'arrêter là, mais je préfère récupérer la ligne créée
// Ca permet de savoir ce qu'on a réellement mis dans la DB
            ResultSet rs = pstmt.getGeneratedKeys();
            long last_inserted_id;
            if (rs.first()) { // Si on a des id créés
                last_inserted_id = rs.getInt(1);
// On récupère l'enregistrement créé
                obj = this.find(last_inserted_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AvionDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return obj;
    }

    @Override
    public Utilisateur update(Avion obj) {
        try {
            String req = "UPDATE " + TABLE + " SET type = ?, " + "capacite = ? WHERE id = ?";
//
            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setString(1, obj.getType());
            pstmt.setInt(2, obj.getCapacity());
            pstmt.setLong(3, obj.getId());
            pstmt.executeUpdate();
// On récupère l'enregistrement modifié
            obj = this.find(obj.getId());
        } catch (SQLException ex) {
            Logger.getLogger(AvionDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return obj;
    }

    @Override
    public void delete(Avion obj) {
        try {
            String req = "DELETE FROM " + TABLE + " WHERE id = ?";

//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setLong(1, obj.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AvionDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

}
