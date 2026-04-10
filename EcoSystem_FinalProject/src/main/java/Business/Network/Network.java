/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Network;

import Business.Enterprise.Enterprise;
import Business.Organization;
import java.util.ArrayList;

/**
 *
 * @author ben
 */
public class Network extends Organization {

    ArrayList<Enterprise> participatingEnterprise;

    public Network(String n) {
        super(n);
        participatingEnterprise = new ArrayList();
    }

    public Enterprise newEnterprise(String enterpriseName) {

        Enterprise enterprise = new Enterprise(enterpriseName);
        participatingEnterprise.add(enterprise);
        return enterprise;
    }

    ;
    
    public ArrayList<Enterprise> getEnterpriseDirectory() {
        return participatingEnterprise;
    }
}
