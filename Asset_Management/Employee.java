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
public class Employee extends companyInfor{
    String birthdate,role,sex,password;
    
    public Employee(){
    }
    public Employee(String ID){
        super(ID);
    }
    public Employee(String ID,String name,String birthdate, String role, String sex, String password) {
        super.setID(ID);
        super.setName(name);
        this.birthdate = birthdate;
        this.role = role;
        this.sex = checker.checkToSwap(checker.checkSex(sex, true)?sex:"Others", this.sex, true);
        this.password = password;
    }

    public String getBirthdate() {
        return birthdate;
    }
    public String getRole() {
        return role;
    }
    public String getSex() {
        return sex;
    }
    public String getPassword() {
        return password;
    }
    
    @Override
    public void add(){
        super.add();
        String tmpBirthDate,tmpRole,tmpSex,tmpPassword;
        Scanner sc = new Scanner(System.in);
        System.out.printf(" Enter date of birth: ");
        do{
            tmpBirthDate = sc.nextLine();
            if(!checker.checkBirthDate(tmpBirthDate, true)){
                System.out.printf("  Please enter again: ");
            }else birthdate = tmpBirthDate;
        }while(!checker.checkBirthDate(tmpBirthDate, false));
    
        System.out.printf(" Enter role: ");
        do{
            tmpRole = sc.nextLine();
            if(!checker.checkRole(tmpRole, true)){
                System.out.printf("  Please enter again: ");
            }else role = tmpRole;
        }while(!checker.checkRole(tmpRole, false));
    
        System.out.printf(" Enter sex: ");
        tmpSex = sc.nextLine();
        if(!checker.checkSex(tmpSex,false)) sex = "Others";
        else sex = tmpSex;
        
        System.out.printf(" Enter password: ");
        do{
            tmpPassword = sc.nextLine();
            if(!checker.checkPassword(tmpPassword, true,true)){
                System.out.printf("  Please enter again: ");
            }else password = tmpPassword;
        }while(!checker.checkPassword(tmpPassword, false,true));
    }
    
    @Override
    public void update(Menu menuUpdate,int choices){
        String tmpBirthDate,tmpRole,tmpSex,tmpPassword;
        switch(choices){
            case 1:
                super.update(menuUpdate, choices);
                break;
            case 6:
                super.update(menuUpdate, choices);
                System.out.printf("\n  Enter Employee updated date of birth: ");
                tmpBirthDate = sc.nextLine();
                birthdate = checker.checkToSwap(tmpBirthDate,birthdate, checker.checkBirthDate(tmpBirthDate,true));
                System.out.printf("\n  Enter Employee updated role: ");
                tmpRole = sc.nextLine();
                role = checker.checkToSwap(tmpRole, role, checker.checkRole(tmpRole, true));
                System.out.printf("\n  Enter Employee updated sex: ");
                tmpSex = sc.nextLine();
                sex = checker.checkToSwap(checker.checkSex(tmpSex, true)?tmpSex:"Others", sex, true);
                System.out.printf("\n  Enter Employee updated password: ");
                tmpPassword = sc.nextLine();
                password = checker.checkToSwap(tmpPassword,password, checker.checkPassword(tmpPassword, true,true));
                break;
            case 2:
                System.out.printf("\n  Enter Employee updated date of birth: ");
                tmpBirthDate = sc.nextLine();
                birthdate = checker.checkToSwap(tmpBirthDate,birthdate, checker.checkBirthDate(tmpBirthDate,true));
                break;
            case 3:
                System.out.printf("\n  Enter Employee updated role: ");
                tmpRole = sc.nextLine();
                role = checker.checkToSwap(tmpRole, role, checker.checkRole(tmpRole, true));
                break;
            case 4:
                System.out.printf("\n  Enter Employee updated sex: ");
                tmpSex = sc.nextLine();
                sex = checker.checkToSwap(checker.checkSex(tmpSex, true)?tmpSex:"Others", sex, true);
                break;
            case 5:
                System.out.printf("\n  Enter Employee updated password: ");
                tmpPassword = sc.nextLine();
                password = checker.checkToSwap(tmpPassword,password, checker.checkPassword(tmpPassword, true,true));
                break;
            default:
                return;
        }
    }
    @Override
    public String toString(){
        return String.format("        %-15s%-22s%-25s%-18s%-22s%-10s%n",super.getID(),super.getName(),birthdate,role,sex,password);
    }
    public String toStringFormat(){
        return super.getID()+';'+super.getName()+';'+birthdate+';'+role+';'+sex+';'+password;
    }
    
}
