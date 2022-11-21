package Asset_Management;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tuan Pham
 */
import java.util.Scanner;
public class InforList extends ArrayList<companyInfor>{
    Scanner sc = new Scanner(System.in);
    public InforList(){
        super();
    }
    public ArrayList<companyInfor> getList(){
        return this;
    }

    public void delete(){
        String tmpId;
        System.out.printf("  \nEnter Infor id to delete: ");
        do{
            tmpId = sc.nextLine();
            if(!checker.checkId(this, tmpId, true,false)) System.out.printf("  \nPlease enter again: ");
            else break;
        }while(!checker.checkId(this,tmpId,false,false));
        companyInfor vc = searchById(tmpId,false);
        if(Objects.isNull(vc)) System.out.printf("  \nId not found, delete aborted!\n");
        else{
        System.out.printf("  \nAre you sure to delete this item(Y/N) ?:");
        if(Menu.getYesNo(sc.nextLine())) {
            this.remove(vc);
            System.out.printf("  \nRemove successfully! \n");
        }else System.out.printf("  \nRemove quit! \n");
        }
    }
    public void search(){
        Menu menuSearch = new Menu();
        menuSearch.addItem("Search by name");
        menuSearch.addItem("Search by id");
        menuSearch.addItem("Back to main menu");
        menuSearch.showMenu();
        switch(menuSearch.getChoice()){
            case 1: 
                System.out.printf("  \nEnter Infor name to search: ");
                searchByName(sc.nextLine());
                break;
            case 2:
                System.out.printf("  \nEnter Infor Code to search: ");
                String id = sc.nextLine();
                companyInfor vc = searchById(id,true);
                vc = null;
                break;
            case 3:
                return ;
        } 
    }
    
    public void searchByName(String name){
           Iterator<companyInfor> iter = this.iterator();
           ArrayList<companyInfor> output = new ArrayList<>();
           while(iter.hasNext()) {
               companyInfor vc = iter.next();
               if(vc.getName().contains(name)) output.add(vc);
           }
           if(output.isEmpty()) System.out.printf("  \nInformation with name %s doesn't exsit!\n",name);
           else{
               sortByName(output);
               displayTitle(output.get(0));
               for(companyInfor vc: output) System.out.printf(vc.toString()+"\n");
           }
    }
    private void sortByName(ArrayList<companyInfor> list){
        list.sort(cmpName);
    }
    private void sortByQuantity(ArrayList<companyInfor> list){
        list.sort(cmpQuantity);
    }
     Comparator<companyInfor> cmpName = new Comparator<companyInfor>(){
        @Override
        public int compare(companyInfor v1, companyInfor v2) {
            return -v1.getName().compareTo(v2.getName());
        }
    };
     Comparator<companyInfor> cmpQuantity = new Comparator<companyInfor>(){
         @Override
         public int compare(companyInfor v1, companyInfor v2){
             if(v1 instanceof Asset) {
                 Asset v11,v22;
                 v11 = (Asset) v1;
                 v22 = (Asset) v2;
                 double result = (v11.getQuantity() - v22.getQuantity());// Add "-" for descending
                 if(result>0) return 1;
                 else if (result<0) return -1;
                 else return 0;
             }
             if(v1 instanceof Request){
                Request v11,v22;
                v11 = (Request) v1;
                v22 = (Request) v2;
                double result = (v11.getAquantity() - v22.getAquantity());// Add "-" for descending
                if(result>0) return 1;
                else if (result<0) return -1;
                else return 0;
             }
             else{
                Borrow v11,v22;
                v11 = (Borrow) v1;
                v22 = (Borrow) v2;
                double result = (v11.getAquantity() - v22.getAquantity());// Add "-" for descending
                if(result>0) return 1;
                else if (result<0) return -1;
                else return 0;
             }
         }
     };
    public companyInfor searchById(String id,boolean print){
        companyInfor vc;
        Iterator<companyInfor> iter = this.iterator();
        boolean flag = true;
        int type =0;
        if(!this.isEmpty()){
            vc = this.get(0);
            if(vc instanceof Request) type=1;
            if(vc instanceof Borrow) type=2;
            if(vc instanceof Asset) type =3;
            if(vc instanceof Employee) type=4;
        }else return null;
        switch(type){
            case 1:
                while(iter.hasNext()){
                vc = iter.next();
                if(((Request)vc).getrID().equals(id)){
                    if(print){
                        ArrayList<companyInfor> tmpvc = new ArrayList();
                        tmpvc.add(vc);
                        displayTitle(tmpvc.get(0));
                        System.out.printf(vc.toString()+"\n");
                        flag = false;
                    }else return vc;
                }
                }
                break;
            case 2:
                while(iter.hasNext()){
                vc = iter.next();
                if(((Borrow)vc).getbID().equals(id)){
                    if(print){
                        ArrayList<companyInfor> tmpvc = new ArrayList();
                        tmpvc.add(vc);
                        displayTitle(tmpvc.get(0));
                        System.out.printf(vc.toString()+"\n");
                        flag = false;
                    }else return vc;
                }
                }
                break;
            case 3:
                while(iter.hasNext()){
                vc = iter.next();
                if(((Asset)vc).getID().equals(id)){
                    if(print){
                        ArrayList<companyInfor> tmpvc = new ArrayList();
                        tmpvc.add(vc);
                        displayTitle(tmpvc.get(0));
                        System.out.printf(vc.toString()+"\n");
                        flag = false;
                    }else return vc;
                }
                }
                break;
            case 4:
                while(iter.hasNext()){
                vc = iter.next();
                if(((Employee)vc).getID().equals(id)){
                    if(print){
                        ArrayList<companyInfor> tmpvc = new ArrayList();
                        tmpvc.add(vc);
                        displayTitle(tmpvc.get(0));
                        System.out.printf(vc.toString()+"\n");
                        flag = false;
                    }else return vc;
                }
                }
                break;
            default:
                if(print) System.out.println("ID not found!");
                return null;
        }return null;
    }
    //Show list
    public void showList(){
        if(this.isEmpty()) {
            System.out.printf("  \nThe list is empty!");
            return ;
        }
        Iterator<companyInfor> iter = this.iterator();
        Menu menuShow = new Menu();
        menuShow.addItem("Show all");
        if(this.get(0) instanceof Employee) menuShow.addItem("Show all sorted by Name");
        if(!(this.get(0) instanceof Employee)) menuShow.addItem("Show all by quantity order");
        if(!(this.get(0) instanceof Employee)) menuShow.addItem("Show items with higher Quantity");
        menuShow.addItem("Back to main Menu");
        menuShow.showMenu();
        switch(menuShow.getChoice()){
            case 1:
                displayTitle(this.get(0));
                while(iter.hasNext()) {
                    System.out.printf(iter.next().toString()+"\n");
                }
                break;
            case 2:
                if(this.get(0) instanceof Employee) {
                    ArrayList<companyInfor> vList = (ArrayList<companyInfor>) this.clone();
                    displayTitle(this.get(0));
                    sortByName(vList);
                    Iterator<companyInfor> iterClone = vList.iterator();
                    while(iterClone.hasNext()){
                        companyInfor vc = iterClone.next();
                        System.out.printf(vc.toString()+"\n");
                    }
                    break;
                }
                ArrayList<companyInfor> vList = (ArrayList<companyInfor>) this.clone();
                displayTitle(this.get(0));
                sortByQuantity(vList);
                Iterator<companyInfor> iterClone = vList.iterator();
                while(iterClone.hasNext()) {
                    companyInfor vc = iterClone.next();
                    System.out.printf(vc.toString()+"\n");
                }
                break;
            case 3:
                if(this.get(0) instanceof Employee) break;
                showHigerPrices();
                break;
            default :
                return;
        }
    }
    public void showHigerPrices(){
        Iterator<companyInfor> iter = this.iterator();
        System.out.printf("  Enter quantity: ");
        String tmpQuantityS = sc.nextLine();
        double QuantityS = (checker.checkQuantity(tmpQuantityS, true))?(int)Math.ceil(Double.parseDouble(tmpQuantityS)):-1;
        if(QuantityS==-1) {
            System.out.println("  Invalid input!");
            return;
        }
        boolean flag = false;
        
        boolean flagTitle=false;
        while(iter.hasNext()) {
            companyInfor vc = iter.next();
            if(!flagTitle){
                displayTitle(vc);
                flagTitle=true;
            }
            if(vc instanceof Asset){
                if(((Asset)vc).getQuantity()>QuantityS) {
                    System.out.printf(vc.toString()+"\n");
                    flag = true;
                }
            }else if(vc instanceof Request){
                if(((Request)vc).getAquantity()>QuantityS) {
                    System.out.printf(vc.toString()+"\n");
                    flag = true;
                }
            }else if(vc instanceof Borrow){
                if(((Borrow)vc).getAquantity()>QuantityS) {
                    System.out.printf(vc.toString()+"\n");
                    flag = true;
                }
            }
        }if(!flag) System.out.println("  No items with prices higher than " + QuantityS);
    }
    //Board header
    private void displayTitle(companyInfor Instance){
        companyInfor vc = null;
        String Id,name,color,price,weight,Quantity,BirthDate,Role,Sex,Password,aID,eID,rDate;
        int flagAsset = 0,flagEmployee = 0;
        System.out.println();
        if(Instance instanceof Asset) {
            Id = "Asset ID";
            name = "Asset name";
            color = "Color appearance";
            price ="Asset price";
            weight = "Asset weight";
            Quantity = "Available asset";
            System.out.println(String.format("      %-15s%-16s%-22s%-16s%-16s%-10s%n",Id,name,color,price,weight,Quantity));
        }else if(Instance instanceof Employee){
            Id = "Employee ID";
            name = "Employee name";
            BirthDate = "Date of birth";
            Role = "Position";
            Sex = "Gender";
            Password = "Login password";
            System.out.println(String.format("      %-18s%-20s%-23s%-20s%-20s%-10s%n",Id,name,BirthDate,Role,Sex,Password));
        }else if(Instance instanceof Request){
            Id = "Request ID";
            aID = "Requested Asset ID";
            eID = "Employee ID requested";
            Quantity = "Assets Request";
            rDate = "Date of request";
            System.out.println(String.format("      %-16s%-24s%-27s%-19s%-16s%n",Id,aID,eID,Quantity,rDate));
        }else if(Instance instanceof Borrow){
            Id = "Borrow ID";
            aID = "Borrow Asset ID";
            eID = "Employee ID borrow";
            Quantity = "Borrows Request";
            rDate = "Date of Borrow";
            System.out.println(String.format("      %-16s%-24s%-27s%-19s%-16s%n",Id,aID,eID,Quantity,rDate));
    }
    
    }   
}