/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Person.PersonAccount;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class AdminDirectory {

    ArrayList<AdminRole> adminlist;

    public AdminDirectory() {

        adminlist = new ArrayList();

    }

    public AdminRole newAdminRole(PersonAccount p) {

        AdminRole sp = new AdminRole(p);
        adminlist.add(sp);
        return sp;
    }

    public AdminRole findAdmin(String id) {

        for (AdminRole admin : adminlist) {

            if (admin.isMatch(id)) {
                return admin;
            }
        }
            return null; //not found after going through the whole list
         }
    
}
