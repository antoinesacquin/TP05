/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import connection.Singleton;
import java.sql.Connection;

/**
 *
 * @author stag
 */
public abstract class DAO<T> {

    //création connection à la base de donnée
    protected Connection connection = Singleton.getInstance();

    /**
     * Permet de récupérer un objet via son ID
     *
     * @param id
     * @return l ’ objet r echerché
     */
    public abstract T find(Integer id);

    /**
     *
     * Permet de créer une entrée dans la base de données par rapport à un objet
     *
     * @param obj
     * @return l ’ objet créé avec son id
     */
    public abstract T create(T obj);

    /**
     * Permet de mettre à jour les données d'une entrée dans la base
     *
     * @param obj
     * @return l ’ objet modifié
     */
    public abstract T update(T obj);
    /**
     * Permet la suppression d'une entrée de la base
     *
     * @param obj
     */
    public abstract void delete(T obj);
    
}
