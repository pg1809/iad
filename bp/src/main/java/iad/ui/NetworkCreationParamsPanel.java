/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iad.ui;

import iad.ui.exceptions.EmptyInputFieldException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author PiotrGrzelak
 */
public class NetworkCreationParamsPanel extends javax.swing.JPanel {

    /**
     * Creates new form NetworkCreationParamsPanel
     */
    public NetworkCreationParamsPanel() {
        initComponents();
    }

    public void fixNetworkInputsField(int value) {
        inputNeuronsInput.setText(String.valueOf(value));
        inputNeuronsInput.setEnabled(false);
    }

    public void fixNetworkOutputField(int value) {
        outputNeuronsInput.setText(String.valueOf(value));
        outputNeuronsInput.setEnabled(false);
    }

    public int getNetworkInputsNum() throws EmptyInputFieldException {
        String inputsNumParam = inputNeuronsInput.getText();
        if (StringUtils.isEmpty(inputsNumParam)) {
            throw new EmptyInputFieldException(inputNeuronsLabel.getText());
        }

        return Integer.parseInt(inputsNumParam);
    }

    public int getNetworkOutputsNum() throws EmptyInputFieldException {
        String outputsNumParam = outputNeuronsInput.getText();
        if (StringUtils.isEmpty(outputsNumParam)) {
            throw new EmptyInputFieldException(outputNeuronsLabel.getText());
        }

        return Integer.parseInt(outputsNumParam);
    }

    public int getHiddenNeuronsNum() throws EmptyInputFieldException {
        String hiddenNumParam = hiddenNeuronsInput.getText();
        if (StringUtils.isEmpty(hiddenNumParam)) {
            throw new EmptyInputFieldException(hiddenNeuronsLabel.getText());
        }

        return Integer.parseInt(hiddenNumParam);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputNeuronsLabel = new javax.swing.JLabel();
        hiddenNeuronsLabel = new javax.swing.JLabel();
        outputNeuronsLabel = new javax.swing.JLabel();
        inputNeuronsInput = new javax.swing.JTextField();
        hiddenNeuronsInput = new javax.swing.JTextField();
        outputNeuronsInput = new javax.swing.JTextField();

        inputNeuronsLabel.setText("Liczba neuronów wejściowych: ");

        hiddenNeuronsLabel.setText("Liczba neuronów ukrytych:");

        outputNeuronsLabel.setText("Liczba neuronów wyjściowych:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(outputNeuronsLabel)
                        .addGap(18, 18, 18)
                        .addComponent(outputNeuronsInput))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputNeuronsLabel)
                            .addComponent(hiddenNeuronsLabel))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputNeuronsInput, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hiddenNeuronsInput, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputNeuronsLabel)
                    .addComponent(inputNeuronsInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hiddenNeuronsLabel)
                    .addComponent(hiddenNeuronsInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outputNeuronsLabel)
                    .addComponent(outputNeuronsInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField hiddenNeuronsInput;
    private javax.swing.JLabel hiddenNeuronsLabel;
    private javax.swing.JTextField inputNeuronsInput;
    private javax.swing.JLabel inputNeuronsLabel;
    private javax.swing.JTextField outputNeuronsInput;
    private javax.swing.JLabel outputNeuronsLabel;
    // End of variables declaration//GEN-END:variables
}
