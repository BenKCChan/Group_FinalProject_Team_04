/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.System;

import java.util.ArrayList;
import Business.Network.Network;
import Business.Organization;
import Business.Organization;
import Business.Operations.RequestBoard;

/**
 *
 * @author ben
 */
public class System extends Organization {
    
    private RequestBoard requestBoard = new RequestBoard();
    ArrayList<Network> participantnetworks;
    String name;
    public System(String name){
        super();
        this.name = name;
        participantnetworks = new ArrayList();
    }
    public Network newNetwork(String networkName){
            Network network = new Network(networkName);
            participantnetworks.add(network);
            return network;
    };
    public ArrayList<Network> getNetworkList(){
        return participantnetworks;
    }

    public String getName() {
        return name;
    }

    public RequestBoard getRequestBoard() {
        return requestBoard;
    }
    
    
}
