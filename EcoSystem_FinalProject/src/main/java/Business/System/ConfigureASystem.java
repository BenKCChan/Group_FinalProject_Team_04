package Business.System;

//import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.UserAccount.UserAccount;
//import Business.System.System;
import Business.Network.Network;
import Business.Operations.RequestBoard;
import Business.Organization;
import Business.Role.AdminDirectory;
import Business.Role.AdminRole;
import Business.Role.LogisticsAnalystRole;
import Business.Role.SupplierAnalystRole;
import Business.Role.SupplierInventoryContorlRole;
import Business.Role.SupplierRole;
import Business.Role.TransportCoordinatorRole;
import Business.utils.RealTimeOilAPI;
import java.io.IOException;


import Business.utils.FakerDataGenerator;
import static java.time.Clock.system;
import static java.time.InstantSource.system;

/**
 *
 * @author rrheg
 */
public class ConfigureASystem {

    public static System initialize() throws IOException, InterruptedException, Exception {

        // Oil Price API
        RealTimeOilAPI oilApi = new RealTimeOilAPI();
        String oilPrice;
        oilPrice = oilApi.refreshNow();
        

        System system = new System("Oil_manufacturing");
        
        RequestBoard rb = system.getRequestBoard();

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

        // Add third org to e3
        Organization e3_org3 = e3.newOrganization("Oil Inventory Dept");

        // Oil Analyst
        UserAccount uaAnalyst = e3_org1.getUserAccountDirectory().newUserAccount("oil.analyst", "Pass@123");
        SupplierAnalystRole analystRole = new SupplierAnalystRole(uaAnalyst);
        e3_org1.getRoleDirectory().addRole(analystRole);

        // Oil Supplier Agent
        UserAccount uaAgent = e3_org2.getUserAccountDirectory().newUserAccount("oil.supplier", "Pass@123");
        SupplierRole agentRole = new SupplierRole(uaAgent);
        e3_org2.getRoleDirectory().addRole(agentRole);

        // Oil Inventory Control
        UserAccount uaInv = e3_org3.getUserAccountDirectory().newUserAccount("oil.inventory", "Pass@123");
        SupplierInventoryContorlRole invRole = new SupplierInventoryContorlRole(uaInv);
        e3_org3.getRoleDirectory().addRole(invRole);

        // Logistics Corp
        Enterprise e4 = network.newEnterprise("Logistics Corp");
        Organization e4_org1 = e4.newOrganization("Shipment Operations");
        Organization e4_org2 = e4.newOrganization("Fleet Management");

        // Transport Coordinator
        UserAccount uaTC = e4_org1.getUserAccountDirectory().newUserAccount("transport.coord", "Pass@123");
        TransportCoordinatorRole tcRole = new TransportCoordinatorRole(uaTC);
        e4_org1.getRoleDirectory().addRole(tcRole);

        // Logistics Analyst  
        UserAccount uaLA = e4_org2.getUserAccountDirectory().newUserAccount("logistics.analyst", "Pass@123");
        LogisticsAnalystRole laRole = new LogisticsAnalystRole(uaLA);
        e4_org2.getRoleDirectory().addRole(laRole);

       // Retrieve accounts for Faker population
        UserAccount analystAccount = e3_org1.getUserAccountDirectory().findUserAccount("oil.analyst");
        UserAccount agentAccount = e3_org2.getUserAccountDirectory().findUserAccount("oil.supplier");

        // Generate 20 historical records using Faker
        FakerDataGenerator.populate(rb, analystAccount, agentAccount, 20); 
       

        // Create Admin
        UserAccount admin = orgAdmin.getUserAccountDirectory().newUserAccount("admin", "****");
        AdminDirectory adminDirectory = system.getAdminDirectory();
        AdminRole adminRole0 = adminDirectory.newAdminRole(admin);

        return system;
    }
;

}
