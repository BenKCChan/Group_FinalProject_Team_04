/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Operations;

/**
 *
 * @author lindq
 */
public class ShipmentRequest {
    private String id;
    private String linkedSellRequestId;
    private double volume;
    private String origin;
    private String destination;
    private String status; // "SCHEDULED", "IN_TRANSIT", "DELIVERED"
    private String scheduledDate;
    private String deliveredDate;

    public ShipmentRequest(String linkedSellRequestId, double volume, String origin, String destination, String scheduledDate) {
        
        this.id = "SHP-" + java.util.UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        this.linkedSellRequestId = linkedSellRequestId;
        this.volume = volume;
        this.origin = origin;
        this.destination = destination;
        this.status = "SCHEDULED";
        this.scheduledDate = scheduledDate;
        this.deliveredDate = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLinkedSellRequestId() {
        return linkedSellRequestId;
    }

    public void setLinkedSellRequestId(String linkedSellRequestId) {
        this.linkedSellRequestId = linkedSellRequestId;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(String deliveredDate) {
        this.deliveredDate = deliveredDate;
    }
    
    
}
