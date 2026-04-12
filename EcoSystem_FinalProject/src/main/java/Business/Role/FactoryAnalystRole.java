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

/**
 *
 * @author kal bugrara
 */
public class FactoryAnalystRole extends Role {

    public FactoryAnalystRole(UserAccount p) {
        super(p, RoleType.FactoryAnalyst);
    }

<<<<<<< HEAD
=======

>>>>>>> main
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, System system) {

        return new AdminWorkAreaJPanel(userProcessContainer, account, organization, enterprise, system);
    }

    @Override
    public String getRole() {
        return RoleType.FactoryAnalyst.getValue();
    }
}
