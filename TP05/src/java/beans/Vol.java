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

    private Long id;
    private String depart;
    private String arrivee;
    private Long idAvion;
    private Long idPilote;
    private java.sql.Date jourDepart;
    private java.sql.Time heureDepart;
    private java.sql.Date jourArrivee;
    private java.sql.Time heureArrivee;

    public Vol() {

    }

    public Vol(Long id, String depart, String arrivee, Long idAvion,
            Long idPilote, Date jourDepart, Time heureDepart, Date jourArrivee, Time heureArrivee) {
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

    public java.sql.Time getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(java.sql.Time heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(Long idAvion) {
        this.idAvion = idAvion;
    }

    public Long getIdPilote() {
        return idPilote;
    }

    public void setIdPilote(Long idPilote) {
        this.idPilote = idPilote;
    }

    public java.sql.Date getJourDepart() {
        return jourDepart;
    }

    public void setJourDepart(java.sql.Date jourDepart) {
        this.jourDepart = jourDepart;
    }

    public java.sql.Time getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(java.sql.Time heureDepart) {
        this.heureDepart = heureDepart;
    }

    public java.sql.Date getJourArrivee() {
        return jourArrivee;
    }

    public void setJourArrivee(java.sql.Date jourArrivee) {
        this.jourArrivee = jourArrivee;
    }

    @Override
    public String toString() {
        return "Vol{" + "id=" + id + ", départ=" + depart + ", arrivée=" + arrivee
                + ", n°Avion=" + idAvion + ", n°Pilote=" + idPilote
                + ", Jour de départ=" + jourDepart + ", Heure de départ=" + heureDepart
                + ", Jour d'arrivée=" + jourArrivee + ", heure d'arrivée=" + heureArrivee + '}';
    }

}
