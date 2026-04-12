/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Organization;

import Business.Role.AdminDirectory;
import Business.Role.AuditorDirectory;
import Business.Role.FactoryAnalystDirectory;
import Business.Role.FactoryInventoryControlDirectory;
import Business.Role.FactoryManagerDirectory;
import Business.Role.FleetMonitorDirectory;
import Business.Role.RoleDirectory;
import Business.Role.SupplierAnalystDirectory;
import Business.Role.SupplierDirectory;
import Business.Role.SupplierInventoryControlDirectory;
import Business.Operations.Workspace;
import Business.UserAccount.UserAccountDirectory;
import java.util.ArrayList;
/**
 *
 * @author ben
 */

public class Organization {

    private String name;
    private RoleDirectory roleDirectory;
    private AdminDirectory adminDirectory;
    private UserAccountDirectory userAccountDirectory;
    private AuditorDirectory auditorDirectory;
    private FactoryAnalystDirectory factoryAnalystDirectory;
    private FactoryInventoryControlDirectory factoryInventoryControlDirectory;
    private FactoryManagerDirectory factoryManagerDirectory;
    private SupplierDirectory supplierDirectory;
    private SupplierAnalystDirectory supplierAnalystDirectory;
    private SupplierInventoryControlDirectory supplierInventoryControlDirectory;
    private FleetMonitorDirectory fleetMonitorDirectory;
    private ArrayList<Workspace> operations;
    private ArrayList<Organization> subordinateOrgs;

    public Organization(String n) {
        name = n;
        roleDirectory = new RoleDirectory();
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
        operations = new ArrayList<>();
        subordinateOrgs = new ArrayList<>();
    }

    public Organization() {
        this("noname");
    }

    public String getName() {
        return name;
    }

    public RoleDirectory getRoleDirectory() {
        return roleDirectory;
    }

    public AdminDirectory getAdminDirectory() {
        return adminDirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
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

    public SupplierDirectory getSupplierDirectory() {
        return supplierDirectory;
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

    public ArrayList<Workspace> getOperations() {
        return operations;
    }

    public ArrayList<Organization> getSubordinateOrgs() {
        return subordinateOrgs;
    }

    @Override
    public String toString() {
        return getName();
    }
}