/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Enterprise.Enterprise;
import Business.Organization;
import Business.Person.PersonAccount;
import Business.UserAccount.UserAccount;
import Business.System.System;
import javax.swing.JPanel;
import ui.SystemAdminWorkArea.AdminWorkAreaJPanel;

/**
 *
 * @author kal bugrara
 */
public class SupplierRole extends Role {

    PersonAccount person;
    
    public SupplierRole(PersonAccount p) {
        super(p, RoleType.Supplier);
    }

    public boolean isMatch(String id) {
        return person.getPersonId().equals(id);
    }

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, System system) {
        
        return new AdminWorkAreaJPanel(userProcessContainer, system);
    }

    @Override
    public String getRole(){
        return  "OilSupplier";
    }
}
