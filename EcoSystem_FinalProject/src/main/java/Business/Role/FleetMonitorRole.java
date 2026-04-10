/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Enterprise.Enterprise;
import Business.Organization;
import Business.UserAccount.UserAccount;
import Business.System.System;
import javax.swing.JPanel;
import ui.SystemAdminWorkArea.AdminWorkAreaJPanel;
import ui.utils.IranHormuzMapJPanel;

/**
 *
 * @author kal bugrara
 */
public class FleetMonitorRole extends Role {

    UserAccount person;
    
    public FleetMonitorRole(UserAccount p) {
        super(p, RoleType.FleetMonitor);
    }

    public boolean isMatch(String id) {
        return person.getId().equals(id);
    }

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, System system) {
        
        return new IranHormuzMapJPanel(userProcessContainer);
    }

    @Override
    public String getRole(){
        return  RoleType.FleetMonitor.getValue();
    }
}
