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


    ArrayList<SupplierRole> studentlist;

    public SupplierDirectory() {

     studentlist = new ArrayList();

    }

    public SupplierRole newStudentProfile(UserAccount p) {

        SupplierRole sp = new SupplierRole(p);
        studentlist.add(sp);
        return sp;
    }

    public SupplierRole findStudent(String id) {

        for (SupplierRole sp : studentlist) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
            return null; //not found after going through the whole list
         }
    
}
