/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iad.ui;

import iad.network.input.InputRow;
import java.awt.Frame;
import java.util.List;

/**
 *
 * @author PiotrGrzelak
 */
public class ResultsDialog extends javax.swing.JDialog {

    /**
     * Creates new form ResultsDialog
     */
    public ResultsDialog(Frame parent, List<InputRow> input, List<double[]> networkOutputs) {
        super(parent, true);
        initComponents();
        
        StringBuilder resultsStringBuilder = new StringBuilder();
        for (int i = 0; i < input.size(); ++i) {
            resultsStringBuilder.append("Input: ")
                        .append(FormatUtils.doubleArrayToString(input.get(i).getValues())).append("\n")
                        .append("Network output: ")
                        .append(FormatUtils.doubleArrayToString(networkOutputs.get(i))).append("\n")
                        .append("Expected output: ")
                        .append(FormatUtils.doubleArrayToString(input.get(i).getExpectedOutput())).append("\n")
                        .append("\n");
        }
        
        resultsTextArea.setText(resultsStringBuilder.toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resultsPanel = new javax.swing.JScrollPane();
        resultsTextArea = new javax.swing.JTextArea();
        headerPanel = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Wyniki");

        resultsTextArea.setEditable(false);
        resultsTextArea.setColumns(20);
        resultsTextArea.setRows(5);
        resultsPanel.setViewportView(resultsTextArea);

        headerLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        headerLabel.setText("Wyniki");
        headerPanel.add(headerLabel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel headerLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JScrollPane resultsPanel;
    private javax.swing.JTextArea resultsTextArea;
    // End of variables declaration//GEN-END:variables
}