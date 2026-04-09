/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Operations;

/**
 *
 * @author lindq
 */
import java.util.ArrayList;

public class RequestBoard {

    private ArrayList<BuyRequest> buyRequests = new ArrayList<>();
    private ArrayList<PriceSuggestion> priceSuggestions = new ArrayList<>();
    private ArrayList<SellRequest> sellRequests = new ArrayList<>();
    private ArrayList<OilTransaction> transactions = new ArrayList<>();

    // BuyRequest (written by Factory Manager, read by you)
    public BuyRequest newBuyRequest(String managerId, String factoryName, double volume, double ceiling, String date) {
        BuyRequest br = new BuyRequest(managerId, factoryName, volume, ceiling, date);
        buyRequests.add(br);
        return br;
    }

    public ArrayList<BuyRequest> getBuyRequests() {
        return buyRequests;
    }

    public BuyRequest findBuyRequest(String id) {
        for (BuyRequest br : buyRequests) {
            if (br.getId().equals(id)) {
                return br;
            }
        }
        return null;
    }

    // PriceSuggestion (written by Oil Analyst) 
    public PriceSuggestion newPriceSuggestion(String analystId, String buyReqId, double price, double margin,
            String notes, String date) {
        PriceSuggestion ps = new PriceSuggestion(analystId, buyReqId, price, margin, notes, date);
        priceSuggestions.add(ps);
        return ps;
    }

    public ArrayList<PriceSuggestion> getPriceSuggestions() {
        return priceSuggestions;
    }

    // SellRequest (written by Oil Supplier Agent) ---
    public SellRequest newSellRequest(String agentId, String buyReqId, double price, double volume, String date) {
        SellRequest sr = new SellRequest(agentId, buyReqId, price, volume, date);
        sellRequests.add(sr);
        return sr;
    }

    public ArrayList<SellRequest> getSellRequests() {
        return sellRequests;
    }

    // OilTransaction (written when sell request approved) ---
    public OilTransaction newTransaction(String sellReqId, double volume, double price, String date) {
        OilTransaction t = new OilTransaction(sellReqId, volume, price, date);
        transactions.add(t);
        return t;
    }

    public ArrayList<OilTransaction> getTransactions() {
        return transactions;
    }
}
