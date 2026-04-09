/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Business.Role.AdminDirectory;
import Business.Role.SupplierDirectory;
import Business.UserAccount.UserAccountDirectory;
import Business.Operations.Workspace;
import Business.Role.RoleDirectory;
import java.util.ArrayList;
/**
 *
 * @author ben
 */
public class Organization {
    
    String name;
    private RoleDirectory roleDirectory = new RoleDirectory();
    AdminDirectory adminDirectory;
    UserAccountDirectory userAccountDirectory; 
    SupplierDirectory supplierDirectory;
    
    ArrayList<Workspace> operations;
    
    public Organization(String n){
        name = n;
        
        adminDirectory = new AdminDirectory();
        userAccountDirectory = new UserAccountDirectory();
        supplierDirectory = new SupplierDirectory();
        ArrayList<Organization> subordinateorgs;
        
    }
    
    public Organization(){
        name = "noname";

        adminDirectory = new AdminDirectory();
        userAccountDirectory = new UserAccountDirectory();
        supplierDirectory = new SupplierDirectory();
        ArrayList<Organization> subordinateorgs;
        
    }


    public AdminDirectory getAdminDirectory() {
        return adminDirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    public SupplierDirectory getSupplierDirectory() {
        return supplierDirectory;
    }
    
    
}
