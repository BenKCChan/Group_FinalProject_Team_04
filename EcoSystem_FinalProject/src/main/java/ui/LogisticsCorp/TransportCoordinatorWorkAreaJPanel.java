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
import java.time.LocalDate;
import java.util.ArrayList;

public class TransportCoordinatorWorkAreaJPanel extends JPanel {

    private JPanel userProcessContainer;
    private UserAccount userAccount;
    private Organization organization;
    private Enterprise enterprise;
    private System system;
    private RequestBoard rb;

    // Metric labels
    private JLabel lblScheduled;
    private JLabel lblInTransit;
    private JLabel lblDelivered;
    private JLabel lblTotalVolume;

    // Shipment table
    private JTable shipmentTable;
    private DefaultTableModel shipmentModel;

    // Action controls
    private JButton btnMarkInTransit;
    private JButton btnMarkDelivered;
    private JTextField txtDeliveryNote;

    public TransportCoordinatorWorkAreaJPanel(JPanel userProcessContainer, UserAccount userAccount,
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

        JLabel lblRole = new JLabel("Transport Coordinator  |  Shipment Operations  |  " + enterprise.getName());
        lblRole.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblRole.setForeground(new Color(8, 80, 65));
        header.add(lblRole);

        JLabel lblUser = new JLabel("Logged in: " + userAccount.getUserLoginName());
        lblUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblUser.setForeground(new Color(15, 110, 86));
        header.add(lblUser);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnRefresh.addActionListener(e -> refreshAll());
        header.add(btnRefresh);

        // ── Metric cards ──────────────────────────────────────────────────────
        JPanel metricsPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        metricsPanel.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        metricsPanel.setBackground(new Color(240, 250, 246));

        lblScheduled  = new JLabel("0");
        lblInTransit  = new JLabel("0");
        lblDelivered  = new JLabel("0");
        lblTotalVolume = new JLabel("0 bbl");

        metricsPanel.add(buildMetricCard("Scheduled", lblScheduled, new Color(225, 245, 238)));
        metricsPanel.add(buildMetricCard("In transit", lblInTransit, new Color(214, 234, 248)));
        metricsPanel.add(buildMetricCard("Delivered", lblDelivered, new Color(214, 245, 214)));
        metricsPanel.add(buildMetricCard("Total volume dispatched", lblTotalVolume, new Color(237, 237, 230)));

        JPanel northStack = new JPanel();
        northStack.setLayout(new BoxLayout(northStack, BoxLayout.Y_AXIS));
        northStack.add(header);
        northStack.add(metricsPanel);
        add(northStack, BorderLayout.NORTH);

        // ── Center: shipment table ────────────────────────────────────────────
        String[] cols = {"Shipment ID", "Linked Sell Req", "Volume (bbl)",
                         "Origin", "Destination", "Scheduled", "Delivered", "Status"};
        shipmentModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        shipmentTable = new JTable(shipmentModel);
        shipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        shipmentTable.setRowHeight(24);
        shipmentTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
        shipmentTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));

        // Color rows by status
        shipmentTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    String status = (String) shipmentModel.getValueAt(row, 7);
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
        centerPane.add(makeSectionLabel("Shipment queue"), BorderLayout.NORTH);
        centerPane.add(new JScrollPane(shipmentTable), BorderLayout.CENTER);

        // ── Action bar ────────────────────────────────────────────────────────
        JPanel actionBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        actionBar.setBackground(new Color(240, 250, 246));
        actionBar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(159, 225, 203)));

        JLabel noteLabel = new JLabel("Note:");
        noteLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtDeliveryNote = new JTextField(25);
        txtDeliveryNote.setFont(new Font("Tahoma", Font.PLAIN, 12));

        btnMarkInTransit = new JButton("Mark as In Transit");
        btnMarkInTransit.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnMarkInTransit.setBackground(new Color(214, 234, 248));
        btnMarkInTransit.setForeground(new Color(12, 68, 124));

        btnMarkDelivered = new JButton("Mark as Delivered");
        btnMarkDelivered.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnMarkDelivered.setBackground(new Color(214, 245, 214));
        btnMarkDelivered.setForeground(new Color(39, 80, 10));

        btnMarkInTransit.addActionListener(e -> handleStatusUpdate("IN_TRANSIT"));
        btnMarkDelivered.addActionListener(e -> handleStatusUpdate("DELIVERED"));

        actionBar.add(noteLabel);
        actionBar.add(txtDeliveryNote);
        actionBar.add(btnMarkInTransit);
        actionBar.add(btnMarkDelivered);

        centerPane.add(actionBar, BorderLayout.SOUTH);
        add(centerPane, BorderLayout.CENTER);
    }

    // ── Status update handler ─────────────────────────────────────────────────
    private void handleStatusUpdate(String newStatus) {
        int row = shipmentTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this,
                "Please select a shipment from the table.",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String shipId  = (String) shipmentModel.getValueAt(row, 0);
        String current = (String) shipmentModel.getValueAt(row, 7);

        // Enforce valid transitions
        if ("DELIVERED".equals(current)) {
            JOptionPane.showMessageDialog(this,
                "This shipment has already been delivered.",
                "Already Delivered", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if ("IN_TRANSIT".equals(newStatus) && "IN_TRANSIT".equals(current)) {
            JOptionPane.showMessageDialog(this,
                "Shipment is already in transit.",
                "No Change", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if ("IN_TRANSIT".equals(newStatus) && "DELIVERED".equals(current)) {
            JOptionPane.showMessageDialog(this,
                "Cannot move a delivered shipment back to in transit.",
                "Invalid Transition", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Find and update the ShipmentRequest object
        for (ShipmentRequest sh : rb.getShipmentRequests()) {
            if (sh.getId().equals(shipId)) {
                sh.setStatus(newStatus);
                if ("DELIVERED".equals(newStatus)) {
                    sh.setDeliveredDate(LocalDate.now().toString());
                }
                break;
            }
        }

        JOptionPane.showMessageDialog(this,
            "Shipment " + shipId + " updated to " + newStatus + ".",
            "Status Updated", JOptionPane.INFORMATION_MESSAGE);

        txtDeliveryNote.setText("");
        refreshAll();
    }

    // ── Refresh ───────────────────────────────────────────────────────────────
    private void refreshAll() {
        refreshShipmentTable();
        refreshMetrics();
    }

    private void refreshShipmentTable() {
        shipmentModel.setRowCount(0);
        ArrayList<ShipmentRequest> list = rb.getShipmentRequests();
        for (int i = list.size() - 1; i >= 0; i--) {
            ShipmentRequest sh = list.get(i);
            shipmentModel.addRow(new Object[]{
                sh.getId(),
                sh.getLinkedSellRequestId(),
                String.format("%,.0f", sh.getVolume()),
                sh.getOrigin(),
                sh.getDestination(),
                sh.getScheduledDate(),
                sh.getDeliveredDate().isEmpty() ? "—" : sh.getDeliveredDate(),
                sh.getStatus()
            });
        }
    }

    private void refreshMetrics() {
        int scheduled = 0, inTransit = 0, delivered = 0;
        double totalVolume = 0;

        for (ShipmentRequest sh : rb.getShipmentRequests()) {
            totalVolume += sh.getVolume();
            switch (sh.getStatus()) {
                case "SCHEDULED"  -> scheduled++;
                case "IN_TRANSIT" -> inTransit++;
                case "DELIVERED"  -> delivered++;
            }
        }

        lblScheduled.setText(String.valueOf(scheduled));
        lblInTransit.setText(String.valueOf(inTransit));
        lblDelivered.setText(String.valueOf(delivered));
        lblTotalVolume.setText(String.format("%,.0f bbl", totalVolume));
    }

    // ── Helpers ───────────────────────────────────────────────────────────────
    private JPanel buildMetricCard(String label, JLabel valueLabel, Color bg) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(bg);
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