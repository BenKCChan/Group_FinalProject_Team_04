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
import Business.Operations.OilTransaction;
import Business.Operations.PriceSuggestion;
import Business.Operations.RequestBoard;
import Business.Operations.SellRequest;
import Business.Operations.ShipmentRequest;
import Business.Organization;
import Business.System.System;
import Business.UserAccount.UserAccount;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OilSupplierAgentWorkAreaJPanel extends JPanel {

    private JPanel userProcessContainer;
    private UserAccount userAccount;
    private Organization organization;
    private Enterprise enterprise;
    private System system;
    private RequestBoard rb;

    // Zone 1 — buy requests
    private JTable buyRequestTable;
    private DefaultTableModel buyRequestModel;

    // Zone 2 — analyst suggestions
    private JTable suggestionTable;
    private DefaultTableModel suggestionModel;
    private JTextField txtSupplierNote;
    private JButton btnAccept;
    private JButton btnReject;

    // Zone 3 — sell request form
    private JComboBox<String> cboBuyRequest;
    private JTextField txtSellPrice;
    private JTextField txtVolume;
    private JTextArea txtNotes;

    // Zone 4 — sell request history
    private JTable sellHistoryTable;
    private DefaultTableModel sellHistoryModel;

    public OilSupplierAgentWorkAreaJPanel(JPanel userProcessContainer, UserAccount userAccount,
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

        // ── Header ───────────────────────────────────────────────────────────
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 10));
        header.setBackground(new Color(250, 238, 218));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(133, 79, 11)));

        JLabel lblRole = new JLabel("Oil Supplier Agent  |  Oil Commercial Dept  |  " + enterprise.getName());
        lblRole.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblRole.setForeground(new Color(99, 56, 6));
        header.add(lblRole);

        JLabel lblUser = new JLabel("Logged in: " + userAccount.getUserLoginName());
        lblUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblUser.setForeground(new Color(133, 79, 11));
        header.add(lblUser);

        add(header, BorderLayout.NORTH);

        // ── Main center area: top split (buy requests | suggestions) ─────────
        JSplitPane topSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        topSplit.setDividerLocation(620);
        topSplit.setResizeWeight(0.55);
        topSplit.setLeftComponent(buildBuyRequestPane());
        topSplit.setRightComponent(buildSuggestionPane());

        // ── Form + history stacked in south ───────────────────────────────────
        JPanel southStack = new JPanel();
        southStack.setLayout(new BoxLayout(southStack, BoxLayout.Y_AXIS));
        southStack.add(buildSellRequestForm());
        southStack.add(buildSellHistoryPane());

        JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        mainSplit.setDividerLocation(280);
        mainSplit.setResizeWeight(0.4);
        mainSplit.setTopComponent(topSplit);
        mainSplit.setBottomComponent(southStack);

        add(mainSplit, BorderLayout.CENTER);
    }

    // ── Zone 1: Buy Request table ─────────────────────────────────────────────
    private JPanel buildBuyRequestPane() {
        String[] cols = {"Request ID", "Factory", "Volume (bbl)", "Ceiling Price", "Date", "Status"};
        buyRequestModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        buyRequestTable = new JTable(buyRequestModel);
        buyRequestTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        buyRequestTable.setRowHeight(24);
        buyRequestTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));

        // Selecting a buy request filters the suggestion table and fills the form combo
        buyRequestTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = buyRequestTable.getSelectedRow();
                if (row >= 0) {
                    String reqId = (String) buyRequestModel.getValueAt(row, 0);
                    refreshSuggestionTable(reqId);
                    cboBuyRequest.setSelectedItem(reqId);
                }
            }
        });

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(makeSectionLabel("Incoming Buy Requests"), BorderLayout.NORTH);
        pane.add(new JScrollPane(buyRequestTable), BorderLayout.CENTER);
        return pane;
    }

    // ── Zone 2: Analyst suggestions + accept/reject ───────────────────────────
    private JPanel buildSuggestionPane() {
        String[] cols = {"Suggestion ID", "Suggested Price", "Margin %", "Notes", "Status"};
        suggestionModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        suggestionTable = new JTable(suggestionModel);
        suggestionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        suggestionTable.setRowHeight(24);
        suggestionTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));

        // Color ACCEPTED green, REJECTED red
        suggestionTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, col);
                String status = (String) suggestionModel.getValueAt(row, 4);
                if (!isSelected) {
                    if ("ACCEPTED".equals(status)) {
                        c.setBackground(new Color(220, 245, 220));
                    } else if ("REJECTED".equals(status)) {
                        c.setBackground(new Color(250, 220, 220));
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                }
                return c;
            }
        });

        // Supplier note field + buttons
        JPanel actionBar = new JPanel(new BorderLayout(8, 0));
        actionBar.setBorder(BorderFactory.createEmptyBorder(6, 4, 6, 4));

        JPanel notePanel = new JPanel(new BorderLayout(4, 0));
        JLabel noteLabel = new JLabel("Note to analyst:");
        noteLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtSupplierNote = new JTextField();
        notePanel.add(noteLabel, BorderLayout.WEST);
        notePanel.add(txtSupplierNote, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 6, 0));
        btnAccept = new JButton("Accept");
        btnReject = new JButton("Reject");
        btnAccept.setBackground(new Color(220, 245, 220));
        btnAccept.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnReject.setBackground(new Color(250, 220, 220));
        btnReject.setFont(new Font("Tahoma", Font.BOLD, 12));

        btnAccept.addActionListener(e -> handleSuggestionDecision("ACCEPTED"));
        btnReject.addActionListener(e -> handleSuggestionDecision("REJECTED"));

        btnPanel.add(btnAccept);
        btnPanel.add(btnReject);

        actionBar.add(notePanel, BorderLayout.CENTER);
        actionBar.add(btnPanel, BorderLayout.EAST);

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(makeSectionLabel("Analyst Price Suggestions"), BorderLayout.NORTH);
        pane.add(new JScrollPane(suggestionTable), BorderLayout.CENTER);
        pane.add(actionBar, BorderLayout.SOUTH);
        return pane;
    }

    // ── Zone 3: Create sell request form ──────────────────────────────────────
    private JPanel buildSellRequestForm() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(200, 196, 185)),
                BorderFactory.createEmptyBorder(10, 16, 10, 16)
        ));
        form.setBackground(new Color(252, 251, 248));

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4, 6, 4, 6);
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridy = 0;

        // Row 0: section label spanning full width
        JLabel formTitle = new JLabel("Create Sell Request  →  Sends to Factory Manager");
        formTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
        formTitle.setForeground(new Color(99, 56, 6));
        gc.gridx = 0;
        gc.gridwidth = 6;
        gc.weightx = 1.0;
        form.add(formTitle, gc);

        gc.gridwidth = 1;
        gc.gridy = 1;

        // Row 1: Linked Buy Request
        gc.gridx = 0;
        gc.weightx = 0;
        form.add(makeFieldLabel("Linked buy request"), gc);
        gc.gridx = 1;
        gc.weightx = 0.3;
        cboBuyRequest = new JComboBox<>();
        form.add(cboBuyRequest, gc);

        // Sell Price
        gc.gridx = 2;
        gc.weightx = 0;
        form.add(makeFieldLabel("Sell price ($/bbl)"), gc);
        gc.gridx = 3;
        gc.weightx = 0.2;
        txtSellPrice = new JTextField(8);
        form.add(txtSellPrice, gc);

        // Volume
        gc.gridx = 4;
        gc.weightx = 0;
        form.add(makeFieldLabel("Volume (bbl)"), gc);
        gc.gridx = 5;
        gc.weightx = 0.2;
        txtVolume = new JTextField(8);
        form.add(txtVolume, gc);

        // Row 2: Notes + submit
        gc.gridy = 2;
        gc.gridx = 0;
        gc.weightx = 0;
        form.add(makeFieldLabel("Notes to factory"), gc);
        gc.gridx = 1;
        gc.gridwidth = 4;
        gc.weightx = 0.7;
        txtNotes = new JTextArea(2, 30);
        txtNotes.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtNotes.setLineWrap(true);
        txtNotes.setBorder(BorderFactory.createLineBorder(new Color(200, 196, 185)));
        form.add(new JScrollPane(txtNotes), gc);

        gc.gridx = 5;
        gc.gridwidth = 1;
        gc.weightx = 0.1;
        JButton btnSubmit = new JButton("<html><center>Submit<br>Sell Request</center></html>");
        btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSubmit.setBackground(new Color(250, 238, 218));
        btnSubmit.setForeground(new Color(99, 56, 6));
        btnSubmit.setPreferredSize(new Dimension(140, 50));
        btnSubmit.addActionListener(e -> handleSellRequestSubmit());
        form.add(btnSubmit, gc);

        return form;
    }

    // ── Zone 4: Sell request history ──────────────────────────────────────────
    private JPanel buildSellHistoryPane() {
        String[] cols = {"Sell Request ID", "Date", "Linked Buy Req", "Price ($/bbl)",
            "Volume (bbl)", "Total Revenue", "Status"};
        sellHistoryModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        sellHistoryTable = new JTable(sellHistoryModel);
        sellHistoryTable.setRowHeight(22);
        sellHistoryTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));

        JScrollPane scroll = new JScrollPane(sellHistoryTable);
        scroll.setPreferredSize(new Dimension(0, 160));

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(makeSectionLabel("Sell Request History"), BorderLayout.NORTH);
        pane.add(scroll, BorderLayout.CENTER);
        return pane;
    }

    // ── Action handlers ───────────────────────────────────────────────────────
    private void handleSuggestionDecision(String decision) {
        int row = suggestionTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this,
                    "Please select a suggestion to " + decision.toLowerCase() + ".",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sugId = (String) suggestionModel.getValueAt(row, 0);
        String currentStatus = (String) suggestionModel.getValueAt(row, 4);

        if (!"PENDING".equals(currentStatus)) {
            JOptionPane.showMessageDialog(this,
                    "This suggestion has already been " + currentStatus + ".",
                    "Already Decided", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String note = txtSupplierNote.getText().trim();

        // Find and update the PriceSuggestion object
        for (PriceSuggestion ps : rb.getPriceSuggestions()) {
            if (ps.getId().equals(sugId)) {
                ps.setStatus(decision);
                ps.setSupplierNote(note.isEmpty() ? "—" : note);
                break;
            }
        }

        // Refresh the suggestion table for current buy request
        int buyRow = buyRequestTable.getSelectedRow();
        if (buyRow >= 0) {
            refreshSuggestionTable((String) buyRequestModel.getValueAt(buyRow, 0));
        }

        txtSupplierNote.setText("");

        // Auto-fill sell price from accepted suggestion
        if ("ACCEPTED".equals(decision)) {
            for (PriceSuggestion ps : rb.getPriceSuggestions()) {
                if (ps.getId().equals(sugId)) {
                    txtSellPrice.setText(String.format("%.2f", ps.getSuggestedPrice()));
                    // Auto-fill volume from linked buy request
                    BuyRequest br = rb.findBuyRequest(ps.getLinkedBuyRequestId());
                    if (br != null) {
                        txtVolume.setText(String.format("%.0f", br.getVolume()));
                    }
                    break;
                }
            }
            JOptionPane.showMessageDialog(this,
                    "Suggestion accepted. Sell price and volume pre-filled from analyst recommendation.",
                    "Accepted", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Suggestion rejected. Note sent back to analyst.",
                    "Rejected", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void handleSellRequestSubmit() {
        // ── Validation ────────────────────────────────────────────────────────
        String linkedBuyId = (String) cboBuyRequest.getSelectedItem();
        if (linkedBuyId == null || linkedBuyId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a linked buy request.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String priceText = txtSellPrice.getText().trim();
        if (priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a sell price.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double sellPrice;
        try {
            sellPrice = Double.parseDouble(priceText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Sell price must be a valid number.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (sellPrice <= 0) {
            JOptionPane.showMessageDialog(this, "Sell price must be greater than zero.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String volumeText = txtVolume.getText().trim();
        if (volumeText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a volume.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double volume;
        try {
            volume = Double.parseDouble(volumeText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Volume must be a valid number.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (volume <= 0) {
            JOptionPane.showMessageDialog(this, "Volume must be greater than zero.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Check the factory's price ceiling
        BuyRequest br = rb.findBuyRequest(linkedBuyId);
        if (br != null && sellPrice > br.getPriceCeiling()) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    String.format("Sell price $%.2f exceeds factory ceiling $%.2f. Submit anyway?",
                            sellPrice, br.getPriceCeiling()),
                    "Above Ceiling", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
        }

        // ── Submit ────────────────────────────────────────────────────────────
        String today = LocalDate.now().toString();
        String agentName = enterprise.getName();
        String factoryName = (br != null) ? br.getFactoryName() : "Factory";

        // 1. Create sell request (cross-enterprise work request)
        SellRequest sr = rb.newSellRequest(
                userAccount.getId(), linkedBuyId, sellPrice, volume, today);

        // 2. Auto-create OilTransaction
        rb.newTransaction(sr.getId(), volume, sellPrice, today);

        // 3. Auto-create ShipmentRequest → notifies Transport Coordinator
        rb.newShipmentRequest(sr.getId(), volume, agentName, factoryName, today);

        // 4. Update the buy request status to APPROVED
        if (br != null) {
            br.setStatus("APPROVED");
        }

        JOptionPane.showMessageDialog(this,
                String.format("Sell request %s submitted.\nTransaction and shipment request created automatically.",
                        sr.getId()),
                "Submitted", JOptionPane.INFORMATION_MESSAGE);

        // Clear form
        txtSellPrice.setText("");
        txtVolume.setText("");
        txtNotes.setText("");
        cboBuyRequest.setSelectedIndex(0);

        refreshAll();
    }

    // ── Refresh methods ───────────────────────────────────────────────────────
    private void refreshAll() {
        refreshBuyRequestTable();
        refreshComboBox();
        refreshSellHistoryTable();
        // Clear suggestion table until a buy request is selected
        suggestionModel.setRowCount(0);
    }

    private void refreshBuyRequestTable() {
        buyRequestModel.setRowCount(0);
        for (BuyRequest br : rb.getBuyRequests()) {
            buyRequestModel.addRow(new Object[]{
                br.getId(),
                br.getFactoryName(),
                String.format("%.0f", br.getVolume()),
                String.format("$%.2f", br.getPriceCeiling()),
                br.getCreatedAt(),
                br.getStatus()
            });
        }
    }

    private void refreshSuggestionTable(String linkedBuyReqId) {
        suggestionModel.setRowCount(0);
        for (PriceSuggestion ps : rb.getPriceSuggestions()) {
            if (ps.getLinkedBuyRequestId().equals(linkedBuyReqId)) {
                suggestionModel.addRow(new Object[]{
                    ps.getId(),
                    String.format("$%.2f", ps.getSuggestedPrice()),
                    String.format("%.1f%%", ps.getMarginPct()),
                    ps.getNotes().length() > 40
                    ? ps.getNotes().substring(0, 40) + "…"
                    : ps.getNotes(),
                    ps.getStatus()
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

    private void refreshSellHistoryTable() {
        sellHistoryModel.setRowCount(0);
        ArrayList<SellRequest> list = rb.getSellRequests();
        for (int i = list.size() - 1; i >= 0; i--) {
            SellRequest sr = list.get(i);
            if (sr.getSupplierAgentPersonId().equals(userAccount.getId())) {
                double revenue = sr.getSellPrice() * sr.getVolume();
                sellHistoryModel.addRow(new Object[]{
                    sr.getId(),
                    sr.getCreatedAt(),
                    sr.getLinkedBuyRequestId(),
                    String.format("$%.2f", sr.getSellPrice()),
                    String.format("%.0f", sr.getVolume()),
                    String.format("$%,.2f", revenue),
                    sr.getStatus()
                });
            }
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────
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
        return lbl;
    }
}
