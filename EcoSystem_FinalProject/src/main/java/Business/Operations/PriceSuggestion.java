/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Operations;

/**
 *
 * @author lindq
 */
public class PriceSuggestion {

    private String id;
    private String analystPersonId;
    private String linkedBuyRequestId;
    private double suggestedPrice;
    private double marginPct;
    private String notes;
    private String status; // "PENDING", "ACCEPTED", "REJECTED"
    private String supplierNote;
    private String submittedAt;

    public PriceSuggestion(String analystPersonId, String linkedBuyRequestId,
        double suggestedPrice, double marginPct, String notes, String submittedAt) {
        this.id = "SUG-" + java.util.UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        this.analystPersonId = analystPersonId;
        this.linkedBuyRequestId = linkedBuyRequestId;
        this.suggestedPrice = suggestedPrice;
        this.marginPct = marginPct;
        this.notes = notes;
        this.status = "PENDING";
        this.supplierNote = "";
        this.submittedAt = submittedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnalystPersonId() {
        return analystPersonId;
    }

    public void setAnalystPersonId(String analystPersonId) {
        this.analystPersonId = analystPersonId;
    }

    public String getLinkedBuyRequestId() {
        return linkedBuyRequestId;
    }

    public void setLinkedBuyRequestId(String linkedBuyRequestId) {
        this.linkedBuyRequestId = linkedBuyRequestId;
    }

    public double getSuggestedPrice() {
        return suggestedPrice;
    }

    public void setSuggestedPrice(double suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }

    public double getMarginPct() {
        return marginPct;
    }

    public void setMarginPct(double marginPct) {
        this.marginPct = marginPct;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupplierNote() {
        return supplierNote;
    }

    public void setSupplierNote(String supplierNote) {
        this.supplierNote = supplierNote;
    }

    public String getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(String submittedAt) {
        this.submittedAt = submittedAt;
    }
    
    
    }
