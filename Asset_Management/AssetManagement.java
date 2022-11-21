/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Asset_Management;

import java.util.Scanner;

/**
 *
 * @author Tuan Pham
 */
public class AssetManagement {
    public static void main(String[] args){
    InforList Asset = new InforList();
    InforList Employee = new InforList();
    InforList Request = new InforList();
    InforList Borrow = new InforList();
    Controller con = new Controller(Asset,Employee,Request,Borrow);
    Menu menuManager = new Menu();
    menuManager.addItem("Login");
    menuManager.addItem("Search company Infor");
    menuManager.addItem("Create new Asset");
    menuManager.addItem("Update company's information");
    menuManager.addItem("Approve employee's request");
    menuManager.addItem("Show list");
    menuManager.addItem("Save");
    menuManager.addItem("Quit");

    boolean logged = false;
    boolean cont = false;
    do{
    menuManager.showMenu();
    int choice = menuManager.getChoice();
    switch(choice){
        case 1:
            con.load();
            logged = con.login();
            break;
        case 2:
            if(logged) {
                con.searchInforByName();
            }
            else {
                System.out.println("  Please login first!");
            }
            break;
        case 3:
            if(logged) {
                con.add();
            }
            else {
                System.out.println("  Please login first!");
            }
            break;
        case 4:
            if(logged){
                con.update();
            }else{
                System.out.println("  Please login first!");
            }
            break;
        case 5:
            if(logged){
                con.approve();
            }else{
                System.out.println("  Please login first!");
            }
            break;
        case 6:
            if(logged){
                con.showList();
            }else{
                System.out.println(" Please login first!");
            }
            break;
        case 7:
            if(logged){
                con.save();
            }else {
                System.out.println(" Please login first!");
            }
            break;
        default:
            Scanner sc = new Scanner(System.in);
            System.out.printf("  Do you want to quit? (Y/N): ");
            cont = Menu.getYesNo(sc.nextLine());
            break;
    }
    }while(!cont);
    }
}
