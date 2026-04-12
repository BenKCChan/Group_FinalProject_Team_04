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
    String name;
    public Enterprise(String name) {
        super(name);
        this.name = name;
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
    
    public Boolean removeParticipantingunits(Organization organization){
        if(!organization.getUserAccountDirectory().getUserAccountList().isEmpty()){
            return false;
        }
        participatingunits.remove(organization);
        return true;
    }
    
    public Organization updateParticipantingunits(Organization oldOrg, Organization newOrg){
        int idx = participatingunits.indexOf(oldOrg);
        participatingunits.set(idx, newOrg);
        return participatingunits.get(idx);
    }
    
    @Override
    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }
}
