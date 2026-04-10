/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Enterprise.Enterprise;
import Business.Organization;
import Business.System.System;
import Business.Person.PersonAccount;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;

/**
 *
 * @author kal bugrara
 */
public abstract class Role {
    PersonAccount person;

    public enum RoleType {
        Admin("Admin"),
        Analyst("Analyst Team"),
        Supplier("Supplier"),
        Purchase("Purchase Department"),
        FactoryManager("Factory Manager"),
        InventoryControl("Inventory Control");


        private String value;

        private RoleType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    public RoleType type;
    public Role(PersonAccount p, RoleType type){
        person = p;
        this.type = type;
    }

    public abstract String getRole();

    public PersonAccount getPerson(){
        return person;
    }

    public boolean isMatch(String id) {
        if (person.getPersonId().equals(id)) {
            return true;
        }
        return false;
    }

    public abstract JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, System system);

    @Override
    public String toString() {
        return (type != null) ? this.type.getValue() : this.getClass().getName();
    }
}
