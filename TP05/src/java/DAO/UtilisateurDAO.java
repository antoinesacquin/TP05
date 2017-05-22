/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Utilisateur;
import connection.Singleton;
import java.sql.Connection;
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
    Connection connection = Singleton.getInstance();

    public Utilisateur find(Integer id) {
        Utilisateur user = null;
        try {
            String req = "SELECT * FROM " + TABLE + " WHERE id = ?";
//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();
            if (result.first()) {
                user = new Utilisateur(id,
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("nom")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return user;
    }

    public Utilisateur create(Utilisateur obj) {
        try {
            String req = "INSERT INTO " + TABLE + " (email, password, nom) VALUES(?, ?, ?)";
//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getEmail());
            pstmt.setString(2, obj.getPassword());
            pstmt.setString(3, obj.getNom());
// On soumet la requête et on récupère le nombre d'id créés
            int id = pstmt.executeUpdate();
// On pourrait s'arrêter là, mais je préfère récupérer la ligne créée
// Ca permet de savoir ce qu'on a réellement mis dans la DB
            ResultSet rs = pstmt.getGeneratedKeys();
            Integer last_inserted_id;
            if (rs.first()) { // Si on a des id créés
                last_inserted_id = rs.getInt(1);
// On récupère l'enregistrement créé
                obj = this.find(last_inserted_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return obj;
    }

    public Utilisateur update(Utilisateur obj) {
        try {
            String req = "UPDATE " + TABLE + " SET email = ?, " + "password = ? "
                    + "nom = ? WHERE id = ?";
//
            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setString(1, obj.getEmail());
            pstmt.setString(2, obj.getPassword());
            pstmt.setString(3, obj.getNom());
            pstmt.executeUpdate();
// On récupère l'enregistrement modifié
            obj = this.find(obj.getId());
        } catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return obj;
    }

    public void delete(Utilisateur obj) {
        try {
            String req = "DELETE FROM " + TABLE + " WHERE id = ?";

//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setInt(1, obj.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
}
