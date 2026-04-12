/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Enterprise.Enterprise;
import Business.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import ui.SystemAdminWorkArea.AdminWorkAreaJPanel;

/**
 *
 * @author kal bugrara
 */
import Business.System.System;
public class AdminRole extends Role {



    public AdminRole(UserAccount p) {

        super(p, RoleType.Admin); 

    }
    @Override
    public String getRole(){
        return  RoleType.Admin.getValue();
    }
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, System system) {
        
        return new AdminWorkAreaJPanel(userProcessContainer, account, organization, enterprise, system);
    }

}