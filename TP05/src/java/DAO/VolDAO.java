/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Vol;
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
 * @author stag
 */
public class VolDAO {

    private final String TABLE = "vol";

    //création connection à la base de donnée
    protected Connection connection = Singleton.getInstance();

    public Vol find(Long id) {
        Vol vol = null;
        try {
            String req = "SELECT * FROM " + TABLE + " WHERE id = ?";
//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setLong(1, id);
            ResultSet result = pstmt.executeQuery();
            if (result.first()) {
                vol = new Vol(id,
                        result.getString("depart"),
                        result.getString("arrivee"),
                        result.getLong("id_avion"),
                        result.getLong("id_pilote"),
                        result.getDate("jdep"),
                        result.getTime("hdep"),
                        result.getDate("jarr"),
                        result.getTime("harr")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(VolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vol;
    }

    public Vol create(Vol obj) {
        try {
            String req = "INSERT INTO " + TABLE + " (depart, arrivee, id_avion, id_pilote, jdep, hdep, jarr, harr) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getDepart());
            pstmt.setString(2, obj.getArrivee());
            pstmt.setLong(3, obj.getId_avion());
            pstmt.setLong(4, obj.getId_pilote());
            pstmt.setDate(5, obj.getJdep());
            pstmt.setTime(6, obj.getHdep());
            pstmt.setDate(7, obj.getJarr());
            pstmt.setTime(8, obj.getHarr());
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
            Logger.getLogger(VolDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return obj;
    }

    public Vol update(Vol obj) {
        try {
            String req = "UPDATE " + TABLE
                    + " SET depart = ?, arrivee = ?, id_avion = ?, id_pilote = ?,"
                    + " jdep = ?, hdep = ?, jarr = ?, harr = ? WHERE id = ?";
//
            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setString(1, obj.getDepart());
            pstmt.setString(2, obj.getArrivee());
            pstmt.setLong(3, obj.getId_avion());
            pstmt.setLong(4, obj.getId_pilote());
            pstmt.setDate(5, obj.getJdep());
            pstmt.setTime(6, obj.getHdep());
            pstmt.setDate(7, obj.getJarr());
            pstmt.setTime(8, obj.getHarr());
            pstmt.setLong(9, obj.getId());
            pstmt.executeUpdate();
// On récupère l'enregistrement modifié
            obj = this.find(obj.getId());
        } catch (SQLException ex) {
            Logger.getLogger(VolDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return obj;
    }

    public void delete(Vol obj) {
        try {
            String req = "DELETE FROM " + TABLE + " WHERE id = ?";

//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setLong(1, obj.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VolDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

}
