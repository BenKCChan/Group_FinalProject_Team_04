/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Enterprise;

import Business.Organization;
import java.util.ArrayList;

/**
 *
 * @author ben
 */
public class Enterprise extends Organization {
    ArrayList<Organization> participatingunits;

    public Enterprise(String name) {
        super(name);
        participatingunits = new ArrayList();
    }
    
    public Organization newOrganization(String orgName){
        Organization organization = new Organization(orgName);
        participatingunits.add(organization);
        return organization;
    }
    
    public ArrayList<Organization> getParticipatingunits(){
        return participatingunits;
    }
}
