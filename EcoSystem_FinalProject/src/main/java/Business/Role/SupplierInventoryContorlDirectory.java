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
public class SupplierInventoryContorlDirectory {


    ArrayList<SupplierInventoryContorlRole> supplierInventoryContorlRoleList;

    public SupplierInventoryContorlDirectory() {

     supplierInventoryContorlRoleList = new ArrayList();

    }

    public SupplierInventoryContorlRole newSupplierInventoryContorlRole(UserAccount p) {

        SupplierInventoryContorlRole sp = new SupplierInventoryContorlRole(p);
        supplierInventoryContorlRoleList.add(sp);
        return sp;
    }

    public SupplierInventoryContorlRole findSupplierInventoryControlRole(String id) {

        for (SupplierInventoryContorlRole sp : supplierInventoryContorlRoleList) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
            return null; //not found after going through the whole list
         }
    
}
