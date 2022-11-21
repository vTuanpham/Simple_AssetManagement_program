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
public class Asset extends companyInfor{
    String color;
    double price,weight;
    int quantity;
    
    public Asset(){
    }
    public Asset(String ID){
        super(ID);
    }
    public Asset(String id,String name,String color, double price, double weight, int quantity) {
        super.setID(id);
        super.setName(name);
        this.color = color;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
    }

    @Override
    public void setID(String id){
        super.setID(id);
    }
    @Override
    public void setName(String name){
        super.setName(name);
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String getID(){
        return super.getID();
    }
    @Override
    public String getName(){
        return super.getName();
    }
    public String getColor() {
        return color;
    }
    public double getPrice() {
        return price;
    }
    public double getWeight() {
        return weight;
    }
    public int getQuantity() {
        return quantity;
    }
    
    
    
    
    
    @Override
    public void add(){
        super.add();
        String tmpColor,tmpPrice,tmpWeight,tmpQuantity;
        Scanner sc = new Scanner(System.in);
        System.out.printf(" Enter color: ");
        do{
            tmpColor = sc.nextLine();
            if(!checker.checkColor(tmpColor, true)){
                System.out.printf("  Please enter again: ");
            }else color = tmpColor;
        }while(!checker.checkColor(tmpColor, false));
    
        System.out.printf(" Enter price: ");
        do{
            tmpPrice = sc.nextLine();
            if(!checker.checkPrice(tmpPrice, true)){
                System.out.printf("  Please enter again: ");
            }else price = Double.parseDouble(tmpPrice);
        }while(!checker.checkPrice(tmpPrice, false));
    
        System.out.printf(" Enter weight: ");
        do{
            tmpWeight = sc.nextLine();
            if(!checker.checkWeight(tmpWeight, true)){
                System.out.printf("  Please enter again: ");
            }else weight = Double.parseDouble(tmpWeight);
        }while(!checker.checkWeight(tmpWeight, false));
        
        System.out.printf(" Enter quantity: ");
        do{
            tmpQuantity = sc.nextLine();
            if(!checker.checkQuantity(tmpQuantity, true)){
                System.out.printf("  Please enter again: ");
            }else quantity = (int)Math.ceil(Double.parseDouble(tmpQuantity));
        }while(!checker.checkQuantity(tmpQuantity, false));
    }
    
    @Override
    public void update(Menu menuUpdate,int choices){
        String tmpColor,tmpPrice,tmpWeight,tmpQuantity;
        switch(choices){
            case 1:
                super.update(menuUpdate, choices);
                break;
            case 6:
                super.update(menuUpdate, choices);
                System.out.printf("\n  Enter Asset color to update: ");
                tmpColor = sc.nextLine();
                color = checker.checkToSwap(tmpColor, color, checker.checkColor(tmpColor,true));
                System.out.printf("\n  Enter Asset price to update: ");
                tmpPrice = sc.nextLine();
                String oldPrice = String.valueOf(price);
                price = Double.parseDouble(checker.checkToSwap(tmpPrice, oldPrice, checker.checkPrice(tmpPrice, true)));
                System.out.printf("\n  Enter Asset weight to update: ");
                tmpWeight = sc.nextLine();
                String oldWeight = String.valueOf(weight);
                price = Double.parseDouble(checker.checkToSwap(tmpWeight, oldWeight, checker.checkWeight(tmpWeight, true)));
                System.out.printf("\n  Enter Asset quantity to update: ");
                tmpQuantity = sc.nextLine();
                String oldQuantity = String.valueOf(quantity);
                quantity = Integer.parseInt(checker.checkToSwap(tmpQuantity,oldQuantity, checker.checkQuantity(tmpQuantity, true)));
                break;
            case 2:
                System.out.printf("\n  Enter Asset color to update: ");
                tmpColor = sc.nextLine();
                color = checker.checkToSwap(tmpColor, color, checker.checkColor(tmpColor,true));
                break;
            case 3:
                System.out.printf("\n  Enter Asset price to update: ");
                tmpPrice = sc.nextLine();
                oldPrice = String.valueOf(price);
                price = Double.parseDouble(checker.checkToSwap(tmpPrice, oldPrice, checker.checkPrice(tmpPrice, true)));
                break;
            case 4:
                System.out.printf("\n  Enter Asset weight to update: ");
                tmpWeight = sc.nextLine();
                oldWeight = String.valueOf(weight);
                weight = Double.parseDouble(checker.checkToSwap(tmpWeight, oldWeight, checker.checkWeight(tmpWeight, true)));
                break;
            case 5:
                System.out.printf("\n  Enter Asset quantity to update: ");
                tmpQuantity = sc.nextLine();
                oldQuantity = String.valueOf(quantity);
                quantity = Integer.parseInt(checker.checkToSwap(tmpQuantity,oldQuantity, checker.checkQuantity(tmpQuantity, true)));
                break;
            default:
                return;
        }
    }
    @Override
    public String toString(){
        return String.format("        %-9s%-25s%-20s%-17s%-20s%-10s%n",super.getID(),super.getName(),color,price,weight,quantity);
    }
    
    public String toStringFormat(){
        return super.getID()+';'+super.getName()+';'+color+';'+price+';'+weight+';'+quantity;
    }
    
    
}
