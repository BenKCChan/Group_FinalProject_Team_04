package Business.System;

import Business.Enterprise.Enterprise;
import Business.UserAccount.UserAccount;

import Business.Network.Network;
import Business.Organization;
import Business.Role.AdminDirectory;
import Business.Role.AdminRole;
import Business.Role.AuditorDirectory;
import Business.Role.FactoryAnalystDirectory;
import Business.Role.FactoryInventoryControlDirectory;
import Business.Role.FactoryManagerDirectory;
import Business.Role.FleetMonitorDirectory;
import Business.Role.SupplierAnalystDirectory;
import Business.Role.SupplierDirectory;
import Business.Role.SupplierInventoryControlDirectory;
import Business.utils.RealTimeOilAPI;
import com.github.javafaker.Faker;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author rrheg
 */
public class ConfigureASystem {
    
    public static System initialize() throws IOException, InterruptedException{
        
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
        
        // Create Admin
        UserAccount admin = orgAdmin.getUserAccountDirectory().newUserAccount("admin", "****");
        AdminDirectory adminDirectory = system.getAdminDirectory();
        AdminRole adminRole0 = adminDirectory.newAdminRole(admin);
        
        // Define all role directory
        AuditorDirectory auditorDirectory = system.getAuditorDirectory();
        FactoryAnalystDirectory factoryAnalystDirectory = system.getFactoryAnalystDirectory();
        FactoryInventoryControlDirectory factoryInventoryControlDirectory = system.getFactoryInventoryControlDirectory();
        FactoryManagerDirectory factoryManagerDirectory = system.getFactoryManagerDirectory();
        FleetMonitorDirectory fleetMonitorDirectory = system.getFleetMonitorDirectory();
        SupplierAnalystDirectory supplierAnalystDirectory = system.getSupplierAnalystDirectory();
        SupplierDirectory supplierDirectory = system.getSupplierDirectory();
        SupplierInventoryControlDirectory supplierInventoryControlDirectory = system.getSupplierInventoryControlDirectory();
       
        // Create dummy account
        ArrayList<Enterprise> generatedEnterpriseList = new ArrayList<Enterprise>();
        for (int i = 0; i<10; i++){
            Faker faker = new Faker();
            Enterprise generatedEnterprise = network.newEnterprise(faker.address().country());
            generatedEnterprise.newOrganization("Financial Analyst Team");
            generatedEnterprise.newOrganization("Purchasing Dept");
            for (Organization org: generatedEnterprise.getParticipatingunits()){
                for (int createUserCount = 0; createUserCount < 10; createUserCount++){
                    UserAccount generatedUser = org.getUserAccountDirectory().newUserAccount(faker.name().username(), faker.internet().password());
                    int random = faker.number().numberBetween(0, 8);
                    switch (random){
                        case 0:
                            adminDirectory.newAdminRole(generatedUser);
                        case 1:
                            auditorDirectory.newAuditorRole(generatedUser);
                        case 2:
                            factoryAnalystDirectory.newFactoryAnalystRole(generatedUser);
                        case 3:
                            factoryInventoryControlDirectory.newFactoryInventoryControlRole(generatedUser);
                        case 4:
                            factoryManagerDirectory.newFactoryManagerRole(generatedUser);
                        case 5:
                            supplierAnalystDirectory.newSupplierAnalystRole(generatedUser);
                        case 6:
                            supplierDirectory.newSupplierRole(generatedUser);
                        case 7:
                            supplierInventoryControlDirectory.newSupplierInventoryContorlRole(generatedUser);
                        default:
                            fleetMonitorDirectory.newFleetMonitorRole(generatedUser);
                    }
                }
            }
            generatedEnterpriseList.add(generatedEnterprise);
        }

        return system;
    };
    
    
}
