/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.UAEOilSupplier;

/**
 *
 * @author lindq
 */
import Business.Enterprise.Enterprise;
import Business.Operations.OilTransaction;
import Business.Operations.RequestBoard;
import Business.Operations.SellRequest;
import Business.Organization;
import Business.System.System;
import Business.UserAccount.UserAccount;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class OilInventoryWorkAreaJPanel extends JPanel {

    private JPanel userProcessContainer;
    private UserAccount userAccount;
    private Organization organization;
    private Enterprise enterprise;
    private System system;
    private RequestBoard rb;

    // Metric labels
    private JLabel lblTotalRevenue;
    private JLabel lblTotalVolume;
    private JLabel lblTxnCount;
    private JLabel lblAvgPrice;

    // Tables
    private JTable txnTable;
    private DefaultTableModel txnModel;

    private JTable breakdownTable;
    private DefaultTableModel breakdownModel;

    public OilInventoryWorkAreaJPanel(JPanel userProcessContainer, UserAccount userAccount,
            Organization organization, Enterprise enterprise, System system) {
        this.userProcessContainer = userProcessContainer;
        this.userAccount = userAccount;
        this.organization = organization;
        this.enterprise = enterprise;
        this.system = system;
        this.rb = system.getRequestBoard();

        setLayout(new BorderLayout(0, 0));
        buildUI();
        refreshAll();
    }

    private void buildUI() {

        // ── Header ────────────────────────────────────────────────────────────
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 10));
        header.setBackground(new Color(250, 238, 218));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(133, 79, 11)));

        JLabel lblRole = new JLabel("Oil Inventory Control  |  Oil Inventory Dept  |  " + enterprise.getName());
        lblRole.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblRole.setForeground(new Color(99, 56, 6));
        header.add(lblRole);

        JLabel lblUser = new JLabel("Logged in: " + userAccount.getUserLoginName());
        lblUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblUser.setForeground(new Color(133, 79, 11));
        header.add(lblUser);

        JButton btnRefresh = new JButton("Refresh Report");
        btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnRefresh.addActionListener(e -> refreshAll());
        header.add(btnRefresh);

        // ── Metric cards ──────────────────────────────────────────────────────
        JPanel metricsPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        metricsPanel.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        metricsPanel.setBackground(new Color(245, 245, 240));

        lblTotalRevenue = new JLabel("$0");
        lblTotalVolume = new JLabel("0 bbl");
        lblTxnCount = new JLabel("0");
        lblAvgPrice = new JLabel("$0.00");

        metricsPanel.add(buildMetricCard("Total revenue earned", lblTotalRevenue));
        metricsPanel.add(buildMetricCard("Total volume sold", lblTotalVolume));
        metricsPanel.add(buildMetricCard("Completed transactions", lblTxnCount));
        metricsPanel.add(buildMetricCard("Average price per barrel", lblAvgPrice));

        // Stack header + metrics in north
        JPanel northStack = new JPanel();
        northStack.setLayout(new BoxLayout(northStack, BoxLayout.Y_AXIS));
        northStack.add(header);
        northStack.add(metricsPanel);
        add(northStack, BorderLayout.NORTH);

        // ── Center: transaction table ─────────────────────────────────────────
        String[] txnCols = {"Transaction ID", "Date", "Linked Sell Request",
            "Volume (bbl)", "Price ($/bbl)", "Total Revenue"};
        txnModel = new DefaultTableModel(txnCols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        txnTable = new JTable(txnModel);
        txnTable.setRowHeight(24);
        txnTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txnTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
        txnTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Right-align numeric columns (3, 4, 5)
        DefaultTableCellRenderer rightAlign = new DefaultTableCellRenderer();
        rightAlign.setHorizontalAlignment(SwingConstants.RIGHT);
        for (int i = 3; i <= 5; i++) {
            txnTable.getColumnModel().getColumn(i).setCellRenderer(rightAlign);
        }

        // Alternating row colors
        txnTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0
                            ? Color.WHITE
                            : new Color(252, 251, 248));
                }
                if (col >= 3) {
                    ((JLabel) c).setHorizontalAlignment(SwingConstants.RIGHT);
                }
                return c;
            }
        });

        JPanel centerPane = new JPanel(new BorderLayout());
        centerPane.add(makeSectionLabel("Transaction Log — Oil Sales"), BorderLayout.NORTH);
        centerPane.add(new JScrollPane(txnTable), BorderLayout.CENTER);

        // ── South: per-factory breakdown table ───────────────────────────────
        String[] breakCols = {"Factory (Destination)", "Transactions", "Volume Sold (bbl)",
            "Total Revenue", "Avg Price ($/bbl)"};
        breakdownModel = new DefaultTableModel(breakCols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        breakdownTable = new JTable(breakdownModel);
        breakdownTable.setRowHeight(24);
        breakdownTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
        breakdownTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));

        // Right-align columns 1-4
        DefaultTableCellRenderer rightAlign2 = new DefaultTableCellRenderer();
        rightAlign2.setHorizontalAlignment(SwingConstants.RIGHT);
        for (int i = 1; i <= 4; i++) {
            breakdownTable.getColumnModel().getColumn(i).setCellRenderer(rightAlign2);
        }

        JScrollPane breakScroll = new JScrollPane(breakdownTable);
        breakScroll.setPreferredSize(new Dimension(0, 160));

        JPanel southPane = new JPanel(new BorderLayout());
        southPane.add(makeSectionLabel("Revenue Breakdown by Factory"), BorderLayout.NORTH);
        southPane.add(breakScroll, BorderLayout.CENTER);
        southPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,
                new Color(200, 196, 185)));

        // Split center and south
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(340);
        splitPane.setResizeWeight(0.65);
        splitPane.setTopComponent(centerPane);
        splitPane.setBottomComponent(southPane);

        add(splitPane, BorderLayout.CENTER);
    }

    // ── Refresh ───────────────────────────────────────────────────────────────
    private void refreshAll() {
        refreshTransactionTable();
        refreshBreakdownTable();
        refreshMetrics();
    }

    private void refreshTransactionTable() {
        txnModel.setRowCount(0);
        ArrayList<OilTransaction> list = rb.getTransactions();

        // Newest first
        for (int i = list.size() - 1; i >= 0; i--) {
            OilTransaction t = list.get(i);
            txnModel.addRow(new Object[]{
                t.getId(),
                t.getCompletedAt(),
                t.getLinkedSellRequestId(),
                String.format("%,.0f", t.getVolume()),
                String.format("$%.2f", t.getPricePerBarrel()),
                String.format("$%,.2f", t.getTotalRevenue())
            });
        }
    }

    private void refreshBreakdownTable() {
        breakdownModel.setRowCount(0);

        // Build a map: destination → aggregated stats
        // We get destination from the ShipmentRequest linked to each SellRequest
        Map<String, double[]> statsMap = new LinkedHashMap<>();
        // double[] = { txnCount, totalVolume, totalRevenue }

        for (OilTransaction t : rb.getTransactions()) {
            // Find the linked SellRequest to get the factory name
            String destination = getFactoryNameForSellRequest(t.getLinkedSellRequestId());

            statsMap.putIfAbsent(destination, new double[]{0, 0, 0});
            double[] stats = statsMap.get(destination);
            stats[0] += 1;                      // count
            stats[1] += t.getVolume();           // total volume
            stats[2] += t.getTotalRevenue();     // total revenue
        }

        for (Map.Entry<String, double[]> entry : statsMap.entrySet()) {
            double[] s = entry.getValue();
            double avgPrice = s[1] > 0 ? s[2] / s[1] : 0;
            breakdownModel.addRow(new Object[]{
                entry.getKey(),
                String.format("%.0f", s[0]),
                String.format("%,.0f", s[1]),
                String.format("$%,.2f", s[2]),
                String.format("$%.2f", avgPrice)
            });
        }

        // Totals row
        if (!statsMap.isEmpty()) {
            double totalTxn = 0, totalVol = 0, totalRev = 0;
            for (double[] s : statsMap.values()) {
                totalTxn += s[0];
                totalVol += s[1];
                totalRev += s[2];
            }
            double overallAvg = totalVol > 0 ? totalRev / totalVol : 0;
            breakdownModel.addRow(new Object[]{
                "TOTAL",
                String.format("%.0f", totalTxn),
                String.format("%,.0f", totalVol),
                String.format("$%,.2f", totalRev),
                String.format("$%.2f", overallAvg)
            });

            // Bold the totals row
            breakdownTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int col) {
                    Component c = super.getTableCellRendererComponent(
                            table, value, isSelected, hasFocus, row, col);
                    if (row == table.getRowCount() - 1) {
                        c.setFont(c.getFont().deriveFont(Font.BOLD));
                        c.setBackground(new Color(250, 238, 218));
                    } else {
                        c.setFont(c.getFont().deriveFont(Font.PLAIN));
                        c.setBackground(isSelected
                                ? table.getSelectionBackground()
                                : Color.WHITE);
                    }
                    if (col >= 1) {
                        ((JLabel) c).setHorizontalAlignment(SwingConstants.RIGHT);
                    }
                    return c;
                }
            });
        }
    }

    private void refreshMetrics() {
        ArrayList<OilTransaction> list = rb.getTransactions();

        if (list.isEmpty()) {
            lblTotalRevenue.setText("$0");
            lblTotalVolume.setText("0 bbl");
            lblTxnCount.setText("0");
            lblAvgPrice.setText("$0.00");
            return;
        }

        double totalRevenue = 0;
        double totalVolume = 0;

        for (OilTransaction t : list) {
            totalRevenue += t.getTotalRevenue();
            totalVolume += t.getVolume();
        }

        double avgPrice = totalVolume > 0 ? totalRevenue / totalVolume : 0;

        lblTotalRevenue.setText(String.format("$%,.0f", totalRevenue));
        lblTotalVolume.setText(String.format("%,.0f bbl", totalVolume));
        lblTxnCount.setText(String.valueOf(list.size()));
        lblAvgPrice.setText(String.format("$%.2f", avgPrice));
    }

    // ── Helpers ───────────────────────────────────────────────────────────────
    private String getFactoryNameForSellRequest(String sellRequestId) {
        // Walk sell requests to find the linked buy request, then get factory name
        for (SellRequest sr : rb.getSellRequests()) {
            if (sr.getId().equals(sellRequestId)) {
                var br = rb.findBuyRequest(sr.getLinkedBuyRequestId());
                if (br != null) {
                    return br.getFactoryName();
                }
            }
        }
        return "Unknown";
    }

    private JPanel buildMetricCard(String label, JLabel valueLabel) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(237, 237, 230));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 196, 185), 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lbl.setForeground(new Color(95, 94, 90));
        valueLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        card.add(lbl, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        return card;
    }

    private JLabel makeSectionLabel(String text) {
        JLabel lbl = new JLabel("  " + text);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbl.setBorder(BorderFactory.createEmptyBorder(6, 4, 4, 0));
        return lbl;
    }
}
