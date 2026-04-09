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
import Business.Operations.BuyRequest;
import Business.Operations.PriceSuggestion;
import Business.Operations.RequestBoard;
import Business.Organization;
import Business.System.System;
import Business.UserAccount.UserAccount;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OilAnalystWorkAreaJPanel extends JPanel {

    private JPanel userProcessContainer;
    private UserAccount userAccount;
    private Organization organization;
    private Enterprise enterprise;
    private System system;
    private RequestBoard rb;

    private static final double BREAK_EVEN = 61.20;

    // Metric labels
    private JLabel lblMarketPrice;
    private JLabel lblAvg30;
    private JLabel lblInventory;
    private JLabel lblBreakEven;

    // Zone 1 — buy request table
    private JTable buyRequestTable;
    private DefaultTableModel buyRequestModel;

    // Zone 2 — form
    private JComboBox<String> cboBuyRequest;
    private JTextField txtSuggestedPrice;
    private JLabel lblMargin;
    private JTextArea txtNotes;

    // Zone 3 — history table
    private JTable historyTable;
    private DefaultTableModel historyModel;

    public OilAnalystWorkAreaJPanel(JPanel userProcessContainer, UserAccount userAccount,
                                     Organization organization, Enterprise enterprise, System system) {
        this.userProcessContainer = userProcessContainer;
        this.userAccount = userAccount;
        this.organization = organization;
        this.enterprise = enterprise;
        this.system = system;
        this.rb = system.getRequestBoard();

        setLayout(new BorderLayout(0, 0));
        buildUI();
        refreshBuyRequestTable();
        refreshComboBox();
        refreshHistoryTable();
    }

    private void buildUI() {

        // ── Header ────────────────────────────────────────────────────────────
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 10));
        header.setBackground(new Color(250, 238, 218));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(133, 79, 11)));

        JLabel lblRole = new JLabel("Oil Analyst  |  Oil Analytics Dept  |  " + enterprise.getName());
        lblRole.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblRole.setForeground(new Color(99, 56, 6));
        header.add(lblRole);

        JLabel lblUser = new JLabel("Logged in: " + userAccount.getUserLoginName());
        lblUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblUser.setForeground(new Color(133, 79, 11));
        header.add(lblUser);

        // ── Metric cards ──────────────────────────────────────────────────────
        JPanel metricsPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        metricsPanel.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        metricsPanel.setBackground(new Color(245, 245, 240));

        lblMarketPrice = new JLabel("$78.40");
        lblAvg30       = new JLabel("$76.15");
        lblInventory   = new JLabel("42,500 bbl");
        lblBreakEven   = new JLabel(String.format("$%.2f", BREAK_EVEN));

        metricsPanel.add(buildMetricCard("Market price (live)", lblMarketPrice));
        metricsPanel.add(buildMetricCard("30-day average", lblAvg30));
        metricsPanel.add(buildMetricCard("Inventory available", lblInventory));
        metricsPanel.add(buildMetricCard("Break-even cost", lblBreakEven));

        JPanel northStack = new JPanel();
        northStack.setLayout(new BoxLayout(northStack, BoxLayout.Y_AXIS));
        northStack.add(header);
        northStack.add(metricsPanel);
        add(northStack, BorderLayout.NORTH);

        // ── Center split: buy request table | form ────────────────────────────
        JSplitPane centerSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        centerSplit.setDividerLocation(680);
        centerSplit.setResizeWeight(0.6);
        centerSplit.setLeftComponent(buildBuyRequestPane());
        centerSplit.setRightComponent(buildFormPane());

        // ── South: history table ──────────────────────────────────────────────
        JPanel southPanel = buildHistoryPane();

        JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        mainSplit.setDividerLocation(300);
        mainSplit.setResizeWeight(0.55);
        mainSplit.setTopComponent(centerSplit);
        mainSplit.setBottomComponent(southPanel);

        add(mainSplit, BorderLayout.CENTER);
    }

    // ── Zone 1: Buy request table ─────────────────────────────────────────────
    private JPanel buildBuyRequestPane() {
        String[] cols = {"Request ID", "Factory", "Volume (bbl)", "Their Ceiling", "Date", "Status"};
        buyRequestModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        buyRequestTable = new JTable(buyRequestModel);
        buyRequestTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        buyRequestTable.setRowHeight(24);
        buyRequestTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
        buyRequestTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));

        // Alternating row colors
        buyRequestTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(252, 251, 248));
                }
                return c;
            }
        });

        // Row click auto-fills combo box
        buyRequestTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = buyRequestTable.getSelectedRow();
                if (row >= 0) {
                    String reqId = (String) buyRequestModel.getValueAt(row, 0);
                    cboBuyRequest.setSelectedItem(reqId);
                }
            }
        });

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(makeSectionLabel("Pending buy requests"), BorderLayout.NORTH);
        pane.add(new JScrollPane(buyRequestTable), BorderLayout.CENTER);

        JLabel hint = new JLabel("  Click a row to link it to your suggestion");
        hint.setFont(new Font("Tahoma", Font.ITALIC, 11));
        hint.setForeground(new Color(133, 79, 11));
        hint.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        pane.add(hint, BorderLayout.SOUTH);

        return pane;
    }

    // ── Zone 2: Suggestion form ───────────────────────────────────────────────
    private JPanel buildFormPane() {
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        form.setBackground(new Color(252, 251, 248));

        JLabel formTitle = new JLabel("Submit price suggestion");
        formTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
        formTitle.setForeground(new Color(99, 56, 6));
        formTitle.setAlignmentX(LEFT_ALIGNMENT);
        form.add(formTitle);
        form.add(Box.createVerticalStrut(12));

        // Linked buy request
        form.add(makeFieldLabel("Linked buy request"));
        cboBuyRequest = new JComboBox<>();
        cboBuyRequest.setAlignmentX(LEFT_ALIGNMENT);
        cboBuyRequest.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        form.add(cboBuyRequest);
        form.add(Box.createVerticalStrut(10));

        // Price + margin row
        JPanel priceRow = new JPanel(new GridLayout(1, 2, 10, 0));
        priceRow.setAlignmentX(LEFT_ALIGNMENT);
        priceRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        priceRow.setOpaque(false);

        JPanel priceField = new JPanel(new BorderLayout(0, 4));
        priceField.setOpaque(false);
        priceField.add(makeFieldLabel("Suggested price ($/bbl)"), BorderLayout.NORTH);
        txtSuggestedPrice = new JTextField("79.50");
        txtSuggestedPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
        priceField.add(txtSuggestedPrice, BorderLayout.CENTER);

        JPanel marginField = new JPanel(new BorderLayout(0, 4));
        marginField.setOpaque(false);
        marginField.add(makeFieldLabel("Margin vs break-even"), BorderLayout.NORTH);
        lblMargin = new JLabel(computeMarginText("79.50"));
        lblMargin.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblMargin.setForeground(new Color(59, 109, 17));
        JPanel marginBox = new JPanel(new BorderLayout());
        marginBox.setBorder(BorderFactory.createLineBorder(new Color(200, 196, 185)));
        marginBox.setBackground(Color.WHITE);
        marginBox.add(lblMargin, BorderLayout.CENTER);
        marginField.add(marginBox, BorderLayout.CENTER);

        priceRow.add(priceField);
        priceRow.add(marginField);
        form.add(priceRow);
        form.add(Box.createVerticalStrut(10));

        // Live margin update
        txtSuggestedPrice.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e)  { updateMarginDisplay(); }
            public void removeUpdate(DocumentEvent e)  { updateMarginDisplay(); }
            public void changedUpdate(DocumentEvent e) { updateMarginDisplay(); }
        });

        // Notes
        form.add(makeFieldLabel("Justification notes"));
        txtNotes = new JTextArea(4, 20);
        txtNotes.setLineWrap(true);
        txtNotes.setWrapStyleWord(true);
        txtNotes.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtNotes.setBorder(BorderFactory.createLineBorder(new Color(200, 196, 185)));
        JScrollPane noteScroll = new JScrollPane(txtNotes);
        noteScroll.setAlignmentX(LEFT_ALIGNMENT);
        noteScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        form.add(noteScroll);
        form.add(Box.createVerticalStrut(14));

        // Submit button
        JButton btnSubmit = new JButton("Submit to Oil Supplier Agent");
        btnSubmit.setAlignmentX(LEFT_ALIGNMENT);
        btnSubmit.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSubmit.setBackground(new Color(250, 238, 218));
        btnSubmit.setForeground(new Color(99, 56, 6));
        btnSubmit.addActionListener(e -> handleSubmit());
        form.add(btnSubmit);

        return form;
    }

    // ── Zone 3: History table ─────────────────────────────────────────────────
    private JPanel buildHistoryPane() {
        String[] cols = {"Suggestion ID", "Date", "Price ($/bbl)", "Margin %",
                         "Linked Request", "Status", "Supplier Note"};
        historyModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        historyTable = new JTable(historyModel);
        historyTable.setRowHeight(22);
        historyTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
        historyTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));

        // Color rows by status
        historyTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    String status = (String) historyModel.getValueAt(row, 5);
                    switch (status) {
                        case "ACCEPTED" -> c.setBackground(new Color(220, 245, 220));
                        case "REJECTED" -> c.setBackground(new Color(250, 220, 220));
                        default         -> c.setBackground(Color.WHITE);
                    }
                }
                return c;
            }
        });

        JScrollPane scroll = new JScrollPane(historyTable);
        scroll.setPreferredSize(new Dimension(0, 180));

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(makeSectionLabel("Suggestion history"), BorderLayout.NORTH);
        pane.add(scroll, BorderLayout.CENTER);
        pane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 196, 185)));

        return pane;
    }

    // ── Submit handler ────────────────────────────────────────────────────────
    private void handleSubmit() {
        String selectedReqId = (String) cboBuyRequest.getSelectedItem();
        if (selectedReqId == null || selectedReqId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please select a linked buy request.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String priceText = txtSuggestedPrice.getText().trim();
        if (priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter a suggested price.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Suggested price must be a valid number.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (price <= 0) {
            JOptionPane.showMessageDialog(this,
                "Price must be greater than zero.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (price < BREAK_EVEN) {
            JOptionPane.showMessageDialog(this,
                String.format("Price $%.2f is below break-even ($%.2f). Cannot submit.", price, BREAK_EVEN),
                "Below Break-Even", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String notes = txtNotes.getText().trim();
        if (notes.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please provide justification notes.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double margin = ((price - BREAK_EVEN) / BREAK_EVEN) * 100.0;
        String today = LocalDate.now().toString();

        rb.newPriceSuggestion(
            userAccount.getId(),
            selectedReqId,
            price,
            margin,
            notes,
            today
        );

        JOptionPane.showMessageDialog(this,
            "Price suggestion submitted successfully.\nLinked to: " + selectedReqId,
            "Submitted", JOptionPane.INFORMATION_MESSAGE);

        txtSuggestedPrice.setText("");
        txtNotes.setText("");
        if (cboBuyRequest.getItemCount() > 0) cboBuyRequest.setSelectedIndex(0);

        refreshHistoryTable();
    }

    // ── Refresh methods ───────────────────────────────────────────────────────
    private void refreshBuyRequestTable() {
        buyRequestModel.setRowCount(0);
        for (BuyRequest br : rb.getBuyRequests()) {
            if ("OPEN".equals(br.getStatus())) {
                buyRequestModel.addRow(new Object[]{
                    br.getId(),
                    br.getFactoryName(),
                    String.format("%,.0f", br.getVolume()),
                    String.format("$%.2f", br.getPriceCeiling()),
                    br.getCreatedAt(),
                    br.getStatus()
                });
            }
        }
    }

    private void refreshComboBox() {
        cboBuyRequest.removeAllItems();
        cboBuyRequest.addItem("");
        for (BuyRequest br : rb.getBuyRequests()) {
            if ("OPEN".equals(br.getStatus())) {
                cboBuyRequest.addItem(br.getId());
            }
        }
    }

    private void refreshHistoryTable() {
        historyModel.setRowCount(0);
        ArrayList<PriceSuggestion> suggestions = rb.getPriceSuggestions();
        for (int i = suggestions.size() - 1; i >= 0; i--) {
            PriceSuggestion ps = suggestions.get(i);
            if (ps.getAnalystPersonId().equals(userAccount.getId())) {
                historyModel.addRow(new Object[]{
                    ps.getId(),
                    ps.getSubmittedAt(),
                    String.format("$%.2f", ps.getSuggestedPrice()),
                    String.format("%.1f%%", ps.getMarginPct()),
                    ps.getLinkedBuyRequestId(),
                    ps.getStatus(),
                    ps.getSupplierNote().isEmpty() ? "—" : ps.getSupplierNote()
                });
            }
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────
    private void updateMarginDisplay() {
        String text = txtSuggestedPrice.getText().trim();
        lblMargin.setText(computeMarginText(text));
        try {
            double p = Double.parseDouble(text);
            lblMargin.setForeground(p >= BREAK_EVEN
                ? new Color(59, 109, 17)
                : new Color(162, 45, 45));
        } catch (NumberFormatException ex) {
            lblMargin.setForeground(Color.GRAY);
        }
    }

    private String computeMarginText(String priceText) {
        try {
            double p = Double.parseDouble(priceText);
            if (p <= 0) return "—";
            double m = ((p - BREAK_EVEN) / BREAK_EVEN) * 100.0;
            return String.format("%.1f%%", m);
        } catch (NumberFormatException ex) {
            return "—";
        }
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

    private JLabel makeFieldLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lbl.setForeground(new Color(95, 94, 90));
        lbl.setAlignmentX(LEFT_ALIGNMENT);
        return lbl;
    }
}