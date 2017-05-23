/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author stag
 */
public class Vol implements Serializable {

    private Integer id;
    private String depart;
    private String arrivee;
    private Integer idAvion;
    private Integer idPilote;
    private java.sql.Date jourDepart;
    private java.sql.Time heureDepart;
    private java.sql.Date jourArrivee;
    private java.sql.Time heureArrivee;

    public Vol() {

    }

    public Vol(Integer id, String depart, String arrivee, Integer idAvion, Integer idPilote, Date jourDepart, Time heureDepart, Date jourArrivee, Time heureArrivee) {
        this.id = id;
        this.depart = depart;
        this.arrivee = arrivee;
        this.idAvion = idAvion;
        this.idPilote = idPilote;
        this.jourDepart = jourDepart;
        this.heureDepart = heureDepart;
        this.jourArrivee = jourArrivee;
        this.heureArrivee = heureArrivee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrivee() {
        return arrivee;
    }

    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
    }

    public Integer getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(Integer idAvion) {
        this.idAvion = idAvion;
    }

    public Integer getIdPilote() {
        return idPilote;
    }

    public void setIdPilote(Integer idPilote) {
        this.idPilote = idPilote;
    }

    public Date getJourDepart() {
        return jourDepart;
    }

    public void setJourDepart(Date jourDepart) {
        this.jourDepart = jourDepart;
    }

    public Time getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Time heureDepart) {
        this.heureDepart = heureDepart;
    }

    public Date getJourArrivee() {
        return jourArrivee;
    }

    public void setJourArrivee(Date jourArrivee) {
        this.jourArrivee = jourArrivee;
    }

    public Time getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(Time heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    @Override
    public String toString() {
        return "Vol{" + "id=" + id + ", départ=" + depart + ", arrivée=" + arrivee
                + ", n°Avion=" + idAvion + ", n°Pilote=" + idPilote
                + ", Jour de départ=" + jourDepart + ", Heure de départ=" + heureDepart
                + ", Jour d'arrivée=" + jourArrivee + ", heure d'arrivée=" + heureArrivee + '}';
    }

}
