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
public class Borrow extends Action{
    String bID;
    public Borrow(){
    }
    public Borrow(String bID,String aID,String eID,int quantity,String DateTime){
        this.bID = bID;
        super.setaID(aID);
        super.seteID(eID);
        super.setAquantity(quantity);
        super.setDate(DateTime);
    }
    //Setters
    public void setbID(String bID) {
        this.bID = bID;
    }
    @Override
    public void setaID(String aID) {
        this.aID = aID;
    }
    @Override
    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public void seteID(String eID) {
        this.eID = eID;
    }
    @Override
    public void setAquantity(int Aquantity) {
        this.Aquantity = Aquantity;
    }
    //Getters
    public String getbID() {
        return bID;
    }
    @Override
    public String getaID() {
        return aID;
    }
    @Override
    public String getDate() {
        return date;
    }
    @Override
    public String geteID() {
        return eID;
    }
    @Override
    public int getAquantity() {
        return Aquantity;
    }
    
    @Override
    public String toString(){
        return String.format("        %-20s%-24s%-27s%-12s%-16s%n",bID,super.getaID(),super.geteID(),super.getAquantity(),super.getDate());
    }
    public String toStringFormat(){
        return bID +';'+super.getaID()+';'+super.geteID()+';'+super.getAquantity()+';'+super.getDate();
    }
}
