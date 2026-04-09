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
import Business.Role.SupplierAnalystRole;
import Business.Role.SupplierInventoryContorlRole;
import Business.Role.SupplierRole;
import Business.utils.RealTimeOilAPI;
import java.io.IOException;

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

// Transport Coordinator and Logistics Analyst roles go here
// once you create those role classes
// Pre-populate RequestBoard
        RequestBoard rb = system.getRequestBoard();
        rb.newBuyRequest("MGR-001", "India Factory", 5000, 80.00, "2026-04-01");
        rb.newBuyRequest("MGR-001", "Japan Factory", 8000, 77.50, "2026-04-03");
        rb.newBuyRequest("MGR-002", "India Factory", 3200, 82.00, "2026-04-06");
        rb.newBuyRequest("MGR-002", "Japan Factory", 6100, 79.00, "2026-04-07");
        rb.newBuyRequest("MGR-001", "India Factory", 4400, 81.50, "2026-04-08");

        // Create Admin
        UserAccount admin = orgAdmin.getUserAccountDirectory().newUserAccount("admin", "****");
        AdminDirectory adminDirectory = system.getAdminDirectory();
        AdminRole adminRole0 = adminDirectory.newAdminRole(admin);

        return system;
    }
;

}
