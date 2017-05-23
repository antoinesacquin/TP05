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
 * @author Antoine SACQUIN
 */
public class UtilisateurDAO {

    //Nom de la table d'intéret
    private static final String TABLE = "inscrits";

    //crétaion connection à la base de donnée
    protected Connection connection = Singleton.getInstance();

    /**
     * Méthode permettant d'obtenir un objet utilisateur dans la base de donnée
     * à partir de son identifiant
     *
     * @param id numero d'identifiant de l'utilisateur
     * @return utilisateur recherché
     */
    public Utilisateur find(Integer id) {
        Utilisateur user = null;
        try {
            String req = "SELECT * FROM " + TABLE + " WHERE id=?";

            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setInt(1, id);
            //System.out.println("request:" + pstmt);

            ResultSet result = pstmt.executeQuery();
            if (result.first()) {
                user = new Utilisateur(
                        id,
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("nom"));
            }

        } catch (SQLException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return user;

    }

    /**
     * Méthode permettant d'obtenir un objet utilisateur dans la base de donnée
     * à partir de son email
     *
     * @param email email de l'utilisateur
     * @return utilisateur recherché
     */
    public Utilisateur find(String email) {
        Utilisateur user = null;
        try {
            String req = "SELECT * FROM " + TABLE + " WHERE email=?";

            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setString(1, email);
            //System.out.println("request:" + pstmt);

            ResultSet result = pstmt.executeQuery();
            if (result.first()) {
                user = new Utilisateur(
                        result.getInt("id"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("nom"));
            }

        } catch (SQLException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return user;

    }

    /**
     * Ajout d'un nouvel utilisateur dans la base de donnée et retour de l'objet
     * créé avec son identifiant généré
     *
     * @param obj Utilisateur à ajouter à la base de donnée
     * @return Utilisateur crée avec son numéro d'identifiant
     */
    public Utilisateur create(Utilisateur obj) {
        try {
            String req = "INSERT INTO " + TABLE + " (email, password, nom) VALUES(?,?,?)";

            PreparedStatement pstmt = this.connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, obj.getEmail());
            pstmt.setString(2, obj.getPassword());
            pstmt.setString(3, obj.getNom());
            //System.out.println("request:" + pstmt);

            int id = pstmt.executeUpdate();

            ResultSet result = pstmt.getGeneratedKeys();
            int lastInsertedId;

            if (result.first()) {
                lastInsertedId = result.getInt(1);
                obj = this.find(lastInsertedId);
            }

        } catch (SQLException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return obj;
    }

    /**
     * Mise à jour de l'utilisateur
     *
     * @param obj utilisateur à mettre à jour
     * @return utilisateur mis à jour
     */
    public Utilisateur update(Utilisateur obj) {
        try {
            String req = "UPDATE " + TABLE + " SET email = ?, password =?, nom = ?  WHERE id=?";

            PreparedStatement pstmt = this.connection.prepareStatement(req);

            pstmt.setString(1, obj.getEmail());
            pstmt.setString(2, obj.getPassword());
            pstmt.setInt(3, obj.getId());
            //System.out.println("request:" + pstmt);
            pstmt.executeUpdate();

            obj = this.find(obj.getId());

        } catch (SQLException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return obj;
    }

    /**
     * Efface un utlisateur de la base de donnée
     *
     * @param obj utilisateur à éliminer
     */
    public void delete(Utilisateur obj) {
        try {
            String req = "DELETE FROM " + TABLE + " WHERE id=?";

            PreparedStatement pstmt = this.connection.prepareStatement(req);

            pstmt.setLong(1, obj.getId());
            //System.out.println("request:" + pstmt);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Vérifie que l'utilisateur existe dans la base de donnée
     *
     * @param user utilisateur dont on vérifie l'existence
     * @return true si existe, false si n'existe pas
     */
    public boolean exist(Utilisateur user) {

        boolean res = false;
        try {
            String req = "SELECT * FROM " + TABLE + " WHERE email=?";

            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setString(1, user.getEmail());
            //System.out.println("request:" + pstmt);

            ResultSet result = pstmt.executeQuery();

            if (result.first()) {
                res = true;
            }

        } catch (SQLException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return res;

    }
}
