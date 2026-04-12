/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.System.System;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;

/**
 *
 * @author kal bugrara
 */
public abstract class Role {

    private UserAccount person;
    public RoleType type;

    public enum RoleType {
        Admin("Admin"),
        FactoryManager("Factory Manager"),
        FactoryAnalyst("Factory Analyst"),
        FactoryInventoryControl("Factory Inventory Control"),
        OilSupplier("Oil Supplier"),
        OilSupplierAnalyst("Oil Supplier Analyst"),
        OilSupplierInventoryControl("Oil Supplier Inventory Control"),
        Auditor("Trade Auditor"),
        FleetMonitor("Fleet Monitor"),
        LogisticsAnalyst("Logistics Analyst"),
        TransportCoordinator("Transport Coordinator");

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

    public Role(UserAccount p, RoleType type) {
        person = p;
        this.type = type;
    }

    public abstract String getRole();

    public UserAccount getPerson() {
        return person;
    }

    public boolean isMatch(String id) {
        return person.getId().equals(id);
    }

    public abstract JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, 
            Organization organization, Enterprise enterprise, System system);

    @Override
    public String toString() {
        return (type != null) ? this.type.getValue() : this.getClass().getName();
    }
}