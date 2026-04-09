/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Operations;

/**
 *
 * @author lindq
 */
public class SellRequest {

    private String id;
    private String supplierAgentPersonId;
    private String linkedBuyRequestId;
    private double sellPrice;
    private double volume;
    private String status; // "PENDING", "APPROVED", "REJECTED"
    private String createdAt;

    public SellRequest(String supplierAgentPersonId, String linkedBuyRequestId,
            double sellPrice, double volume, String createdAt) {
        this.id = "SR-" + java.util.UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        this.supplierAgentPersonId = supplierAgentPersonId;
        this.linkedBuyRequestId = linkedBuyRequestId;
        this.sellPrice = sellPrice;
        this.volume = volume;
        this.status = "PENDING";
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplierAgentPersonId() {
        return supplierAgentPersonId;
    }

    public void setSupplierAgentPersonId(String supplierAgentPersonId) {
        this.supplierAgentPersonId = supplierAgentPersonId;
    }

    public String getLinkedBuyRequestId() {
        return linkedBuyRequestId;
    }

    public void setLinkedBuyRequestId(String linkedBuyRequestId) {
        this.linkedBuyRequestId = linkedBuyRequestId;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    
}
