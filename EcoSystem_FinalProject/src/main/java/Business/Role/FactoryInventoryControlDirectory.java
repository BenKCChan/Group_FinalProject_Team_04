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
public class FactoryInventoryControlDirectory {

    ArrayList<FactoryInventoryControlRole> factoryList;

    public FactoryInventoryControlDirectory() {

        factoryList = new ArrayList();

    }

    public FactoryInventoryControlRole newFactoryInventoryControlRole(UserAccount p) {

        FactoryInventoryControlRole sp = new FactoryInventoryControlRole(p);
        factoryList.add(sp);
        return sp;
    }

    public FactoryInventoryControlRole findFactoryInventoryControlRole(String id) {

        for (FactoryInventoryControlRole sp : factoryList) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
        return null; //not found after going through the whole list
    }

    public ArrayList<FactoryInventoryControlRole> removeRole(UserAccount u) {
        FactoryInventoryControlRole searchRole = findFactoryInventoryControlRole(String.valueOf(u.getId()));
        if (searchRole != null) {
            factoryList.remove(searchRole);
        }
        return factoryList;
    }

}