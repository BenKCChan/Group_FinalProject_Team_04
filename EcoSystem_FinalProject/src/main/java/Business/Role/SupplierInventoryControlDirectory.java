/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.UserAccount.UserAccount;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class SupplierInventoryControlDirectory {


    ArrayList<SupplierInventoryControlRole> supplierInventoryContorlRoleList;

    public SupplierInventoryControlDirectory() {

     supplierInventoryContorlRoleList = new ArrayList();

    }

    public SupplierInventoryControlRole newSupplierInventoryContorlRole(UserAccount p) {

        SupplierInventoryControlRole sp = new SupplierInventoryControlRole(p);
        supplierInventoryContorlRoleList.add(sp);
        return sp;
    }

    public SupplierInventoryControlRole findSupplierInventoryControlRole(String id) {

        for (SupplierInventoryControlRole sp : supplierInventoryContorlRoleList) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
            return null; //not found after going through the whole list
         }
    
}
