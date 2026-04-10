package Business;

//import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.UserAccount.UserAccount;
import Business.System.System;
import Business.Organization;
import Business.Network.Network;
import Business.Person.PersonAccount;
import Business.Person.PersonDirectory;
import Business.Role.AdminDirectory;
import Business.Role.AdminRole;
import Business.Role.AnalystRole;
import Business.Role.FactoryManagerRole;
import Business.Role.InventoryControlRole;
import Business.UserAccount.UserAccountDirectory;
/**
 *
 * @author rrheg
 */
public class ConfigureASystem {

    public static System initialize(){
        System system = new System("Oil_manufacturing");

        // Create 1 network
        Network network = system.newNetwork("Oil Supply Chain Network");

        // Create 3 Enterprise under the same network
        Enterprise e1 = network.newEnterprise("India Factory");
        Enterprise e2 = network.newEnterprise("Japan Factory");
        Enterprise e3 = network.newEnterprise("UAE Oil Supplier");

        // create enterprise Admin
        Enterprise enterpriseAdmin = network.newEnterprise("Admin");

        // Add admin org to enterprise admin
        Organization orgAdmin = enterpriseAdmin.newOrganization("Admin");

        // Each Enterprise create 2 Org
        Organization e1_org1 = e1.newOrganization("Financial Analyst Team");
        Organization e1_org2 = e1.newOrganization("Purchasing Dept");

        Organization e2_org1 = e2.newOrganization("Financial Analyst Team");
        Organization e2_org2 = e2.newOrganization("Purchasing Dept");

        Organization e3_org1 = e3.newOrganization("Financial Analyst Team");
        Organization e3_org2 = e3.newOrganization("Purchasing Dept");

        // Create Admin
        PersonDirectory personDirectory = orgAdmin.getPersonDirectory();
        PersonAccount admin = personDirectory.newPerson("Admin");
        AdminDirectory adminDirectory = system.getAdminDirectory();
        AdminRole adminRole0 = adminDirectory.newAdminRole(admin);
        orgAdmin.getUserAccountDirectory().newUserAccount(adminRole0, "admin", "****");

        // Create Analyst, Manager and Inventory
        PersonAccount e1AnalystPerson = e1_org1.getPersonDirectory().newPerson("India Analyst");
        AnalystRole e1AnalystRole = e1_org1.getAnalystDirectory().newAnalystRole(e1AnalystPerson);
        e1_org1.getUserAccountDirectory().newUserAccount(e1AnalystRole, "analyst_india", "****");
        
        PersonAccount e1ManagerPerson = e1_org2.getPersonDirectory().newPerson("India Manager");
        FactoryManagerRole e1ManagerRole = e1_org2.getFactoryManagerDirectory().newFactoryManagerRole(e1ManagerPerson);
        e1_org2.getUserAccountDirectory().newUserAccount(e1ManagerRole, "manager_india", "****");
        
        PersonAccount e1InvPerson = e1_org2.getPersonDirectory().newPerson("India Inventory");
        InventoryControlRole e1InvRole = e1_org2.getInventoryControlDirectory().newInventoryControlRole(e1InvPerson);
        e1_org2.getUserAccountDirectory().newUserAccount(e1InvRole, "inventory_india", "****");
        
        PersonAccount e2AnalystPerson = e2_org1.getPersonDirectory().newPerson("Japan Analyst");
        AnalystRole e2AnalystRole = e2_org1.getAnalystDirectory().newAnalystRole(e2AnalystPerson);
        e2_org1.getUserAccountDirectory().newUserAccount(e2AnalystRole, "analyst_japan", "****");
        
        PersonAccount e2ManagerPerson = e2_org2.getPersonDirectory().newPerson("Japan Manager");
        FactoryManagerRole e2ManagerRole = e2_org2.getFactoryManagerDirectory().newFactoryManagerRole(e2ManagerPerson);
        e2_org2.getUserAccountDirectory().newUserAccount(e2ManagerRole, "manager_japan", "****");
        
        PersonAccount e2InvPerson = e2_org2.getPersonDirectory().newPerson("Japan Inventory");
        InventoryControlRole e2InvRole = e2_org2.getInventoryControlDirectory().newInventoryControlRole(e2InvPerson);
        e2_org2.getUserAccountDirectory().newUserAccount(e2InvRole, "inventory_japan", "****");
        
        return system;
    };


}
