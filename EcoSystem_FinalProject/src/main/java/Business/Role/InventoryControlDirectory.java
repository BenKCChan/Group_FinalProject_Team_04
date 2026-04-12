/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Role;

import java.util.ArrayList;
import Business.System.System;
import Business.UserAccount.UserAccount;

/**
 *
 * @author krystinfalcone
 */
public class InventoryControlDirectory {
    
    ArrayList<InventoryControlRole> controlList;
    
    public InventoryControlDirectory() {
        controlList = new ArrayList();
    }
    public InventoryControlRole newInventoryControlRole(UserAccount p) {
        InventoryControlRole ic = new InventoryControlRole(p);
        controlList.add(ic);
        return ic;
    }
    public InventoryControlRole findController(String id) {
        for (InventoryControlRole ic : controlList) {
            if (ic.isMatch(id)) return ic;
        }
        return null;
    }
    public ArrayList<InventoryControlRole> getControlList() {
        return controlList;
    }
}
