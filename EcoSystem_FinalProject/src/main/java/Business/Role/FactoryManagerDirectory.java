/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Role;

import Business.Person.PersonAccount;
import java.util.ArrayList;

/**
 *
 * @author krystinfalcone
 */
public class FactoryManagerDirectory {
    
    ArrayList<FactoryManagerRole> managerList;
    
    public FactoryManagerDirectory() {
        managerList = new ArrayList();
    }
    public FactoryManagerRole newFactoryManagerRole(PersonAccount p) {
        FactoryManagerRole fm = new FactoryManagerRole(p);
        managerList.add(fm);
        return fm;
    }
    public FactoryManagerRole findManager(String id) {
        for (FactoryManagerRole fm : managerList) {
            if (fm.isMatch(id)) return fm;
        }
        return null;
    }
    public ArrayList<FactoryManagerRole> getManagerList() {
        return managerList;
    }
}
