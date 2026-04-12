/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Role;

import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import Business.System.System;
import ui.InventoryControlWorkArea.InventoryControlWorkAreaJPanel;

/**
 *
 * @author krystinfalcone
 */
public class InventoryControlRole extends Role {
    
    public InventoryControlRole(UserAccount p) {
        super(p, RoleType.InventoryControl);
    }
    @Override
    public String getRole(){
        return "Inventory Control";
    }
    @Override 
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, System system) {
        return new InventoryControlWorkAreaJPanel(userProcessContainer, account, organization, enterprise, system);
    }
}
