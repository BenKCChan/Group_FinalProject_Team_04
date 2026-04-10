/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Inventory;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author krystinfalcone
 */
public class OilInventory {
    
    private final ArrayList<OilInventoryItem> items = new ArrayList<>();
    private static int itemCounter = 1;

    public OilInventoryItem addPurchase(String supplier, int quantityBarrels, double pricePerBarrel, String linkedRequestId) {
        String itemId = "INV-" + String.format("%04d", itemCounter++);
        OilInventoryItem item = new OilInventoryItem(itemId, supplier, quantityBarrels, pricePerBarrel, linkedRequestId);
        items.add(item);
        return item;
    }

    public int getTotalBarrels() {
        int t = 0;
        for (OilInventoryItem i : items) t += i.getQuantityBarrels();
        return t;
    }

    public double getTotalCost() {
        double t = 0;
        for (OilInventoryItem i : items) t += i.getTotalCost();
        return t;
    }

    public double getAveragePricePerBarrel() {
        int b = getTotalBarrels();
        return b == 0 ? 0 : getTotalCost() / b;
    }

    public ArrayList<OilInventoryItem> getItems() {
        return items;
    }

    public static class OilInventoryItem {
        private final String itemId;
        private final String supplier;
        private final int quantityBarrels;
        private final double pricePerBarrel;
        private final Date purchaseDate;
        private final String linkedRequestId;

        public OilInventoryItem(String itemId, String supplier, int quantityBarrels, double pricePerBarrel, String linkedRequestId) {
            this.itemId = itemId;
            this.supplier = supplier;
            this.quantityBarrels = quantityBarrels;
            this.pricePerBarrel = pricePerBarrel;
            this.purchaseDate = new Date();
            this.linkedRequestId = linkedRequestId;
        }

        public String getItemId() { return itemId; }
        public String getSupplier() { return supplier; }
        public int getQuantityBarrels() { return quantityBarrels; }
        public double getPricePerBarrel() { return pricePerBarrel; }
        public Date getPurchaseDate() { return purchaseDate; }
        public String getLinkedRequestId() { return linkedRequestId; }

        public double getTotalCost() {
            return quantityBarrels * pricePerBarrel;
        }
    }
}
    