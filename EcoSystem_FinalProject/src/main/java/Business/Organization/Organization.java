/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Business.Role.AdminDirectory;
import Business.Role.SupplierDirectory;
import Business.UserAccount.UserAccountDirectory;
import Business.Operations.Workspace;
import Business.Role.AuditorDirectory;
import Business.Role.FactoryAnalystDirectory;
import Business.Role.FactoryInventoryControlDirectory;
import Business.Role.FactoryManagerDirectory;
import Business.Role.FleetMonitorDirectory;
import Business.Role.SupplierAnalystDirectory;
import Business.Role.SupplierInventoryControlDirectory;
import java.util.ArrayList;
/**
 *
 * @author ben
 */
public class Organization {
    
    String name;
    
    AdminDirectory adminDirectory;
    UserAccountDirectory userAccountDirectory; 
    AuditorDirectory auditorDirectory;
    FactoryAnalystDirectory factoryAnalystDirectory;
    FactoryInventoryControlDirectory factoryInventoryControlDirectory;
    FactoryManagerDirectory factoryManagerDirectory;
    SupplierDirectory supplierDirectory;
    SupplierAnalystDirectory supplierAnalystDirectory;
    SupplierInventoryControlDirectory supplierInventoryControlDirectory;
    FleetMonitorDirectory fleetMonitorDirectory;
    
    ArrayList<Workspace> operations;
    
    public Organization(String n){
        name = n;
        
        adminDirectory = new AdminDirectory();
        userAccountDirectory = new UserAccountDirectory();
        supplierDirectory = new SupplierDirectory();
        auditorDirectory = new AuditorDirectory();
        factoryAnalystDirectory = new FactoryAnalystDirectory();
        factoryInventoryControlDirectory = new FactoryInventoryControlDirectory();
        factoryManagerDirectory = new FactoryManagerDirectory();
        supplierAnalystDirectory = new SupplierAnalystDirectory();
        supplierInventoryControlDirectory = new SupplierInventoryControlDirectory();
        fleetMonitorDirectory = new FleetMonitorDirectory();
        ArrayList<Organization> subordinateorgs;
        
    }
    
    public Organization(){
        name = "noname";

        adminDirectory = new AdminDirectory();
        userAccountDirectory = new UserAccountDirectory();
        supplierDirectory = new SupplierDirectory();
        auditorDirectory = new AuditorDirectory();
        factoryAnalystDirectory = new FactoryAnalystDirectory();
        factoryInventoryControlDirectory = new FactoryInventoryControlDirectory();
        factoryManagerDirectory = new FactoryManagerDirectory();
        supplierAnalystDirectory = new SupplierAnalystDirectory();
        supplierInventoryControlDirectory = new SupplierInventoryControlDirectory();
        fleetMonitorDirectory = new FleetMonitorDirectory();
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

    public AuditorDirectory getAuditorDirectory() {
        return auditorDirectory;
    }

    public FactoryAnalystDirectory getFactoryAnalystDirectory() {
        return factoryAnalystDirectory;
    }

    public FactoryInventoryControlDirectory getFactoryInventoryControlDirectory() {
        return factoryInventoryControlDirectory;
    }

    public FactoryManagerDirectory getFactoryManagerDirectory() {
        return factoryManagerDirectory;
    }

    public SupplierAnalystDirectory getSupplierAnalystDirectory() {
        return supplierAnalystDirectory;
    }

    public SupplierInventoryControlDirectory getSupplierInventoryControlDirectory() {
        return supplierInventoryControlDirectory;
    }

    public FleetMonitorDirectory getFleetMonitorDirectory() {
        return fleetMonitorDirectory;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
