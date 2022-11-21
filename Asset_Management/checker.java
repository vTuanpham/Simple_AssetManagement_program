/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tuan Pham
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Asset_Management;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Tuan Pham
 */
public class checker {
    public static boolean containSpace(String o){
        boolean result = o.contains(" ")?true:false;
        return result;
    }
    public static int containNumber(String o){
        int result;
        if(o.isEmpty()) return -2;
        for(char c:o.toCharArray()) if(Character.isAlphabetic(c)) return -1;
        if(containsSpecialCharacter(o)) return -3;
        if(checker.containSpace(o)) return 0;
        
        try{
            Double testNum = Double.parseDouble(o);
        }catch(Exception e) {
            return -4;
        }
        return 1;
    }
    public static boolean containsSpecialCharacter(String s) {
        if(s.contains(".")){
        try{
            int iBefore = s.indexOf(".")-1;
            int iAfter = s.indexOf(".")+1;
        if(Character.isDigit(s.toCharArray()[iAfter])&&Character.isDigit(s.toCharArray()[iBefore])&&s.length()-s.replace(".", "").length()==1) return false;
        }catch(Exception e){
            return true;
        }
        }
        for(char c:s.toCharArray()) if(!Character.isAlphabetic(c)&&!Character.isDigit(c)) return true;
        return false;
    //return (s == null) ? false : s.matches("[^A-Za-z0-9 ]");
    }
    public static boolean checkDuplicate(ArrayList<companyInfor> arr,String tempID){
        try{
        if(arr.isEmpty()) return false;
        Iterator<companyInfor> iter = arr.iterator();
        while(iter.hasNext()) {
            companyInfor cmp = iter.next();
            if(cmp instanceof Request) if(((Request)cmp).getrID().equals(tempID)) return true;
            if(cmp instanceof Borrow) if(((Borrow)cmp).getbID().equals(tempID)) return true;
            if(cmp.getID().equals(tempID)) return true;
        }
        return false;
        }catch(Exception e){
            return false;
        }
    }
    private static boolean isValidDate(String date,DateTimeFormatter dtf){        
        try{
            LocalDate.parse(date,dtf);
        }catch(DateTimeParseException e){
            return false;
        }
        return true;
    }
    public static int checkValidDateTime(String DateTime){
        if(DateTime.isEmpty()) return -1;
        for(char c:DateTime.toCharArray()) if(Character.isAlphabetic(c)) return -3;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
        if(!isValidDate(DateTime,dtf)) return 0; 
        LocalDate lc = LocalDate.parse(DateTime,dtf);
        System.out.printf(lc.toString());
        if(lc.getYear()>LocalDate.now().getYear()) return -2;
        return 1;
    }
    public static int checkValidBirthDate(String BirthDate){
        if(BirthDate.isEmpty()) return -1;
        for(char c:BirthDate.toCharArray()) if(Character.isAlphabetic(c)) return -3;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
        if(!isValidDate(BirthDate,dtf)) return 0; 
        LocalDate lc = LocalDate.parse(BirthDate,dtf);
        if(lc.getYear()>LocalDate.now().getYear()) return -2;
        return 1;
    }
    public static boolean checkMatch(String src,String check){
        return src.matches(check);
    }
    public static boolean checkId(ArrayList<companyInfor> arr,String tmpId,boolean print,boolean checkDup){
        if(arr.isEmpty()) checkDup=false;
        if(print){
            if(tmpId.isEmpty()) {
                System.out.println("  Invalid input!\n Id cannot be empty!");
                return false;
            }if(checkDuplicate(arr,tmpId)) {
                if(checkDup){
                    System.out.println("  Id already exsited!");
                    return false;
                }
            }
            if(containSpace(tmpId)) {
                System.out.println("  Invalid input!\n  Id cannot contain spaces");
                return false;
            }
            return true;
        }else if(checkDup){
            if(tmpId.isEmpty()||checkDuplicate(arr,tmpId)||containSpace(tmpId)) return false;
        }else if(tmpId.isEmpty()||containSpace(tmpId)) return false;
        return true;
    }
    public static boolean checkLoginInfor(ArrayList<companyInfor> eArr,String tmpID,String tmpPass,boolean print){
        boolean flag1=false,flag2=false;
        if(checkDuplicate(eArr,tmpID)) flag1=true;
        Iterator<companyInfor> iter = eArr.iterator();
        while(iter.hasNext()) {
            Employee e = (Employee)iter.next();
            if(e.getPassword().equals(tmpPass)) flag2=true;
        }
        if(print&&!(flag1&&flag2)) System.out.printf("  Incorrect ID or password!\n");
        return flag1&&flag2;
    }
    public static boolean checkName(String tmpName,boolean print){
        if(print){
            if(tmpName.isEmpty()) {
                System.out.printf(" Name cannot be empty!");
                return false;   
            }
        }if(tmpName.isEmpty()) return false;
        else return true;
    }
    public static boolean checkColor(String tmpColor,boolean print){
        if(tmpColor.isEmpty()){
            if(print) {
                System.out.printf(" Color cannot be empty!");
                return false;
            }else return false;
        }return true;
    }
    public static boolean checkPrice(String tmpPrice,boolean print){
        if(print){
        switch(containNumber(tmpPrice)){
            case -2: 
                System.out.println("  Invalid input!\n Price cannot be empty!");
                return false;
            case -1:
                System.out.println("  Invalid input!\n Price cannot contain letters!");
                return false;
            case 0:
                System.out.println("  Invalid input!\n Price cannot contain spaces!");
                return false;
            case -3:
                System.out.println("  Invalid input!\n  Price cannot contain special character!");
                return false;
            case -4:
                System.out.println("  Invalid input!\n  Invalid price input!");
                return false;        
            default:
                if(Double.parseDouble(tmpPrice)<=0){
                    System.out.println("  Invalid input!\n  Price cannot be negative!");
                    return false;
                } 
                return true;
            }
        }else if(containNumber(tmpPrice)==1) return Double.parseDouble(tmpPrice)>0;
        else return false;
    }
     public static boolean checkWeight(String tmpWeight,boolean print){
        if(print){
        switch(containNumber(tmpWeight)){
            case -2: 
                System.out.println("  Invalid input!\n Weight cannot be empty!");
                return false;
            case -1:
                System.out.println("  Invalid input!\n Weight cannot contain letters!");
                return false;
            case 0:
                System.out.println("  Invalid input!\n Weight cannot contain spaces!");
                return false;
            case -3:
                System.out.println("  Invalid input!\n  Weight cannot contain special character!");
                return false;
            case -4:
                System.out.println("  Invalid input!\n  Invalid weight input!");
                return false;  
            default:
                if(Double.parseDouble(tmpWeight)<=0){
                    System.out.println("  Invalid input!\n  weight cannot be negative!");
                    return false;
                } 
                return true;
            }
        }else if(containNumber(tmpWeight)==1) return Double.parseDouble(tmpWeight)>0;
        else return false;
    }
     public static boolean checkQuantity(String tmpQuantity,boolean print){
        if(print){
        switch(containNumber(tmpQuantity)){
            case -2: 
                System.out.println("  Invalid input!\n Quantity cannot be empty!");
                return false;
            case -1:
                System.out.println("  Invalid input!\n Quantity cannot contain letters!");
                return false;
            case 0:
                System.out.println("  Invalid input!\n Quantity cannot contain spaces!");
                return false;
            case -3:
                System.out.println("  Invalid input!\n  Quantity cannot contain special character!");
                return false;
            case -4:
                System.out.println("  Invalid input!\n  Invalid quantity input!");
                return false;  
            default:
                if(Double.parseDouble(tmpQuantity)<=0){
                    System.out.println("  Invalid input!\n  Quantity cannot be negative!");
                    return false;
                } 
                return true;
            }
        }else if(containNumber(tmpQuantity)==1) return Double.parseDouble(tmpQuantity)>0;
        else return false;
    }
    public static boolean checkRole(String tmpRole,boolean print){
        if(tmpRole.isEmpty()){
            if(print) {
                System.out.printf(" Role cannot be empty!");
                return false;
            }else return false;
        }return true;
    }
    public static boolean checkSex(String tmpSex,boolean print){
        if(!tmpSex.toUpperCase().equals("MALE")&&!tmpSex.toUpperCase().equals("FEMALE")) return false; 
        return true;
    }
    public static boolean checkType(String tmpType,boolean print){
        if(tmpType.isEmpty()){
            if(print){
                System.out.println("  Invalid input!\n Car type cannot be empty!");
                return false;
            }else return false;
        }return true;
    }
    public static boolean checkDateTime(String DateTime,boolean print){
        if(print){
            switch(checkValidDateTime(DateTime)){
                case -1:
                    System.out.println("  Invalid input!\n Date cannot be empty!");
                    return false;
                case 0:
                    System.out.printf("\n  Invalid input!\n Invaid format(Must be yyyy/MM/dd HH:mm:ss)");
                    return false;
                case -3:
                    System.out.printf("\n  Date cannot contains word!");
                    return false;
                case -2:
                    System.out.format("\n  Invalid input!\n Date out of range!(must be in between 1600 and %s)",LocalDate.now().getYear());
                default:
                    return true;
            }
        }else if(checkValidDateTime(DateTime)==1) return true;
        else return false;
    }
    public static boolean checkBirthDate(String BirthDate,boolean print){
        if(print){
            switch(checkValidBirthDate(BirthDate)){
                case -1:
                    System.out.println("  Invalid input!\n Date cannot be empty!");
                    return false;
                case 0:
                    System.out.printf("\n  Invalid input!\n Invaid format(Must be yyyy/MM/dd)");
                    return false;
                case -3:
                    System.out.printf("\n  Date cannot contains word!");
                    return false;
                case -2:
                    System.out.format("\n  Invalid input!\n Date out of range!(must be in between 1600 and %s)",LocalDate.now().getYear());
                default:
                    return true;
            }
        }else if(checkValidBirthDate(BirthDate)==1) return true;
        else return false;
    }
    public static boolean checkPassword(String tmpPassword,boolean print,boolean checkStrong){
        if(print){
            if(tmpPassword.isEmpty()) {
                System.out.printf(" Password cannot be empty!");
                return false;
            }
            if(containSpace(tmpPassword)){
                System.out.printf(" Password cannot contain spaces!");
                return false;
            }
            if(checkStrong){
                if(tmpPassword.length()<8) {
                    System.out.printf(" Password must be longer than 8 characters!");
                    return false;
                }
                boolean flagA=false,flagB=false,flagC=false,flagD=false;
                for(char c:tmpPassword.toCharArray()) {
                    if(Character.isAlphabetic(c)) 
                        flagA=true;
                    if(Character.isDigit(c))
                        flagB=true;
                    if(Character.isUpperCase(c))
                        flagC=true;
                }if(tmpPassword.length()>12) flagD=true;  
                int Apoint=0,Bpoint=0,Cpoint=0,Dpoint=0,Score;
                if(flagA) Apoint = 5;
                if(flagB) Bpoint = 7;
                if(flagC) Cpoint = 5;
                if(flagD) Dpoint = 12;
                Score = Apoint+Bpoint+Cpoint+Dpoint;
                String strongNess="";
                for(int i=0;i<Score;i++){
                    strongNess+="#";
                }while(strongNess.length()<29) strongNess+="-";
                System.out.println(" Weak                 Strong");
                System.out.println(strongNess);
            }            
            }else if(tmpPassword.isEmpty()||containSpace(tmpPassword)||tmpPassword.length()<8) return false;
        return true;
    }
    //Swappers
    public static String checkToSwap(String New,String Old,boolean proceed){
        if(proceed) return New;
        else {
            System.out.printf("  Invalid input!\n Changes may not applied!");
            return Old;
        }
    }
    public static int checkToSwapInt(int New,int old,boolean proceed){
        if(proceed) return New;
        else{
            System.out.printf("  Insufficient quantity!\n Changes many not applied!");
            return old;
        }
    }

}
    
    