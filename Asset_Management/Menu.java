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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Menu extends ArrayList<String> implements I_Menu {

    public Menu() {
        super();
    }
    // must implement all abstract method of I_Menu interface
    @Override
    public void addItem(String s) {
        // do not delete this comment, your code here:
        this.add(s);
    }

    @Override
    public void showMenu() {
        // do not delete this comment, your code here:
        int i=0,choice;
        Iterator<String> iter = this.iterator();
        System.out.println();
        while(iter.hasNext()) System.out.println("   "+ ++i+") " + iter.next());
    }


    @Override
    public int getChoice() {
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        System.out.printf("  Enter your choice: ");
        int number = 0;
            do{
            try {
                number = Integer.parseInt(sc.nextLine());
                flag = true;
            }catch (Exception e) {
                System.out.println("Input number!!!");
            }
            }while(!flag);
        return number;
    }
    public static boolean getYesNo(String option){
        if(option.length()>1||option.length()<0) {
            System.out.printf("  Invalid input!\n  Proceed as No");
            return false;
        }
        else {
            if(option.contains("Y")||option.contains("y")) return true;
            else if(option.contains("N")||option.contains("n")) return false;
            else {
                System.out.printf("  Invalid input!\n  Proceed as No");
                return false;
            }
        }
    }
    

}

