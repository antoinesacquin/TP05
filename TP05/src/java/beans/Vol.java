/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author stag
 */
public class Vol {

    private Long id;
    private String depart;
    private String arrivee;
    private Long id_avion;
    private Long id_pilote;
    private java.sql.Date jdep;
    private java.sql.Time hdep;
    private java.sql.Date jarr;
    private java.sql.Time harr;

    public Vol() {

    }

    public Vol(Long id, String depart, String arrivee, Long id_avion, Long id_pilote,
            java.sql.Date jdep, java.sql.Time hdep, java.sql.Date jarr, java.sql.Time harr) {
        this.id = id;
        this.depart = depart;
        this.arrivee = arrivee;
        this.id_avion = id_avion;
        this.id_pilote = id_pilote;
        this.jdep = jdep;
        this.hdep = hdep;
        this.jarr = jarr;
        this.harr = harr;
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

    public Long getId_avion() {
        return id_avion;
    }

    public void setId_avion(Long id_avion) {
        this.id_avion = id_avion;
    }

    public Long getId_pilote() {
        return id_pilote;
    }

    public void setId_pilote(Long id_pilote) {
        this.id_pilote = id_pilote;
    }

    public java.sql.Date getJdep() {
        return jdep;
    }

    public void setJdep(java.sql.Date jdep) {
        this.jdep = jdep;
    }

    public java.sql.Time getHdep() {
        return hdep;
    }

    public void setHdep(java.sql.Time hdep) {
        this.hdep = hdep;
    }

    public java.sql.Date getJarr() {
        return jarr;
    }

    public void setJarr(java.sql.Date jarr) {
        this.jarr = jarr;
    }

    public java.sql.Time getHarr() {
        return harr;
    }

    public void setHarr(java.sql.Time harr) {
        this.harr = harr;
    }

    @Override
    public String toString() {
        return "Vol{" + "id=" + id + ", depart=" + depart + ", arrivee=" + arrivee
                + ", id_avion=" + id_avion + ", id_pilote=" + id_pilote + ", jdep=" + jdep
                + ", hdep=" + hdep + ", jarr=" + jarr + ", harr=" + harr + '}';
    }

}
