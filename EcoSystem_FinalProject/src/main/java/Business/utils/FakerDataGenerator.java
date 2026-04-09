/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.utils;

/**
 *
 * @author lindq
 */

import Business.Operations.BuyRequest;
import Business.Operations.OilTransaction;
import Business.Operations.PriceSuggestion;
import Business.Operations.RequestBoard;
import Business.Operations.SellRequest;
import Business.Operations.ShipmentRequest;
import Business.UserAccount.UserAccount;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class FakerDataGenerator {

    private static final Faker faker = new Faker();
    private static final Random random = new Random();
    private static final double BREAK_EVEN = 61.20;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final String[] FACTORIES = {
        "India Factory", "Japan Factory"
    };

    private static final String ORIGIN = "UAE Oil Supplier";

    // Generates a date string N days before today
    private static String daysAgo(int days) {
        return LocalDate.now().minusDays(days).format(FMT);
    }

    // Random price between min and max, rounded to 2dp
    private static double randomPrice(double min, double max) {
        double raw = min + (max - min) * random.nextDouble();
        return Math.round(raw * 100.0) / 100.0;
    }

    // Random volume between min and max, rounded to nearest 100
    private static double randomVolume(double min, double max) {
        double raw = min + (max - min) * random.nextDouble();
        return Math.round(raw / 100.0) * 100.0;
    }

    private static double computeMargin(double price) {
        return Math.round(((price - BREAK_EVEN) / BREAK_EVEN) * 1000.0) / 10.0;
    }

    public static void populate(RequestBoard rb,
                                 UserAccount analystAccount,
                                 UserAccount agentAccount,
                                 int numberOfRecords) {

        for (int i = 0; i < numberOfRecords; i++) {

            // Space records across the last 90 days, oldest first
            int daysAgoValue = (numberOfRecords - i) * (90 / numberOfRecords);
            String date = daysAgo(daysAgoValue);
            String deliveryDate = daysAgo(daysAgoValue - 3);

            // Pick a random factory
            String factory = FACTORIES[random.nextInt(FACTORIES.length)];

            // Random volume and price ceiling
            double volume    = randomVolume(3000, 10000);
            double ceiling   = randomPrice(76.00, 83.00);
            double sellPrice = randomPrice(BREAK_EVEN + 10, ceiling);
            double margin    = computeMargin(sellPrice);

            // Faker-generated manager ID
            String managerId = "MGR-" + faker.number().digits(4);

            // 1. Buy request — mark APPROVED for history records
            BuyRequest br = rb.newBuyRequest(managerId, factory, volume, ceiling, date);
            br.setStatus("APPROVED");

            // 2. Price suggestion from analyst
            String analystNote = faker.lorem().sentence(8);
            PriceSuggestion ps = rb.newPriceSuggestion(
                analystAccount.getId(),
                br.getId(),
                sellPrice,
                margin,
                analystNote,
                date
            );

            // Vary suggestion outcomes — 75% accepted, 25% rejected
            if (random.nextInt(4) == 0) {
                ps.setStatus("REJECTED");
                ps.setSupplierNote(faker.lorem().sentence(5));
            } else {
                ps.setStatus("ACCEPTED");
                ps.setSupplierNote(faker.lorem().sentence(4));
            }

            // Only create sell/transaction/shipment if suggestion was accepted
            if ("ACCEPTED".equals(ps.getStatus())) {

                // 3. Sell request
                SellRequest sr = rb.newSellRequest(
                    agentAccount.getId(),
                    br.getId(),
                    sellPrice,
                    volume,
                    date
                );
                sr.setStatus("APPROVED");

                // 4. Oil transaction
                rb.newTransaction(sr.getId(), volume, sellPrice, date);

                // 5. Shipment request — vary status by age
                ShipmentRequest sh = rb.newShipmentRequest(
                    sr.getId(), volume, ORIGIN, factory, date);

                if (daysAgoValue > 7) {
                    sh.setStatus("DELIVERED");
                    sh.setDeliveredDate(deliveryDate);
                } else if (daysAgoValue > 3) {
                    sh.setStatus("IN_TRANSIT");
                } else {
                    sh.setStatus("SCHEDULED");
                }
            }
        }

        // Add a few open buy requests for the analyst to work on live
        for (int i = 0; i < 5; i++) {
            String factory  = FACTORIES[random.nextInt(FACTORIES.length)];
            double volume   = randomVolume(3000, 10000);
            double ceiling  = randomPrice(76.00, 83.00);
            String managerId = "MGR-" + faker.number().digits(4);
            String date = daysAgo(random.nextInt(5));
            rb.newBuyRequest(managerId, factory, volume, ceiling, date);
            // status stays "OPEN" — these are the live requests the analyst sees
        }
    }
}