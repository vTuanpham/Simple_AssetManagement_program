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
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.util.Objects;
public class Controller implements InController{
    InforList Asset;
    InforList Employee;
    InforList Request;
    InforList Borrow;
    Scanner sc = new Scanner(System.in);
    public Controller(InforList Asset,InforList Employee,InforList Request,InforList Borrow){
        this.Asset = Asset;
        this.Employee = Employee;
        this.Request = Request;
        this.Borrow = Borrow;
    }
    @Override
    public void add(){
        companyInfor vc = null;
        Menu menuInforType = new Menu();
        Scanner sc = new Scanner(System.in);
        System.out.printf("  Enter Information type: ");
        menuInforType.add("Asset");
        menuInforType.add("Employee");
        menuInforType.showMenu();
        int InforType = menuInforType.getChoice();
        switch(InforType){
            case 1:
                System.out.printf(" Enter Information id: ");
                String tmpID;
                do{
                    tmpID = sc.nextLine();
                if(!checker.checkId(Asset.getList(), tmpID, true, true)){
                    System.out.printf("  Please enter again: ");
                }
                }while(!checker.checkId(Asset.getList(),tmpID,false,true));
                vc = new Asset(tmpID);
                Asset.add(vc);
                vc.add();
                break;
            case 2:
                System.out.printf(" Enter Information id: ");
                do{
                    tmpID = sc.nextLine();
                if(!checker.checkId(Employee.getList(), tmpID, true, true)){
                    System.out.printf("  Please enter again: ");
                }
                }while(!checker.checkId(Employee.getList(),tmpID,false,true));
                vc = new Employee(tmpID);
                Employee.add(vc);
                vc.add();
                break;
        }
    }
    @Override
    public void update(){
        String tmpId;
        Menu menuUpdate = new Menu();
        menuUpdate.addItem("Update Infor name");
        System.out.printf(" Enter Infor id to update: ");
        do{
            tmpId = sc.nextLine();
            if(!checker.checkId(Asset, tmpId, true,false)) System.out.printf("  \nPlease enter again: ");
            else break;
        }while(!checker.checkId(Asset, tmpId, false,false));
        companyInfor vca = Asset.searchById(tmpId,false);
        companyInfor vce = Employee.searchById(tmpId, false);
        companyInfor vc = null;
        if(Objects.isNull(vca)&&Objects.isNull(vce)) System.out.printf("  \nId not found, update aborted!\n");
        else {
            if(!Objects.isNull(vca)) vc = vca;
            else vc = vce;
            boolean ans = false;
            if(vc instanceof Asset) {
                menuUpdate.addItem("Update Asset color");
                menuUpdate.addItem("Update Asset price");
                menuUpdate.addItem("Update Asset weight");
                menuUpdate.addItem("Update Asset quantity");
                menuUpdate.addItem("Update all");
            }else{
                menuUpdate.addItem("Update Employee birthdate");
                menuUpdate.addItem("Update Employee role");
                menuUpdate.addItem("Update Employee sex");
                menuUpdate.addItem("Update Employee password");
                menuUpdate.addItem("Update all");
                }
            do{
                menuUpdate.showMenu();
                vc.update(menuUpdate,menuUpdate.getChoice());
                System.out.printf(" Continue updating this Infor? (Y/N): ");
                ans = Menu.getYesNo(sc.nextLine());
                if (!ans) {
                    System.out.printf("  \nUpdate %s finished!\n",vc.getID());
                    break;
                }
            }while(ans);
        }
    }
    
    
    public void searchInforByName(){
        Menu menuSearchName = new Menu();
        menuSearchName.addItem("Search Asset");
        menuSearchName.addItem("Search Employee");
        menuSearchName.addItem("Search dataBase");
        menuSearchName.showMenu();
        int choice = menuSearchName.getChoice();
        System.out.printf("  Enter search name: ");
        String searchName = sc.nextLine();
        switch(choice){
            case 1:
               Asset.searchByName(searchName);
               break;
            case 2:
                Employee.searchByName(searchName);
                break;
            case 3:
                System.out.printf("\t\tAsset\n");
                Asset.searchByName(searchName);
                System.out.printf("\t\tEmployee\n");
                Employee.searchByName(searchName);
            default:
                return;
        }
    }
    @Override
    public void approve(){
        Menu menuApprove = new Menu();
        Asset.showList();
        Request.showList();
        System.out.printf("  Enter request ID: ");
        companyInfor r = Request.searchById(sc.nextLine(), false);
        if(Objects.isNull(r)) {
            System.out.println("  ID not found!");
            return ;
        }
        companyInfor a = Asset.searchById(((Request)r).getaID(),false);
        /*
        try{
            a = Asset.searchById(((Request)r).getrID(),false);
        }catch(Exception e){
            System.out.println("  Invalid ID!");
            return;
        }
        */
        menuApprove.addItem("Approve");
        menuApprove.addItem("Disapprove");
        menuApprove.addItem("Quit");
        menuApprove.showMenu();
        int choice = menuApprove.getChoice();
        switch(choice){
            case 1:
                int quantity = checker.checkToSwapInt(((Asset)a).getQuantity()-((Request)r).getAquantity(),((Asset)a).getQuantity(), ((Asset)a).getQuantity()>((Request)r).getAquantity());
                if(quantity==((Asset)a).getQuantity()) Request.remove(r);
                else {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                    LocalDateTime now = LocalDateTime.now();
                    String id = String.format("B%06d",now.getDayOfMonth()+now.getHour()+now.getSecond());
                    Borrow.add(new Borrow(id,a.getID(),((Request)r).geteID(),((Request)r).getAquantity(),dtf.format(now)));
                    Request.remove(r);
                    ((Asset)a).setQuantity(quantity);
                }
                break;
            case 2:
                System.out.println("  Request declined..");
                System.out.println("  Removed Request");
                Request.remove(r);
            default:
                return;
        }
    }
    @Override
    public void showList(){
        Menu menuShow = new Menu();
        menuShow.addItem("Show Asset");
        menuShow.addItem("Show Employee");
        menuShow.addItem("Show Request");
        menuShow.addItem("Show borrow");
        menuShow.addItem("Show All");
        menuShow.showMenu();
        int choice = menuShow.getChoice();
        switch(choice){
            case 1:
                Asset.showList();
                break;
            case 2:
                Employee.showList();
                break;
            case 3:
                Request.showList();
                break;
            case 4:
                Borrow.showList();
                break;
            case 5:
                Asset.showList();
                Employee.showList();
                Request.showList();
                Borrow.showList();
        }
        
    }
    //Methods
    @Override
    public boolean login(){
        Scanner sc = new Scanner(System.in);
        String tmpID,tmpPassword;
        do{
            System.out.printf("  Enter your ID: ");
            tmpID = sc.nextLine();
            System.out.printf("  Enter your password: ");
            tmpPassword = sc.nextLine();
            checker.checkLoginInfor(Employee,tmpID,tmpPassword,true);
        }while(!checker.checkLoginInfor(Employee, tmpID, tmpPassword, false));
        return true;
    }
    @Override
    public void save(){
        //AssetSave
        fileEditor afE = new fileEditor();
        afE.setdirPath("C:\\Users\\Tuan Pham\\OneDrive\\Desktop\\Study\\Spring2022_cn3\\LAB\\Project",new Asset());
        afE.fileCreate();
        afE.fileWrite(Asset);
        //EmployeeSave
        fileEditor efE = new fileEditor();
        efE.setdirPath("C:\\Users\\Tuan Pham\\OneDrive\\Desktop\\Study\\Spring2022_cn3\\LAB\\Project",new Employee());
        efE.fileCreate();
        efE.fileWrite(Employee);
        //RequestSave
        fileEditor rfE = new fileEditor();
        rfE.setdirPath("C:\\Users\\Tuan Pham\\OneDrive\\Desktop\\Study\\Spring2022_cn3\\LAB\\Project",new Request());
        rfE.fileCreate();
        rfE.fileWrite(Request);
        //BorrowSave
        fileEditor bfE = new fileEditor();
        bfE.setdirPath("C:\\Users\\Tuan Pham\\OneDrive\\Desktop\\Study\\Spring2022_cn3\\LAB\\Project",new Borrow());
        bfE.fileCreate();
        bfE.fileWrite(Borrow);
    }
    @Override
    public void load(){
        //AssetLoad
        fileEditor afE = new fileEditor();
        afE.setdirPath("C:\\Users\\Tuan Pham\\OneDrive\\Desktop\\Study\\Spring2022_cn3\\LAB\\Project",new Asset());
        afE.fileRead();
        afE.setdirPath("C:\\Users\\Tuan Pham\\OneDrive\\Desktop\\Study\\Spring2022_cn3\\LAB\\Project",new Employee());
        afE.fileRead();
        afE.setdirPath("C:\\Users\\Tuan Pham\\OneDrive\\Desktop\\Study\\Spring2022_cn3\\LAB\\Project",new Request());
        afE.fileRead();
        afE.setdirPath("C:\\Users\\Tuan Pham\\OneDrive\\Desktop\\Study\\Spring2022_cn3\\LAB\\Project",new Borrow());
        afE.fileRead();
        afE.readFormat(Asset,Employee,Request,Borrow);



       
        
        
        /*
        //EmployeeLoad
        fileEditor efE = new fileEditor();
        efE.setdirPath("C:\\Users\\Tuan Pham\\OneDrive\\Desktop\\Study\\Spring2022_cn3\\LAB\\Project",new Employee());
        efE.fileRead();
        efE.readFormat(Asset,Employee,Request,Borrow);
        //RequestLoad
        fileEditor rfE = new fileEditor();
        rfE.setdirPath("C:\\Users\\Tuan Pham\\OneDrive\\Desktop\\Study\\Spring2022_cn3\\LAB\\Project",new Request());
        rfE.fileRead();
        rfE.readFormat(Asset,Employee,Request,Borrow);
        //BorrowLoad
        fileEditor bfE = new fileEditor();
        bfE.setdirPath("C:\\Users\\Tuan Pham\\OneDrive\\Desktop\\Study\\Spring2022_cn3\\LAB\\Project",new Borrow());
        bfE.fileRead();
        bfE.readFormat(Asset,Employee,Request,Borrow);
        */
    }
    
}
