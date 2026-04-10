/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.OilTrade;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author bacha
 */
public class OilTradeRequestList {
    
    private final ArrayList<OilTradeRequest> requests = new ArrayList<>();
    private static AtomicInteger counter = new AtomicInteger(1);
    
    public OilTradeRequest addRequest(String fromOrganization, String fromEnterprise, String toOrganization, String toEnterprise, OilTradeRequest.RequestType type, double pricePerBarrel, int quantityBarrels, String requestedBy) {
        String id = "REQ-" + String.format("%04d", counter.getAndIncrement());
        OilTradeRequest req = new OilTradeRequest(id, fromOrganization, fromEnterprise, toOrganization, toEnterprise, type, pricePerBarrel, quantityBarrels, requestedBy);
        requests.add(req);
        return req;
    }
    public ArrayList<OilTradeRequest> getAllRequests() {
        return requests;
    }
    public ArrayList<OilTradeRequest> getRequestsForEnterprise(String enterpriseName) {
        ArrayList<OilTradeRequest> result = new ArrayList<>();
        for (OilTradeRequest r : requests) {
            if (r.getFromEnterprise().equals(enterpriseName) || r.getToEnterprise().equals(enterpriseName)) {
                result.add(r);
            }
        }
        return result;
    }
    public ArrayList<OilTradeRequest> getRequestsByUser(String username) {
        ArrayList<OilTradeRequest> result = new ArrayList<>();
        for (OilTradeRequest r : requests) {
            if (r.getRequestedBy().equals(username)) result.add(r);
        }
        return result;
    }
    public OilTradeRequest findRequest(String requestId) { 
        for (OilTradeRequest r : requests) {
            if (r.getRequestId().equals(requestId)) return r;
        }
        return null;
    }
}
