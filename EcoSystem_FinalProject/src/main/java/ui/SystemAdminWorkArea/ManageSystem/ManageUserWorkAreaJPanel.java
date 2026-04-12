/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.SystemAdminWorkArea.ManageSystem;

import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Role.AdminDirectory;
import Business.Role.AuditorDirectory;
import Business.Role.FactoryAnalystDirectory;
import Business.Role.FactoryInventoryControlDirectory;
import Business.Role.FactoryManagerDirectory;
import Business.Role.FleetMonitorDirectory;
import Business.Role.Role;
import Business.Role.SupplierAnalystDirectory;
import Business.Role.SupplierDirectory;
import Business.Role.SupplierInventoryControlDirectory;
import Business.UserAccount.UserAccount;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ben
 */
public class ManageUserWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CreateOrgWorkAreaJPanel
     */
    JPanel userProcessContainer;
    Business.System.System system;
    UserAccount userAccount;
    Network network;
    Enterprise enterprise;
    Organization org;
    UserAccount selectedUser;

    public ManageUserWorkAreaJPanel(JPanel userProcessContainer, UserAccount userAccount, Business.System.System system, Network network, Enterprise enterprise, Organization org) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userAccount = userAccount;
        this.system = system;
        this.network = network;
        this.enterprise = enterprise;
        this.org = org;
        comboRole.addItem(Role.RoleType.Admin.getValue());
        comboRole.addItem(Role.RoleType.FactoryAnalyst.getValue());
        comboRole.addItem(Role.RoleType.FactoryInventoryControl.getValue());
        comboRole.addItem(Role.RoleType.FactoryManager.getValue());
        comboRole.addItem(Role.RoleType.OilSupplierAnalyst.getValue());
        comboRole.addItem(Role.RoleType.OilSupplierInventoryControl.getValue());
        comboRole.addItem(Role.RoleType.OilSupplier.getValue());
        comboRole.addItem(Role.RoleType.Auditor.getValue());
        comboRole.addItem(Role.RoleType.FleetMonitor.getValue());
        reloadTable();
    }

    public void reloadTable() {
        int rowCount = tblUser.getRowCount();
        DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        AdminDirectory adminUserList = system.getAdminDirectory();
        AuditorDirectory auditorDirectory = system.getAuditorDirectory();
        FleetMonitorDirectory fleetMonitorDirectory = system.getFleetMonitorDirectory();
        FactoryManagerDirectory factoryManagerDirectory = system.getFactoryManagerDirectory();
        FactoryAnalystDirectory factoryAnalystDirectory = system.getFactoryAnalystDirectory();
        FactoryInventoryControlDirectory factoryInventoryControlDirectory = system.getFactoryInventoryControlDirectory();
        SupplierDirectory supplierDirectory = system.getSupplierDirectory();
        SupplierInventoryControlDirectory supplierInventoryControlDirectory = system.getSupplierInventoryControlDirectory();
        SupplierAnalystDirectory supplierAnalystDirectory = system.getSupplierAnalystDirectory();

        for (UserAccount user : system.getUserAccountDirectory().getUserAccountList()) {
            Object row[] = new Object[2];
            row[0] = user;
            String roleName = "";

            if (adminUserList.findAdmin(user.getId()) != null) {
                roleName += adminUserList.findAdmin(user.getId()).getRole();
            } else if (auditorDirectory.findAuditor(user.getId()) != null) {
                roleName += auditorDirectory.findAuditor(user.getId()).getRole();
            } else if (fleetMonitorDirectory.findFeetMonitor(user.getId()) != null) {
                roleName += fleetMonitorDirectory.findFeetMonitor(user.getId()).getRole();
            } else if (factoryManagerDirectory.findFactoryManager(user.getId()) != null) {
                roleName += factoryManagerDirectory.findFactoryManager(user.getId()).getRole();
            } else if (factoryAnalystDirectory.findFactoryAnalyst(user.getId()) != null) {
                roleName += factoryAnalystDirectory.findFactoryAnalyst(user.getId()).getRole();
            } else if (factoryInventoryControlDirectory.findFactoryInventoryControlRole(user.getId()) != null) {
                roleName += factoryInventoryControlDirectory.findFactoryInventoryControlRole(user.getId()).getRole();
            } else if (supplierDirectory.findSupplier(user.getId()) != null) {
                roleName += supplierDirectory.findSupplier(user.getId()).getRole();
            } else if (supplierInventoryControlDirectory.findSupplierInventoryControlRole(user.getId()) != null) {
                roleName += supplierInventoryControlDirectory.findSupplierInventoryControlRole(user.getId()).getRole();
            } else if (supplierAnalystDirectory.findSupplierAnalyst(user.getId()) != null) {
                roleName += supplierAnalystDirectory.findSupplierAnalyst(user.getId()).getRole();
            }
            row[1] = roleName;
            model.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBack1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        btnCreateUser = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPwd = new javax.swing.JPasswordField();
        comboRole = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btnUpdateUser = new javax.swing.JButton();
        btnDeleteUser = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N
        jLabel1.setText("Manage User");

        btnBack1.setText("<< Exit");
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "UserName", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblUserMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblUser);

        btnCreateUser.setText("Create User");
        btnCreateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateUserActionPerformed(evt);
            }
        });

        jLabel2.setText("User Name");

        jLabel3.setText("Password");

        comboRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRoleActionPerformed(evt);
            }
        });

        jLabel4.setText("Role");

        btnUpdateUser.setText("Update User");
        btnUpdateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateUserActionPerformed(evt);
            }
        });

        btnDeleteUser.setText("Delete User");
        btnDeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBack1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPwd)
                                    .addComponent(txtUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnCreateUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUpdateUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDeleteUser)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addContainerGap(296, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateUser)
                    .addComponent(btnUpdateUser)
                    .addComponent(btnDeleteUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addComponent(btnBack1)
                .addGap(21, 21, 21))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addContainerGap(314, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 18, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 18, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 473, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.first(userProcessContainer);
    }//GEN-LAST:event_btnBack1ActionPerformed

    private void btnCreateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateUserActionPerformed
        // TODO add your handling code here:
        if (txtUserName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "User Name cannot be empty", "ERROR", ERROR_MESSAGE);
            return;
        }
        if (!txtUserName.getText().matches("[A-Za-z0-9]+")) {
            JOptionPane.showMessageDialog(this, "User Name is not accept special characters", "ERROR", ERROR_MESSAGE);
            return;
        }
        if (txtPwd.getPassword() == null || txtPwd.getPassword().length < 0) {
            JOptionPane.showMessageDialog(this, "Password cannot be empty", "ERROR", ERROR_MESSAGE);
            return;
        }

        UserAccount newUserAccount = system.getUserAccountDirectory().newUserAccount(txtUserName.getText(), new String(txtPwd.getPassword()));
        
        if(newUserAccount==null){
            JOptionPane.showMessageDialog(this, "Account is exist", "ERROR", ERROR_MESSAGE);
            return;
        }
        
        String selectedRole = comboRole.getSelectedItem().toString();
        if (Role.RoleType.Admin.getValue().equals(selectedRole)) {
            AdminDirectory dir = system.getAdminDirectory();
            dir.newAdminRole(newUserAccount);
            reloadTable();
        } else if (Role.RoleType.FactoryManager.getValue().equals(selectedRole)) {
            FactoryManagerDirectory dir = system.getFactoryManagerDirectory();
            dir.newFactoryManagerRole(newUserAccount);
            reloadTable();
        } else if (Role.RoleType.FactoryAnalyst.getValue().equals(selectedRole)) {
            FactoryAnalystDirectory dir = system.getFactoryAnalystDirectory();
            dir.newFactoryAnalystRole(newUserAccount);
            reloadTable();
        } else if (Role.RoleType.FactoryInventoryControl.getValue().equals(selectedRole)) {
            FactoryInventoryControlDirectory dir = system.getFactoryInventoryControlDirectory();
            dir.newFactoryInventoryControlRole(newUserAccount);
            reloadTable();
        } else if (Role.RoleType.OilSupplier.getValue().equals(selectedRole)) {
            SupplierDirectory dir = system.getSupplierDirectory();
            dir.newSupplierRole(newUserAccount);
            reloadTable();
        } else if (Role.RoleType.OilSupplierAnalyst.getValue().equals(selectedRole)) {
            SupplierAnalystDirectory dir = system.getSupplierAnalystDirectory();
            dir.newSupplierAnalystRole(newUserAccount);
            reloadTable();
        } else if (Role.RoleType.OilSupplierInventoryControl.getValue().equals(selectedRole)) {
            SupplierInventoryControlDirectory dir = system.getSupplierInventoryControlDirectory();
            dir.newSupplierInventoryContorlRole(newUserAccount);
            reloadTable();
        } else if (Role.RoleType.Auditor.getValue().equals(selectedRole)) {
            AuditorDirectory dir = system.getAuditorDirectory();
            dir.newAuditorRole(newUserAccount);
            reloadTable();
        } else if (Role.RoleType.FleetMonitor.getValue().equals(selectedRole)) {
            FleetMonitorDirectory dir = system.getFleetMonitorDirectory();
            dir.newFleetMonitorRole(newUserAccount);
            reloadTable();
        } else {
            JOptionPane.showMessageDialog(this, "System ERROR, Selected Role Not Found", "ERROR", ERROR_MESSAGE);
            return;
        }

//        network.newEnterprise(txtUserName.getText());

    }//GEN-LAST:event_btnCreateUserActionPerformed

    private void comboRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRoleActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_comboRoleActionPerformed

    private void btnUpdateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateUserActionPerformed
        // TODO add your handling code here:
        if (selectedUser == null) {
            JOptionPane.showMessageDialog(this, "Please select a user in the table first.", "ERROR", ERROR_MESSAGE);
            return;
        }
        if (!txtUserName.getText().matches("[A-Za-z0-9]+")) {
            JOptionPane.showMessageDialog(this, "User Name is not accept special characters", "ERROR", ERROR_MESSAGE);
            return;
        }
        if (txtPwd.getPassword() == null || txtPwd.getPassword().length < 0) {
            JOptionPane.showMessageDialog(this, "Password cannot be empty", "ERROR", ERROR_MESSAGE);
            return;
        }

        // Update password (if provided)
        char[] pwd = txtPwd.getPassword();
        system.getUserAccountDirectory().findUserAccount(selectedUser.getUserLoginName()).setUsername(txtUserName.getText());
        system.getUserAccountDirectory().findUserAccount(selectedUser.getUserLoginName()).setPassword(new String(pwd));
        
        changeRoleForUser(selectedUser);

        reloadTable();
        JOptionPane.showMessageDialog(this, "User updated.");
    }//GEN-LAST:event_btnUpdateUserActionPerformed

    private void btnDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteUserActionPerformed
        // TODO add your handling code here:
        if (selectedUser == null) {
            JOptionPane.showMessageDialog(this, "Please select a user first.", "ERROR", ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete user: " + selectedUser.getUserLoginName() + " ?",
                "Confirm delete",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        removeUserFromAllRoleDirectories(selectedUser);

        system.getUserAccountDirectory().removeUser(selectedUser);

        selectedUser = null;
        txtUserName.setText("");
        txtPwd.setText("");

        reloadTable();
    }//GEN-LAST:event_btnDeleteUserActionPerformed

    private void removeUserFromAllRoleDirectories(UserAccount user) {

        system.getAdminDirectory().removeRole(user);
        system.getAuditorDirectory().newAuditorRole(user);
        system.getFleetMonitorDirectory().removeRole(user);
        system.getFactoryManagerDirectory().removeRole(user);
        system.getFactoryAnalystDirectory().removeRole(user);
        system.getFactoryInventoryControlDirectory().removeRole(user);
        system.getSupplierDirectory().removeRole(user);
        system.getSupplierInventoryControlDirectory().removeRole(user);
        system.getSupplierAnalystDirectory().removeRole(user);
    }

    public void changeRoleForUser(UserAccount user) {
        removeUserFromAllRoleDirectories(user);

        String selectedRole = comboRole.getSelectedItem().toString();
        if (Role.RoleType.Admin.getValue().equals(selectedRole)) {
            AdminDirectory dir = system.getAdminDirectory();
            dir.newAdminRole(user);
            reloadTable();
        } else if (Role.RoleType.FactoryManager.getValue().equals(selectedRole)) {
            FactoryManagerDirectory dir = system.getFactoryManagerDirectory();
            dir.newFactoryManagerRole(user);
            reloadTable();
        } else if (Role.RoleType.FactoryAnalyst.getValue().equals(selectedRole)) {
            FactoryAnalystDirectory dir = system.getFactoryAnalystDirectory();
            dir.newFactoryAnalystRole(user);
            reloadTable();
        } else if (Role.RoleType.FactoryInventoryControl.getValue().equals(selectedRole)) {
            FactoryInventoryControlDirectory dir = system.getFactoryInventoryControlDirectory();
            dir.newFactoryInventoryControlRole(user);
            reloadTable();
        } else if (Role.RoleType.OilSupplier.getValue().equals(selectedRole)) {
            SupplierDirectory dir = system.getSupplierDirectory();
            dir.newSupplierRole(user);
            reloadTable();
        } else if (Role.RoleType.OilSupplierAnalyst.getValue().equals(selectedRole)) {
            SupplierAnalystDirectory dir = system.getSupplierAnalystDirectory();
            dir.newSupplierAnalystRole(user);
            reloadTable();
        } else if (Role.RoleType.OilSupplierInventoryControl.getValue().equals(selectedRole)) {
            SupplierInventoryControlDirectory dir = system.getSupplierInventoryControlDirectory();
            dir.newSupplierInventoryContorlRole(user);
            reloadTable();
        } else if (Role.RoleType.Auditor.getValue().equals(selectedRole)) {
            AuditorDirectory dir = system.getAuditorDirectory();
            dir.newAuditorRole(user);
            reloadTable();
        } else if (Role.RoleType.FleetMonitor.getValue().equals(selectedRole)) {
            FleetMonitorDirectory dir = system.getFleetMonitorDirectory();
            dir.newFleetMonitorRole(user);
            reloadTable();
        } else {
            JOptionPane.showMessageDialog(this, "System ERROR, Selected Role Not Found", "ERROR", ERROR_MESSAGE);
            return;
        }
    }

    private void tblUserMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserMousePressed
        // TODO add your handling code here:
        int size = tblUser.getRowCount();
        int selectedrow = tblUser.getSelectionModel().getLeadSelectionIndex();

        if (selectedrow < 0 || selectedrow > size - 1) {
            return;
        }
        selectedUser = (UserAccount) tblUser.getValueAt(selectedrow, 0);
        if (selectedUser == null) {
            return;
        }
    }//GEN-LAST:event_tblUserMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnCreateUser;
    private javax.swing.JButton btnDeleteUser;
    private javax.swing.JButton btnUpdateUser;
    private javax.swing.JComboBox<String> comboRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUser;
    private javax.swing.JPasswordField txtPwd;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
