/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Role;

import Business.UserAccount.UserAccount;
import java.util.ArrayList;

/**
 *
 * @author krystin falcone
 */
public class AnalystDirectory {
    ArrayList<AnalystRole> analystList;
    
    public AnalystDirectory() {
        analystList = new ArrayList();
    }
    public AnalystRole newAnalystRole(UserAccount p) {
        AnalystRole ar = new AnalystRole(p);
        analystList.add(ar);
        return ar;
    }
    public AnalystRole findAnalyst(String id) {
        for (AnalystRole ar : analystList) {
            if (ar.isMatch(id))
                return ar;
        }
        return null;
    }
    public ArrayList<AnalystRole> getAnalystList() {
        return analystList;
    }
}
