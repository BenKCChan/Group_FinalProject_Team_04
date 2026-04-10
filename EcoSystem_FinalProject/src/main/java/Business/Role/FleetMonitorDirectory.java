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
public class FleetMonitorDirectory {


    ArrayList<FleetMonitorRole> fleetMonitorList;

    public FleetMonitorDirectory() {

     fleetMonitorList = new ArrayList();

    }

    public FleetMonitorRole newFleetMonitorRole(UserAccount p) {

        FleetMonitorRole sp = new FleetMonitorRole(p);
        fleetMonitorList.add(sp);
        return sp;
    }

    public FleetMonitorRole findFeetMonitor(String id) {

        for (FleetMonitorRole sp : fleetMonitorList) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
            return null; //not found after going through the whole list
         }
    
}
