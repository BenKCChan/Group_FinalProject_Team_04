/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.utils;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.SwingUtilities;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import javafx.scene.web.WebEngine;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author ben
 */
public class IranHormuzMapJPanel extends javax.swing.JPanel {

    /**
     * Creates new form IranHormuzMap
     */
    private final JFXPanel jfxPanel = new JFXPanel(); // starts JavaFX runtime
    private WebView webView;
    private WebEngine engine;
    private final JButton closeBtn = new JButton("Close");

    public IranHormuzMapJPanel(JPanel userProcessContainer) {
        initComponents();
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                closeWebView();

                // IMPORTANT: remove this panel from the CardLayout container
                userProcessContainer.remove(IranHormuzMapJPanel.this);

                CardLayout layout = (CardLayout) userProcessContainer.getLayout();
                layout.previous(userProcessContainer);

                userProcessContainer.revalidate();
                userProcessContainer.repaint();
            }
        });
        // Make sure THIS panel is a BorderLayout container
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_NONE));
        setLayout(new BorderLayout());
        // Top bar with Close button (you can place it anywhere)
        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        top.add(closeBtn);
        add(top, BorderLayout.NORTH);
        add(jfxPanel, BorderLayout.CENTER);

        // Build WebView on the JavaFX Application Thread
        Platform.runLater(() -> {
            webView = new WebView();
            engine = webView.getEngine();
            // Optional: a couple of sensible defaults
            webView.setContextMenuEnabled(false);

            jfxPanel.setScene(new Scene(webView));

            String url
                    = "https://www.youtube.com/live/8SAo9jrrB_s?si=81whZ95aHnsb2zxr";
            engine.setUserAgent(
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                    + "AppleWebKit/537.36 (KHTML, like Gecko) "
                    + "Chrome/123.0.0.0 Safari/537.36"
            );
//            webView.getEngine().load(url);
            String html = "<html>"
                    + "<head>"
                    + "  <meta name=" + "\"referrer\"" + " content=" + "\"strict-origin-when-cross-origin\"" + " />"
                    + "</head>"
                    + "<body style='margin:0;padding:0;background-color:black;'>"
                    + "  <iframe width='100%' height='100%' "
                    + "    src='" + url + "?origin=https://www.youtube.com' "
                    + "    frameborder='0' "
                    + "    allow='autoplay; encrypted-media; picture-in-picture' "
                    + "    allowfullscreen "
                    + "    referrerpolicy='strict-origin-when-cross-origin'>"
                    + "  </iframe>"
                    + "</body>"
                    + "</html>";

            engine.loadContent(html);

        });

        revalidate();
        repaint();
    }

    private void closeWebView() {
        Platform.runLater(() -> {
            try {
                if (engine != null) {
                    engine.load(null);          // unload page (stops video)
                }
                if (jfxPanel != null) {
                    jfxPanel.setScene(null);    // detach scene
                }
            } catch (Throwable t) {
                t.printStackTrace();
            } finally {
                webView = null;
                engine = null;
            }
        });

        SwingUtilities.invokeLater(() -> {
            remove(jfxPanel);
            revalidate();
            repaint();

            closeBtn.setEnabled(false);
        });
    }

    /**
     * Optional helper if you want to navigate later
     */
    public void loadURL(String url) {
        Platform.runLater(() -> {
            if (webView != null) {
                webView.getEngine().load(url);
            }
        });
    }

    /**
     * Optional cleanup (call when you remove/dispose this panel)
     */
    public void disposeWebView() {
        Platform.runLater(() -> {
            if (webView != null) {
                webView.getEngine().load(null);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        jLabel1.setText("Iran Hormuz Map");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(277, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
