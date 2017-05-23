/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Vol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stag
 */
public class VolDAO extends DAO<Vol> {

    private static final String TABLE = "vol";

    @Override
    public Vol find(Integer id) {
        Vol vol = null;
        try {
            String req = "SELECT * FROM " + TABLE + " WHERE id = ?";
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setInt(1, id);
            //System.out.println("request:" + pstmt);
            ResultSet result = pstmt.executeQuery();
            if (result.first()) {
                vol = new Vol(
                        id,
                        result.getString("depart"),
                        result.getString("arrivee"),
                        result.getInt("id_avion"),
                        result.getInt("id_pilote"),
                        result.getDate("jdep"),
                        result.getTime("hdep"),
                        result.getDate("jarr"),
                        result.getTime("harr")
                );
            }

        } catch (SQLException e) {
            Logger.getLogger(VolDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return vol;
    }

    @Override
    public Vol create(Vol obj) {
        try {
            String req = "INSERT INTO " + TABLE + " (depart, arrivee, id_avion,"
                    + " id_pilote, jdep, hdep, jarr, harr) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement pstmt = this.connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, obj.getDepart());
            pstmt.setString(2, obj.getArrivee());
            pstmt.setLong(3, obj.getIdAvion());
            pstmt.setLong(4, obj.getIdPilote());
            pstmt.setDate(5, obj.getJourDepart());
            pstmt.setTime(6, obj.getHeureDepart());
            pstmt.setDate(7, obj.getJourArrivee());
            pstmt.setTime(8, obj.getHeureArrivee());
            //System.out.println("request:" + pstmt);

            int id = pstmt.executeUpdate();

            ResultSet result = pstmt.getGeneratedKeys();
            int lastInsertedId;

            if (result.first()) {
                lastInsertedId = result.getInt(1);
                obj = this.find(lastInsertedId);
            }

        } catch (SQLException e) {
            Logger.getLogger(VolDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return obj;
    }

    @Override
    public Vol update(Vol obj) {
        try {
            String req = "UPDATE " + TABLE + " SET depart = ?, arrivee = ?, id_avion = ?,"
                    + " id_pilote = ?, jdep = ?, hdep = ?, jarr = ?, harr = ? WHERE id = ?";

            PreparedStatement pstmt = this.connection.prepareStatement(req);

            pstmt.setString(1, obj.getDepart());
            pstmt.setString(2, obj.getArrivee());
            pstmt.setLong(3, obj.getIdAvion());
            pstmt.setLong(4, obj.getIdPilote());
            pstmt.setDate(5, obj.getJourDepart());
            pstmt.setTime(6, obj.getHeureDepart());
            pstmt.setDate(7, obj.getJourArrivee());
            pstmt.setTime(8, obj.getHeureArrivee());
            pstmt.setLong(9, obj.getId());
            //System.out.println("request:" + pstmt);
            pstmt.executeUpdate();

            obj = this.find(obj.getId());

        } catch (SQLException e) {
            Logger.getLogger(VolDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return obj;
    }

    @Override
    public void delete(Vol obj) {
        try {
            String req = "DELETE FROM " + TABLE + " WHERE id=?";

            PreparedStatement pstmt = this.connection.prepareStatement(req);

            pstmt.setLong(1, obj.getId());
            //System.out.println("request:" + pstmt);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(VolDAO.class.getName()).log(Level.SEVERE, null, e);
        }

    }

/**Retourne toutes les villes de départ de la table
 * 
 * @return 
 */
    public Set<String> findAllDeparts() {
        Set<String> tabId = new HashSet<>();
        try {
            String req = "SELECT depart FROM " + TABLE;

            Statement stmt = this.connection.createStatement();
            ResultSet result = stmt.executeQuery(req);
            while (result.next()) {
                tabId.add(result.getString(1));
            }

        } catch (SQLException e) {
            Logger.getLogger(VolDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return tabId;
    }
    public Set<Vol> findAll(String depart) {
        Set<Vol> tabId = new HashSet<>();
        try {
            String req = "SELECT * FROM " + TABLE+" WHERE depart = ?";

            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setString(1, depart);
            
            ResultSet result = pstmt.executeQuery(req);
            while (result.next()) {
               Vol vol = new Vol(
                        result.getInt("id"),
                        result.getString("depart"),
                        result.getString("arrivee"),
                        result.getInt("id_avion"),
                        result.getInt("id_pilote"),
                        result.getDate("jdep"),
                        result.getTime("hdep"),
                        result.getDate("jarr"),
                        result.getTime("harr")
                );
                tabId.add(vol);
            }

        } catch (SQLException e) {
            Logger.getLogger(VolDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return tabId;
    }
}
