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
import ui.UAEOilSupplier.OilInventoryWorkAreaJPanel;

/**
 *
 * @author kal bugrara
 */
public class SupplierInventoryContorlRole extends Role {
    
    public SupplierInventoryContorlRole(UserAccount p) {
        super(p, RoleType.OilSupplierInventoryControl);
    }

    @Override
public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, System system) {
    
    return new OilInventoryWorkAreaJPanel(userProcessContainer, account, organization, enterprise, system);
}

    @Override
    public String getRole(){
        return  RoleType.OilSupplierInventoryControl.getValue();
    }
}
