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
public class AuditorDirectory {

    ArrayList<AuditorRole> auditorList;

    public AuditorDirectory() {

        auditorList = new ArrayList();

    }

    public AuditorRole newAuditorRole(UserAccount p) {

        AuditorRole sp = new AuditorRole(p);
        auditorList.add(sp);
        return sp;
    }

    public AuditorRole findAuditor(String id) {

        for (AuditorRole sp : auditorList) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
        return null; //not found after going through the whole list
    }

    public ArrayList<AuditorRole> removeRole(UserAccount u) {
        AuditorRole searchRole = findAuditor(String.valueOf(u.getId()));
        if (searchRole != null) {
            auditorList.remove(searchRole);
        }
        return auditorList;
    }
}
