/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import Business.System.System;
import ui.FactoryManagerWorkArea.FactoryManagerWorkAreaJPanel;

/**
 *
 * @author krystinfalcone
 */
public class FactoryManagerRole extends Role {

    public FactoryManagerRole(UserAccount p) {
        super(p, RoleType.FactoryManager);
    }
    @Override
    public String getRole() {
        return RoleType.FactoryManager.getValue();
    }
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, System system) {
        return new FactoryManagerWorkAreaJPanel(userProcessContainer, account, organization, enterprise, system);
    }
}
