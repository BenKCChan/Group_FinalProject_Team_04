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
public class SupplierAnalystDirectory {


    ArrayList<SupplierAnalystRole> supplierAnalystList;

    public SupplierAnalystDirectory() {

     supplierAnalystList = new ArrayList();

    }

    public SupplierAnalystRole newSupplierAnalystRole(UserAccount p) {

        SupplierAnalystRole sp = new SupplierAnalystRole(p);
        supplierAnalystList.add(sp);
        return sp;
    }

    public SupplierAnalystRole findSupplierAnalyst(String id) {

        for (SupplierAnalystRole sp : supplierAnalystList) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
            return null; //not found after going through the whole list
         }
    
}
