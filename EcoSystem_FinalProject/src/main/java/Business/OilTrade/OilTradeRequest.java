/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.OilTrade;

import java.util.Date;

/**
 *
 * @author krystinfalcone
 */
public class OilTradeRequest {
    
    public enum RequestType {
        BUY("Buy Oil"), SELL("Sell Oil");
        
        private final String label;
        RequestType(String label) { this.label = label; }
        public String getLabel() { return label; }
        @Override public String toString() { return label; }
    }
    
    public enum RequestStatus {
        PENDING("Pending"), APPROVED("Approved"), REJECTED("Rejected");
        
        private final String label;
        RequestStatus(String label) { this.label = label; }
        @Override public String toString() { return label; }
    }
    
    private final String requestId;
    private final String fromOrganization;
    private final String fromEnterprise;
    private final String toOrganization;
    private final String toEnterprise;
    private final RequestType requestType;
    private final double pricePerBarrel;
    private final int quantityBarrels;
    private final String requestedBy;
    private final Date requestDate;
    private RequestStatus status;
    private String reviewNotes;
    
    public OilTradeRequest(String requestId, String fromOrganization,  String fromEnterprise, String toOrganization, String toEnterprise, RequestType requestType, double pricePerBarrel, int quantityBarrels, String requestedBy) {
        
        this.requestId = requestId;
        this.fromOrganization = fromOrganization;
        this.fromEnterprise = fromEnterprise; 
        this.toOrganization = toOrganization;
        this.toEnterprise = toEnterprise; 
        this.requestType = requestType;
        this.pricePerBarrel = pricePerBarrel;
        this.quantityBarrels = quantityBarrels;
        this.requestedBy = requestedBy;
        this.requestDate = new Date();
        this.status = RequestStatus.PENDING;
        this.reviewNotes = "";
        
    }
    public String getRequestId() {
        return requestId;
    }
    public String getFromOrganization() {
        return fromOrganization; 
    }
    public String getFromEnterprise() {
        return fromEnterprise;
    }
    public String getToOrganization() {
        return toOrganization;
    }
    public String getToEnterprise() {
        return toEnterprise;
    }
    public RequestType getRequestType() {
        return requestType;
    }
    public double getPricePerBarrel() {
        return pricePerBarrel;
    }
    public int getQuantityBarrels() {
        return quantityBarrels;
    }
    public String getRequestedBy() {
        return requestedBy;
    }
    public Date getRequestDate() {
        return requestDate;
    }
    public RequestStatus getStatus() {
        return status;
    }
    public String getReviewNotes() {
        return reviewNotes;
    }
    public void setStatus(RequestStatus status) {
        this.status = status; 
    }
    public void setReviewNotes(String reviewNotes) {
        this.reviewNotes = reviewNotes;
    }
    public double getTotalCost() { 
        return pricePerBarrel * quantityBarrels;
    }
    @Override
    public String toString() { 
        return requestId + " | " + requestType + " | $" + String.format("%.2f", pricePerBarrel) + "/bbl | " + quantityBarrels + " bbls | " + status;
    }
}
