/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Network;

import Business.Enterprise.Enterprise;
import Business.OilTrade.OilTradeRequestList;
import Business.Organization.Organization;
import java.util.ArrayList;


/**
 *
 * @author ben
 */
public class Network extends Organization {

    ArrayList<Enterprise> participatingEnterprise;
    OilTradeRequestList tradeRequestList;
    String name;
    
    public Network (String n){
        super(n);
        name = n;
        participatingEnterprise = new ArrayList();
        tradeRequestList = new OilTradeRequestList();
    }

    public Enterprise newEnterprise(String enterpriseName){
        Enterprise enterprise = new Enterprise(enterpriseName);
        participatingEnterprise.add(enterprise);
        return enterprise;
    };

    public ArrayList<Enterprise> getEnterpriseDirectory(){
        return participatingEnterprise;
    }
    public OilTradeRequestList getTradeRequestList() {
        return tradeRequestList;
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
