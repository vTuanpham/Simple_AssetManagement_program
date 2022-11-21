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
public class Request extends Action{
    String rID;
    public Request() {
    }
    public Request(String rID,String aID,String eID,int quantity,String DateTime){
        this.rID = rID;
        super.setaID(aID);
        super.seteID(eID);
        super.setAquantity(quantity);
        super.setDate(DateTime);
    }
    //Setters
    public void setrID(String rID) {
        this.rID = rID;
    }
    @Override
    public void setaID(String aID) {
        super.getaID();
    }
    @Override
    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public void seteID(String eID) {
        super.seteID(eID);
    }
    @Override
    public void setAquantity(int Aquantity) {
        this.Aquantity = Aquantity;
    }
    //Getters

    public String getrID() {
        return rID;
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
        return String.format("        %-20s%-24s%-27s%-12s%-16s%n",rID,super.getaID(),super.geteID(),super.getAquantity(),super.getDate());
    }
    public String toStringFormat(){
        return rID +';'+super.getaID()+';'+super.geteID()+';'+super.getAquantity()+';'+super.getDate();
    }
}
