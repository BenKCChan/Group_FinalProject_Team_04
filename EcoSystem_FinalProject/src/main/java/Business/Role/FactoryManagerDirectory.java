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
public class FactoryManagerDirectory {

    private ArrayList<FactoryManagerRole> factoryList;

    public FactoryManagerDirectory() {
        factoryList = new ArrayList<>();
    }

    public FactoryManagerRole newFactoryManagerRole(UserAccount p) {
        FactoryManagerRole sp = new FactoryManagerRole(p);
        factoryList.add(sp);
        return sp;
    }

    public FactoryManagerRole findFactoryManager(String id) {
        for (FactoryManagerRole sp : factoryList) {
            if (sp.isMatch(id)) {
                return sp;
            }
        }
        return null;
    }

    public ArrayList<FactoryManagerRole> removeRole(UserAccount u) {
        FactoryManagerRole searchRole = findFactoryManager(u.getId());
        if (searchRole != null) {
            factoryList.remove(searchRole);
        }
        return factoryList;
    }

    public ArrayList<FactoryManagerRole> getFactoryList() {
        return factoryList;
    }
}