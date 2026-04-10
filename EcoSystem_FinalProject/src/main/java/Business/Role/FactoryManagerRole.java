/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Role;

import Business.Enterprise.Enterprise;
import Business.Organization;
import Business.Person.PersonAccount;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import Business.System.System;
import ui.FactoryManagerWorkArea.FactoryManagerWorkAreaJPanel;

/**
 *
 * @author krystinfalcone
 */
public class FactoryManagerRole extends Role {
    
    public FactoryManagerRole(PersonAccount p) {
        super(p, RoleType.FactoryManager);
    }
    @Override 
    public String getRole() {
        return "Factory Manager";
    }
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, System system) {
        return new FactoryManagerWorkAreaJPanel(userProcessContainer, account, organization, enterprise, system);
    }
}
