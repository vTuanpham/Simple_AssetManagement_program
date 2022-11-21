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
import java.util.Scanner;
public class companyInfor {
    String ID,name;
    int quantity;
    Scanner sc = new Scanner(System.in);
    public companyInfor(){
    }
    public companyInfor(String ID) {
        this.ID = ID;
    }
    //Setters
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setName(String name) {
        this.name = name;
    }
    //Getters
    public String getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public void add(){
        String tmpName;
        System.out.printf("  Enter new infor name: ");
        do{
            tmpName = sc.nextLine();
        if(!checker.checkName(tmpName, true)){
            System.out.printf("  Please enter again: ");
        }
        }while(!checker.checkName(tmpName, false));
        name = tmpName;
    }
    
    public void update(Menu menuUpdate,int choice){
        switch(choice){
            case 1:
            case 6:
                System.out.printf("\n  Enter name to update: ");
                String tmpName = sc.nextLine();
                name = checker.checkToSwap(tmpName,name, checker.checkName(tmpName, true));
                break;
        }
    }
    
    
    
}
