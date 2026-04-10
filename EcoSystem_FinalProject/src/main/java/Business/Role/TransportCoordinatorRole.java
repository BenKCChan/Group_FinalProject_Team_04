/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Role;

/**
 *
 * @author lindq
 */
import Business.Enterprise.Enterprise;
import Business.Organization;
import Business.UserAccount.UserAccount;
import Business.System.System;
import javax.swing.JPanel;
import ui.LogisticsCorp.TransportCoordinatorWorkAreaJPanel;

public class TransportCoordinatorRole extends Role {

    public TransportCoordinatorRole(UserAccount p) {
        super(p, RoleType.TransportCoordinator);
    }

    @Override
    public String getRole() {
        return RoleType.TransportCoordinator.getValue();
    }

    @Override
    public JPanel createWorkArea(JPanel container, UserAccount account,
            Organization org, Enterprise enterprise, System system) {
        return new TransportCoordinatorWorkAreaJPanel(
                container, account, org, enterprise, system);
    }
}
