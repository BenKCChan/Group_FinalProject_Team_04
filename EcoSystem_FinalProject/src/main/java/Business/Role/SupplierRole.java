/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.System.System;
import javax.swing.JPanel;
import ui.UAEOilSupplier.OilSupplierAgentWorkAreaJPanel;

/**
 *
 * @author kal bugrara
 */
public class SupplierRole extends Role {

    UserAccount person;
    
    public SupplierRole(UserAccount p) {
        super(p, RoleType.OilSupplier);
    }

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account,
            Organization organization, Enterprise enterprise, System system) {
        return new OilSupplierAgentWorkAreaJPanel(userProcessContainer, account, organization, enterprise, system);
    }

    @Override
    public String getRole() {
        return RoleType.OilSupplier.getValue();
    }
}