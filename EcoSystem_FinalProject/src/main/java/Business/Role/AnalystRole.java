/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Role;

import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import ui.AnalystWorkArea.AnalystWorkAreaJPanel;
import Business.System.System;


/**
 *
 * @author krystinfalcone
 */
public class AnalystRole extends Role {
    
    public AnalystRole(UserAccount p) {
        super(p, RoleType.Analyst);
    }
    @Override
    public String getRole() {
        return "Analyst";
    }
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, System system) {
        return new AnalystWorkAreaJPanel(userProcessContainer, account, organization, enterprise, system);
    }
}
