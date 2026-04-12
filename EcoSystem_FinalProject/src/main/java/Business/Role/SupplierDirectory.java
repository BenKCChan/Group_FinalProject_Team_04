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
public class SupplierDirectory {

    ArrayList<SupplierRole> supplierList;

    public SupplierDirectory() {

        supplierList = new ArrayList();

    }

    public SupplierRole newSupplierRole(UserAccount p) {

        SupplierRole sp = new SupplierRole(p);
        supplierList.add(sp);
        return sp;
    }

    public SupplierRole findSupplier(String id) {

        for (SupplierRole sp : supplierList) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
        return null; //not found after going through the whole list
    }

<<<<<<< HEAD
=======
    public ArrayList<SupplierRole> removeRole(UserAccount u) {
        SupplierRole searchRole = findSupplier(String.valueOf(u.getId()));
        if (searchRole != null) {
            supplierList.remove(searchRole);
        }
        return supplierList;
    }
>>>>>>> main
}
