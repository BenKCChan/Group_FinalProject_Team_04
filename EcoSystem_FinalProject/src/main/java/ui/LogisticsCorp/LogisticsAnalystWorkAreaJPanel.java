/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.LogisticsCorp;

/**
 *
 * @author lindq
 */

import Business.Enterprise.Enterprise;
import Business.Operations.RequestBoard;
import Business.Operations.ShipmentRequest;
import Business.Organization;
import Business.System.System;
import Business.UserAccount.UserAccount;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class LogisticsAnalystWorkAreaJPanel extends JPanel {

    private JPanel userProcessContainer;
    private UserAccount userAccount;
    private Organization organization;
    private Enterprise enterprise;
    private System system;
    private RequestBoard rb;

    // Metric labels
    private JLabel lblTotalShipments;
    private JLabel lblDelivered;
    private JLabel lblInTransit;
    private JLabel lblTotalVolume;

    // Tables
    private JTable shipmentTable;
    private DefaultTableModel shipmentModel;

    private JTable routeTable;
    private DefaultTableModel routeModel;

    public LogisticsAnalystWorkAreaJPanel(JPanel userProcessContainer, UserAccount userAccount,
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
        header.setBackground(new Color(225, 245, 238));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(15, 110, 86)));

        JLabel lblRole = new JLabel("Logistics Analyst  |  Fleet Management  |  " + enterprise.getName());
        lblRole.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblRole.setForeground(new Color(8, 80, 65));
        header.add(lblRole);

        JLabel lblUser = new JLabel("Logged in: " + userAccount.getUserLoginName());
        lblUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblUser.setForeground(new Color(15, 110, 86));
        header.add(lblUser);

        JButton btnRefresh = new JButton("Refresh Report");
        btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnRefresh.addActionListener(e -> refreshAll());
        header.add(btnRefresh);

        // ── Metric cards ──────────────────────────────────────────────────────
        JPanel metricsPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        metricsPanel.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        metricsPanel.setBackground(new Color(240, 250, 246));

        lblTotalShipments = new JLabel("0");
        lblDelivered      = new JLabel("0");
        lblInTransit      = new JLabel("0");
        lblTotalVolume    = new JLabel("0 bbl");

        metricsPanel.add(buildMetricCard("Total shipments", lblTotalShipments));
        metricsPanel.add(buildMetricCard("Delivered", lblDelivered));
        metricsPanel.add(buildMetricCard("In transit / scheduled", lblInTransit));
        metricsPanel.add(buildMetricCard("Total volume handled", lblTotalVolume));

        JPanel northStack = new JPanel();
        northStack.setLayout(new BoxLayout(northStack, BoxLayout.Y_AXIS));
        northStack.add(header);
        northStack.add(metricsPanel);
        add(northStack, BorderLayout.NORTH);

        // ── Center: full shipment log ─────────────────────────────────────────
        String[] cols = {"Shipment ID", "Volume (bbl)", "Origin",
                         "Destination", "Scheduled", "Delivered", "Status"};
        shipmentModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        shipmentTable = new JTable(shipmentModel);
        shipmentTable.setRowHeight(24);
        shipmentTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
        shipmentTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));

        // Color by status
        shipmentTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    String status = (String) shipmentModel.getValueAt(row, 6);
                    switch (status) {
                        case "SCHEDULED"  -> c.setBackground(new Color(225, 245, 238));
                        case "IN_TRANSIT" -> c.setBackground(new Color(214, 234, 248));
                        case "DELIVERED"  -> c.setBackground(new Color(214, 245, 214));
                        default           -> c.setBackground(Color.WHITE);
                    }
                }
                return c;
            }
        });

        JPanel centerPane = new JPanel(new BorderLayout());
        centerPane.add(makeSectionLabel("Full shipment log"), BorderLayout.NORTH);
        centerPane.add(new JScrollPane(shipmentTable), BorderLayout.CENTER);

        // ── South: route breakdown ────────────────────────────────────────────
        String[] routeCols = {"Route (Origin → Destination)", "Shipments",
                              "Volume (bbl)", "Delivered", "Pending"};
        routeModel = new DefaultTableModel(routeCols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        routeTable = new JTable(routeModel);
        routeTable.setRowHeight(24);
        routeTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
        routeTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));

        // Right-align numeric columns
        DefaultTableCellRenderer rightAlign = new DefaultTableCellRenderer();
        rightAlign.setHorizontalAlignment(SwingConstants.RIGHT);
        for (int i = 1; i <= 4; i++) {
            routeTable.getColumnModel().getColumn(i).setCellRenderer(rightAlign);
        }

        JScrollPane routeScroll = new JScrollPane(routeTable);
        routeScroll.setPreferredSize(new Dimension(0, 160));

        JPanel southPane = new JPanel(new BorderLayout());
        southPane.add(makeSectionLabel("Performance by route"), BorderLayout.NORTH);
        southPane.add(routeScroll, BorderLayout.CENTER);
        southPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,
                new Color(159, 225, 203)));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(320);
        splitPane.setResizeWeight(0.65);
        splitPane.setTopComponent(centerPane);
        splitPane.setBottomComponent(southPane);

        add(splitPane, BorderLayout.CENTER);
    }

    // ── Refresh ───────────────────────────────────────────────────────────────
    private void refreshAll() {
        refreshShipmentTable();
        refreshRouteTable();
        refreshMetrics();
    }

    private void refreshShipmentTable() {
        shipmentModel.setRowCount(0);
        var list = rb.getShipmentRequests();
        for (int i = list.size() - 1; i >= 0; i--) {
            ShipmentRequest sh = list.get(i);
            shipmentModel.addRow(new Object[]{
                sh.getId(),
                String.format("%,.0f", sh.getVolume()),
                sh.getOrigin(),
                sh.getDestination(),
                sh.getScheduledDate(),
                sh.getDeliveredDate().isEmpty() ? "—" : sh.getDeliveredDate(),
                sh.getStatus()
            });
        }
    }

    private void refreshRouteTable() {
        routeModel.setRowCount(0);

        // Map: "Origin → Destination" → [count, volume, delivered, pending]
        Map<String, double[]> map = new LinkedHashMap<>();

        for (ShipmentRequest sh : rb.getShipmentRequests()) {
            String route = sh.getOrigin() + " → " + sh.getDestination();
            map.putIfAbsent(route, new double[]{0, 0, 0, 0});
            double[] s = map.get(route);
            s[0]++;
            s[1] += sh.getVolume();
            if ("DELIVERED".equals(sh.getStatus())) s[2]++;
            else s[3]++;
        }

        for (Map.Entry<String, double[]> entry : map.entrySet()) {
            double[] s = entry.getValue();
            routeModel.addRow(new Object[]{
                entry.getKey(),
                String.format("%.0f", s[0]),
                String.format("%,.0f", s[1]),
                String.format("%.0f", s[2]),
                String.format("%.0f", s[3])
            });
        }

        // Totals row
        if (!map.isEmpty()) {
            double tc = 0, tv = 0, td = 0, tp = 0;
            for (double[] s : map.values()) {
                tc += s[0]; tv += s[1]; td += s[2]; tp += s[3];
            }
            routeModel.addRow(new Object[]{
                "TOTAL",
                String.format("%.0f", tc),
                String.format("%,.0f", tv),
                String.format("%.0f", td),
                String.format("%.0f", tp)
            });

            // Bold totals row
            routeTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int col) {
                    Component c = super.getTableCellRendererComponent(
                            table, value, isSelected, hasFocus, row, col);
                    if (row == table.getRowCount() - 1) {
                        c.setFont(c.getFont().deriveFont(Font.BOLD));
                        c.setBackground(new Color(225, 245, 238));
                    } else {
                        c.setFont(c.getFont().deriveFont(Font.PLAIN));
                        c.setBackground(isSelected
                                ? table.getSelectionBackground()
                                : Color.WHITE);
                    }
                    if (col >= 1) ((JLabel) c).setHorizontalAlignment(SwingConstants.RIGHT);
                    return c;
                }
            });
        }
    }

    private void refreshMetrics() {
        var list = rb.getShipmentRequests();
        int delivered = 0, other = 0;
        double totalVol = 0;

        for (ShipmentRequest sh : list) {
            totalVol += sh.getVolume();
            if ("DELIVERED".equals(sh.getStatus())) delivered++;
            else other++;
        }

        lblTotalShipments.setText(String.valueOf(list.size()));
        lblDelivered.setText(String.valueOf(delivered));
        lblInTransit.setText(String.valueOf(other));
        lblTotalVolume.setText(String.format("%,.0f bbl", totalVol));
    }

    // ── Helpers ───────────────────────────────────────────────────────────────
    private JPanel buildMetricCard(String label, JLabel valueLabel) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(225, 245, 238));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(159, 225, 203), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lbl.setForeground(new Color(8, 80, 65));
        valueLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
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