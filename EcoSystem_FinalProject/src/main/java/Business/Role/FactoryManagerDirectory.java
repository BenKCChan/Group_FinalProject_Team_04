/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Business.Role;

import Business.UserAccount.UserAccount;

import java.util.ArrayList;
/**
 *
 * @author krystinfalcone
 */
public class FactoryManagerDirectory {
    
    ArrayList<FactoryManagerRole> factoryList;
    
    public FactoryManagerDirectory() {
        factoryList = new ArrayList();
    }
    public FactoryManagerRole newFactoryManagerRole(UserAccount p) {
        FactoryManagerRole sp = new FactoryManagerRole(p);
        factoryList.add(sp);
        return sp;
        }
    
    public FactoryManagerRole findFactoryManager(String id) {
        for (FactoryManagerRole sp : factoryList) {
            if (sp.isMatch(id)){ 
                return sp;
        }
    }
        return null;
    }

    public ArrayList<FactoryManagerRole> removeRole(UserAccount u) {
        FactoryManagerRole searchRole = findFactoryManager(String.valueOf(u.getId()));
        if (searchRole != null) {
            factoryList.remove(searchRole);
        }
        return factoryList;
    }
}
