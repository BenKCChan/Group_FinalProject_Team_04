/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Business.Inventory.OilInventory;
import Business.Person.PersonDirectory;
import Business.Role.AdminDirectory;
import Business.Role.SupplierDirectory;
import Business.UserAccount.UserAccountDirectory;
import Business.Operations.Workspace;
import Business.Role.AnalystDirectory;
import Business.Role.FactoryManagerDirectory;
import Business.Role.InventoryControlDirectory;
import java.util.ArrayList;
/**
 *
 * @author ben
 */
public class Organization {

    String name;
    PersonDirectory personDirectory; // all people profile regradless of the role

    AdminDirectory adminDirectory;
    UserAccountDirectory userAccountDirectory;
    SupplierDirectory supplierDirectory;
    AnalystDirectory analystDirectory;
    FactoryManagerDirectory factoryManagerDirectory;
    InventoryControlDirectory inventoryControlDirectory;
    OilInventory oilInventory;

    ArrayList<Workspace> operations;

    public Organization(String n){
        name = n;

        personDirectory = new PersonDirectory();
        adminDirectory = new AdminDirectory();
        userAccountDirectory = new UserAccountDirectory();
        supplierDirectory = new SupplierDirectory();
        analystDirectory = new AnalystDirectory();
        factoryManagerDirectory = new FactoryManagerDirectory();
        inventoryControlDirectory = new InventoryControlDirectory();
        oilInventory = new OilInventory();
        ArrayList<Organization> subordinateorgs;

    }

    public Organization(){
        name = "noname";

        personDirectory = new PersonDirectory();
        adminDirectory = new AdminDirectory();
        userAccountDirectory = new UserAccountDirectory();
        supplierDirectory = new SupplierDirectory();
        analystDirectory = new AnalystDirectory();
        factoryManagerDirectory = new FactoryManagerDirectory();
        inventoryControlDirectory = new InventoryControlDirectory();
        oilInventory = new OilInventory();
        ArrayList<Organization> subordinateorgs;

    }

    public PersonDirectory getPersonDirectory() {
        return personDirectory;
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
    public String getName() {
        return name;
    }

}
