/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Operations;

/**
 *
 * @author lindq
 */
public class BuyRequest {

    private String id;
    private String factoryManagerId;
    private String factoryName;
    private double volume;
    private double priceCeiling;
    private String status; // "OPEN", "APPROVED", "REJECTED"
    private String createdAt;

    public BuyRequest(String factoryManagerId, String factoryName,
            double volume, double priceCeiling, String createdAt) {
        this.id = "BR-" + java.util.UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        this.factoryManagerId = factoryManagerId;
        this.factoryName = factoryName;
        this.volume = volume;
        this.priceCeiling = priceCeiling;
        this.status = "OPEN";
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFactoryManagerId() {
        return factoryManagerId;
    }

    public void setFactoryManagerId(String factoryManagerId) {
        this.factoryManagerId = factoryManagerId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPriceCeiling() {
        return priceCeiling;
    }

    public void setPriceCeiling(double priceCeiling) {
        this.priceCeiling = priceCeiling;
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
    
    @Override
    public String toString() { 
        return id + " — " + factoryName; 
    }
    
}
