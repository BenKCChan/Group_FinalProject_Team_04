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
public class FactoryAnalystDirectory {


    ArrayList<FactoryAnalystRole> factoryList;

    public FactoryAnalystDirectory() {

     factoryList = new ArrayList();

    }

    public FactoryAnalystRole newFactoryManagerRole(UserAccount p) {

        FactoryAnalystRole sp = new FactoryAnalystRole(p);
        factoryList.add(sp);
        return sp;
    }

    public FactoryAnalystRole findFactoryAnalyst(String id) {

        for (FactoryAnalystRole sp : factoryList) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
            return null; //not found after going through the whole list
         }
    
}
