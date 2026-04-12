/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Operations;

/**
 *
 * @author lindq
 */
public class OilTransaction {

    private String id;
    private String linkedSellRequestId;
    private double volume;
    private double pricePerBarrel;
    private double totalRevenue;
    private String completedAt;

    public OilTransaction(String linkedSellRequestId, double volume, double pricePerBarrel, String completedAt) {
        this.id = "TXN-" + java.util.UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        this.linkedSellRequestId = linkedSellRequestId;
        this.volume = volume;
        this.pricePerBarrel = pricePerBarrel;
        this.totalRevenue = volume * pricePerBarrel;
        this.completedAt = completedAt;
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

    public double getPricePerBarrel() {
        return pricePerBarrel;
    }

    public void setPricePerBarrel(double pricePerBarrel) {
        this.pricePerBarrel = pricePerBarrel;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }

}
