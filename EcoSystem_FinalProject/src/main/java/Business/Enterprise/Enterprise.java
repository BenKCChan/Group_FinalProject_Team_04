/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Enterprise;

import Business.Organization.Organization;
import java.util.ArrayList;

/**
 *
 * @author ben
 */
public class Enterprise extends Organization {

    private ArrayList<Organization> participatingUnits;

    public Enterprise(String name) {
        super(name);
        participatingUnits = new ArrayList<>();
    }

    public Organization newOrganization(String orgName) {
        Organization organization = new Organization(orgName);
        participatingUnits.add(organization);
        return organization;
    }

    public ArrayList<Organization> getParticipatingunits() {
        return participatingUnits;
    }

    public boolean removeParticipatingUnit(Organization organization) {
        if (!participatingUnits.contains(organization)) {
            return false;
        }
        if (!organization.getUserAccountDirectory().getUserAccountList().isEmpty()) {
            return false;
        }
        participatingUnits.remove(organization);
        return true;
    }

    public Organization updateParticipatingUnit(Organization oldOrg, Organization newOrg) {
        int idx = participatingUnits.indexOf(oldOrg);
        if (idx < 0) {
            return null;
        }
        participatingUnits.set(idx, newOrg);
        return participatingUnits.get(idx);
    }

    public String getEnterpriseName() {
        return getName();
    }

    @Override
    public String toString() {
        return getName();
    }
}