/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Organization;

import Business.Inventory.OilInventory;
import Business.Role.AdminDirectory;
import Business.Role.SupplierDirectory;
import Business.UserAccount.UserAccountDirectory;
import Business.Operations.Workspace;
import Business.Role.AnalystDirectory;
import Business.Role.InventoryControlDirectory;
import Business.Role.AuditorDirectory;
import Business.Role.FactoryAnalystDirectory;
import Business.Role.FactoryInventoryControlDirectory;
import Business.Role.FactoryManagerDirectory;
import Business.Role.FleetMonitorDirectory;
import Business.Role.RoleDirectory;
import Business.Role.SupplierAnalystDirectory;
import Business.Role.SupplierInventoryControlDirectory;
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
    AnalystDirectory analystDirectory;
    AuditorDirectory auditorDirectory;
    FactoryInventoryControlDirectory factoryInventoryControlDirectory;
    FactoryManagerDirectory factoryManagerDirectory;
    FactoryAnalystDirectory factoryAnalystDirectory;
    InventoryControlDirectory inventoryControlDirectory;
    SupplierAnalystDirectory supplierAnalystDirectory;
    SupplierInventoryControlDirectory supplierInventoryControlDirectory;
    FleetMonitorDirectory fleetMonitorDirectory;
    OilInventory oilInventory;

    ArrayList<Workspace> operations;

    public Organization(String n){
        name = n;
 
        adminDirectory = new AdminDirectory();
        userAccountDirectory = new UserAccountDirectory();
        supplierDirectory = new SupplierDirectory();
        analystDirectory = new AnalystDirectory();
        auditorDirectory = new AuditorDirectory();
        factoryAnalystDirectory = new FactoryAnalystDirectory();
        factoryInventoryControlDirectory = new FactoryInventoryControlDirectory();
        factoryManagerDirectory = new FactoryManagerDirectory();
        inventoryControlDirectory = new InventoryControlDirectory();
        supplierAnalystDirectory = new SupplierAnalystDirectory();
        supplierInventoryControlDirectory = new SupplierInventoryControlDirectory();
        fleetMonitorDirectory = new FleetMonitorDirectory();
        oilInventory = new OilInventory();

    }

    public Organization(){
        name = "noname";
        adminDirectory = new AdminDirectory();
        userAccountDirectory = new UserAccountDirectory();
        supplierDirectory = new SupplierDirectory();
        analystDirectory = new AnalystDirectory();
        auditorDirectory = new AuditorDirectory();
        factoryAnalystDirectory = new FactoryAnalystDirectory();
        factoryInventoryControlDirectory = new FactoryInventoryControlDirectory();
        factoryManagerDirectory = new FactoryManagerDirectory();
        inventoryControlDirectory = new InventoryControlDirectory();
        supplierAnalystDirectory = new SupplierAnalystDirectory();
        supplierInventoryControlDirectory = new SupplierInventoryControlDirectory();
        fleetMonitorDirectory = new FleetMonitorDirectory();
        oilInventory = new OilInventory();
      
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
    public AnalystDirectory getAnalystDirectory() {
        return analystDirectory;
    }
    public FactoryManagerDirectory getFactoryManagerDirectory() {
        return factoryManagerDirectory;
    }
    public InventoryControlDirectory getInventoryControlDirectory(){
        return inventoryControlDirectory;
    }
    public OilInventory getOilInventory() {
        return oilInventory;
    }

    public RoleDirectory getRoleDirectory() {
        return roleDirectory;
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

    public SupplierAnalystDirectory getSupplierAnalystDirectory() {
        return supplierAnalystDirectory;
    }

    public SupplierInventoryControlDirectory getSupplierInventoryControlDirectory() {
        return supplierInventoryControlDirectory;
    }

    public FleetMonitorDirectory getFleetMonitorDirectory() {
        return fleetMonitorDirectory;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return name;
    }

}
