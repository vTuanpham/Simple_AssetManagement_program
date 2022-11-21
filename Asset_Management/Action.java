/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Asset_Management;

/**
 *
 * @author Tuan Pham
 */
public abstract class Action extends companyInfor{
    String aID,date,eID;
    int Aquantity;

    //Setters
    public void setDate(String date) {
        this.date = date;
    }
    public void setAquantity(int quantity) {
        this.Aquantity = quantity;
    }
    public void setaID(String aID) {
        this.aID = aID;
    }
    public void seteID(String eID) {
        this.eID = eID;
    }
    
    //Getters
    public String getaID() {
        return aID;
    }
    public String getDate() {
        return date;
    }
    public int getAquantity() {
        return Aquantity;
    }
    public String geteID() {
        return eID;
    } 
    @Override
    public abstract String toString();
    public abstract String toStringFormat();
    
    
    
    
}
