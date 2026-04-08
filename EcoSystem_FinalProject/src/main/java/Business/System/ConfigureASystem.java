package Business;

//import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.UserAccount.UserAccount;
import Business.System.System;
import Business.Organization;
import Business.Network.Network;
import Business.Role.AdminDirectory;
import Business.Role.AdminRole;
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
        UserAccount admin = orgAdmin.getUserAccountDirectory().newUserAccount("admin", "****");
        AdminDirectory adminDirectory = system.getAdminDirectory();
        AdminRole adminRole0 = adminDirectory.newAdminRole(admin);

        return system;
    };
    
    
}
