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
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class fileEditor {
    private File fileSaver;
    private String[] dataRead=new String[1000];
    private int lineRead=0;
    private String dirPath;
    //Setters
    public void setdirPath(String dirPath,companyInfor Instance){
        String datType="";
        this.dirPath="";
        File newDir = new File(dirPath+"//SaveNote");
        if(!newDir.exists()) newDir.mkdirs();
        if(Instance instanceof Asset) datType = "Asset.dat";
        if(Instance instanceof Employee) datType = "Employee.dat";
        if(Instance instanceof Request) datType = "Request.dat";
        
        if(Instance instanceof Borrow)datType = "Borrow.dat";
        this.dirPath=dirPath+"\\SaveNote\\AssetManagement\\"+datType;
    }
    //Getters
    public String getDirPath(){
        return dirPath;
    }
    //Methods
    public void fileCreate(){
        fileSaver=new File(dirPath);
        try{
        if (fileSaver.createNewFile()) System.out.printf("\n  File created: "+fileSaver.getName()+"\n");
        }catch (IOException e){
            System.out.printf("\n  An error occurred while creating file\n ");
            e.printStackTrace();
        }
    }
    //Writer
    public void fileWrite(ArrayList<companyInfor> arr){
        Iterator<companyInfor> iter = arr.iterator();
        try{
            FileWriter writer=new FileWriter(dirPath);
            while(iter.hasNext()) {
                companyInfor vc = iter.next();
                if(vc instanceof Asset) writer.write("Asset : "+((Asset)vc).toStringFormat()+"\n");
                else if(vc instanceof Employee) writer.write("Employee : "+((Employee)vc).toStringFormat()+"\n");
                else if(vc instanceof Request) writer.write("Request : "+((Request)vc).toStringFormat()+"\n");
                else writer.write("Borrow : "+((Borrow)vc).toStringFormat()+"\n");
            }
            System.out.printf("\n  Successfully wrote to file\n");
            writer.close();
        }catch(IOException e){
            System.out.printf("\n  An error occurred while writing to file\n ");
            e.printStackTrace();
        }
    }
    //Reader 
    public void fileRead(){
        Scanner sc;
        fileSaver = new File(dirPath);
        if(!fileSaver.isFile()) {
            System.out.printf("\n  File does not exsit!\n  Create file?(Y/N)");
            sc = new Scanner(System.in);
            String choices = sc.nextLine();
            switch(choices){
                case "Y": this.fileCreate(); break;
                case "N": return;
                default: return;
            }
        }else{
        try{
            sc = new Scanner(fileSaver);
            while(sc.hasNextLine()){
                dataRead[lineRead] = sc.nextLine();
                lineRead++;
            }
            sc.close();
        }catch(IOException e){
            System.out.printf("\n  An error occurred while reading file\n ");
            e.printStackTrace();
        }
    }
    }
    //Read format and save to list
    public void readFormat(ArrayList<companyInfor> Asset,ArrayList<companyInfor> Employee,ArrayList<companyInfor> Request,ArrayList<companyInfor> Borrow){
        Scanner sc;
        companyInfor vc = null;
        int flag=0;
        for (int i=0;i<lineRead;i++){
            String tmpId,tmpname = "",tmpcolor = "",tmpprice = "",tmpweight = "",tmpquantity = "",tmpbirthdate = "",tmprole = "",tmpsex = "",tmppassword="",tmpDateTime="";
            //Asset
            if(dataRead[i].contains("Asset : ")) {
                dataRead[i] = dataRead[i].replaceAll("Asset : ","");
                sc = new Scanner(dataRead[i]).useDelimiter(";");
                tmpId = sc.next();
                if(checker.checkId(Asset, tmpId, false,true)) {
                    try{
                    while(sc.hasNext()) {
                        tmpname=sc.next();
                        tmpcolor=sc.next();
                        tmpprice=sc.next();
                        tmpweight=sc.next();
                        tmpquantity=sc.next();
                    }
                    }catch(Exception e){
                        flag = 1;
                    }
                        if(checker.checkName(tmpname, false)&&checker.checkColor(tmpcolor, false)&&checker.checkPrice(tmpprice, false)&&checker.checkWeight(tmpweight, false)&&checker.checkQuantity(tmpquantity, false)){
                            vc = new Asset(tmpId,tmpname,tmpcolor,Double.parseDouble(tmpprice),Double.parseDouble(tmpweight),(int)Math.ceil(Double.parseDouble(tmpquantity)));
                            Asset.add(vc);
                            sc.close();
                        }else flag=1;
                }else flag=1; 
            //Employee
            }else if (dataRead[i].contains("Employee : ")){
                dataRead[i] = dataRead[i].replaceAll("Employee : ","");
                sc = new Scanner(dataRead[i]).useDelimiter(";");
                tmpId = sc.next();
                if(checker.checkId(Employee, tmpId, false,true)){
                    try{
                        while(sc.hasNext()){
                            tmpname=sc.next();
                            tmpbirthdate = sc.next();
                            tmprole=sc.next();
                            tmpsex=sc.next();
                            tmppassword=sc.next();
                        }
                    }catch(Exception e){
                        flag=1;
                    }
                    if(checker.checkName(tmpname, false)&&checker.checkBirthDate(tmpbirthdate, false)&&checker.checkRole(tmprole, false)&&checker.checkPassword(tmppassword, false,false)){
                            vc = new Employee(tmpId,tmpname,tmpbirthdate,tmprole,tmpsex,tmppassword);
                            Employee.add(vc);
                            sc.close();
                        }else flag=1;
                }else flag=1;
            //Request    
            }else if(dataRead[i].contains("Request : ")){
                dataRead[i] = dataRead[i].replaceAll("Request : ","");
                sc = new Scanner(dataRead[i]).useDelimiter(";");
                String tmprID = sc.next();
                String tmpaID = sc.next();
                String tmpeID = sc.next();
                if(checker.checkId(Request, tmprID, false,true)&&checker.checkDuplicate(Asset, tmpaID)&&checker.checkDuplicate(Employee, tmpeID)){
                    try{
                        while(sc.hasNext()){
                            tmpquantity=sc.next();
                            tmpDateTime=sc.next();
                        }
                    }catch(Exception e){
                        flag=1;
                    }
                    if(checker.checkQuantity(tmpquantity, false)&&checker.checkDateTime(tmpDateTime,false)){
                        vc = new Request(tmprID,tmpaID,tmpeID,(int)Math.ceil(Double.parseDouble(tmpquantity)),tmpDateTime);
                        Request.add(vc);
                        sc.close();
                    }else flag=1;
            }else flag=1;
            //Borrow
            }else if(dataRead[i].contains("Borrow : ")){
                dataRead[i] = dataRead[i].replaceAll("Borrow : ","");
                sc = new Scanner(dataRead[i]).useDelimiter(";");
                String tmpbID = sc.next();
                String tmpaID = sc.next();
                String tmpeID = sc.next();
                if(checker.checkId(Borrow, tmpbID, false,true)&&checker.checkDuplicate(Asset, tmpaID)&&checker.checkDuplicate(Employee, tmpeID)){
                    try{
                        while(sc.hasNext()){
                            tmpquantity=sc.next();
                            tmpDateTime=sc.next();
                        }
                    }catch(Exception e){
                        flag=1;
                    }
                    if(checker.checkQuantity(tmpquantity, false)&&checker.checkDateTime(tmpDateTime,false)){
                        vc = new Borrow(tmpbID,tmpaID,tmpeID,(int)Math.ceil(Double.parseDouble(tmpquantity)),tmpDateTime);
                        Borrow.add(vc);
                        sc.close();
                    }else flag=1;
            }
            else if(!dataRead[i].isEmpty()) flag=1;
            }
        }           
                    if (flag==1) System.out.println("  Some items were not added due to incorrect input!");
                    else System.out.println("  Load successfully");            
    }
}
