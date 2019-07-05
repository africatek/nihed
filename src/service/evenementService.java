/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.evenement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author wyouness
 */
public class evenementService implements IService<evenement>{
    private DataSource connexion;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public evenementService() {
        connexion=DataSource.getInstance();
       
    }
   
    public void insertPS(evenement t) {
        try {
            String requete="insert into evenement (nom,dure_reservation,destination,date_depart,dure_trip,promotion,htl,rest) values (?,?,?,?,?,?,?,?,?)";
            pst=connexion.getCnx().prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setDate(2, t.getDure_reservation());
            pst.setString(3, t.getDestination());
            pst.setDate(4, t.getDate_depart());
            pst.setInt(5, t.getDure_trip());
            pst.setInt(6, t.getPromotion());
            pst.setInt(7, t.getNbPlace());
            pst.setInt(8, t.getHtl().getIdH());
            pst.setInt(9, t.getRest().getIdR());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(evenement t) {
        try {
            String requete="update evenement set nom=?,dure_reservation=?,destination,date_depart=?,dure_trip,promotion=?,htl=?,rest=?  where idE=?";
            pst=connexion.getCnx().prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setDate(2, t.getDure_reservation());
            pst.setString(3, t.getDestination());
            pst.setDate(4, t.getDate_depart());
            pst.setInt(5, t.getDure_trip());
            pst.setInt(6, t.getPromotion());
            pst.setInt(7, t.getNbPlace());
            pst.setInt(8, t.getHtl().getIdH());
            pst.setInt(9, t.getRest().getIdR());
            pst.setInt(10, t.getIdE());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<evenement> readAll() {
         List<evenement> list=new ArrayList<>();
        try {
           
            String requete="select * from evenement";
            ste=connexion.getCnx().createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()){
                evenement E=new evenement(rs.getInt(1), rs.getString(2), rs.getDate(3),
                rs.getString(4),rs.getDate(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10));
                list.add(E);
            }
         
            
        } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public evenement readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(evenement t) {
        try {
            String requete="delete from evenement where id ="+t.getIdE();
            ste=connexion.getCnx().createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
