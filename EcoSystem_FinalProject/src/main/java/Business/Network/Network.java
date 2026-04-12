/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Network;

import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 *
 * @author ben
 */
public class Network extends Organization {

    ArrayList<Enterprise> participatingEnterprise;
    String name;
    public Network (String n){
        super(n);
        name = n;
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
    
    public Boolean removeEnterprise(Enterprise enterprise){
        if(!enterprise.getParticipatingunits().isEmpty()){
            return false;
        }
        participatingEnterprise.remove(enterprise);
        return true;
    }
    @Override
    public String toString(){
        return name;
    }
}
