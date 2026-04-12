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

    private ArrayList<SupplierInventoryControlRole> supplierInventoryControlRoleList;

    public SupplierInventoryControlDirectory() {
        supplierInventoryControlRoleList = new ArrayList<>();
    }

    public SupplierInventoryControlRole newSupplierInventoryControlRole(UserAccount p) {
        SupplierInventoryControlRole sp = new SupplierInventoryControlRole(p);
        supplierInventoryControlRoleList.add(sp);
        return sp;
    }

    public SupplierInventoryControlRole findSupplierInventoryControlRole(String id) {
        for (SupplierInventoryControlRole sp : supplierInventoryControlRoleList) {
            if (sp.isMatch(id)) {
                return sp;
            }
        }
        return null;
    }

    public ArrayList<SupplierInventoryControlRole> removeRole(UserAccount u) {
        SupplierInventoryControlRole searchRole = findSupplierInventoryControlRole(u.getId());
        if (searchRole != null) {
            supplierInventoryControlRoleList.remove(searchRole);
        }
        return supplierInventoryControlRoleList;
    }

    public ArrayList<SupplierInventoryControlRole> getSupplierInventoryControlRoleList() {
        return supplierInventoryControlRoleList;
    }
}