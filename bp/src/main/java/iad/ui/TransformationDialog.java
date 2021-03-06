/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iad.ui;

import iad.network.MultiLayerNetwork;
import iad.network.exceptions.CannotCreateNetworkException;
import iad.network.factory.MultiLayerNetworkFactory;
import iad.network.input.InputRow;
import iad.network.strategy.bp.BackPropagationStrategy;
import iad.network.training.ThresholdEpochNetworkTrainer;
import iad.ui.exceptions.EmptyInputFieldException;
import iad.ui.plot.PlotGenerator;
import iad.ui.plot.PlotNamer;
import java.awt.Frame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author PiotrGrzelak
 */
public class TransformationDialog extends javax.swing.JDialog {

    private PlotGenerator generator;

    private MultiLayerNetwork network;

    private int hiddenNeurons;

    /**
     * Creates new form TransformationDialog
     */
    public TransformationDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        generator = new PlotGenerator();
        initComponents();
        
        networkCreationParamsPanel.fixNetworkInputsField(4);
        networkCreationParamsPanel.fixNetworkOutputField(4);
        learningParamsInputPanel.setDefaultLearningRate(0.4);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        headerSeparator = new javax.swing.JSeparator();
        biasPanel = new javax.swing.JPanel();
        biasCheckbox = new javax.swing.JCheckBox();
        createNetworkButton = new javax.swing.JButton();
        downSeparator = new javax.swing.JSeparator();
        buttonPanel = new javax.swing.JPanel();
        startExperimentButton = new javax.swing.JButton();
        networkCreationParamsPanel = new iad.ui.NetworkCreationParamsPanel();
        createNetworkSeparator = new javax.swing.JSeparator();
        learningParamsInputPanel = new iad.ui.LearningParamsInputPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Transformacja");

        headerLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        headerLabel.setText("Transformacja");
        headerPanel.add(headerLabel);

        biasCheckbox.setText("Sieć z biasem");
        biasPanel.add(biasCheckbox);

        createNetworkButton.setText("Stwórz sieć");
        createNetworkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNetworkButtonActionPerformed(evt);
            }
        });
        biasPanel.add(createNetworkButton);

        startExperimentButton.setText("Start");
        startExperimentButton.setEnabled(false);
        startExperimentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startExperimentButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(startExperimentButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(headerSeparator)
            .addComponent(biasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(downSeparator)
            .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(networkCreationParamsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(createNetworkSeparator)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(learningParamsInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(headerSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(networkCreationParamsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(biasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createNetworkSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(learningParamsInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(downSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startExperimentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startExperimentButtonActionPerformed
        if (network == null) {
            return;
        }

        try {
            double learningRate = learningParamsInputPanel.getLearningRate();
            double momentumFactor = learningParamsInputPanel.getMomentumFactor();
            int maximumNumberOfEpochs = learningParamsInputPanel.getMaximumEpochNumber();
            double errorThreshold = learningParamsInputPanel.getErrorThreshold();

            List<InputRow> trainingData = new ArrayList<>();

            int inputs = 4;
            for (int i = 0; i < inputs; ++i) {
                double[] input = new double[inputs];
                input[i] = 1;

                trainingData.add(new InputRow(input, input));
            }

            ThresholdEpochNetworkTrainer trainer
                    = new ThresholdEpochNetworkTrainer(maximumNumberOfEpochs, errorThreshold, learningRate, momentumFactor);
            List<Double> meanSquaredError = trainer.trainNetwork(network, trainingData);

            PlotNamer plotNamer = new PlotNamer();
            plotNamer.setBaseName("error").setEpochs(meanSquaredError.size())
                    .setLearningRate(learningRate).setMomentumFactor(momentumFactor)
                    .setHiddenNeurons(hiddenNeurons);

            generator.generateErrorChart(meanSquaredError, plotNamer.generateName());
            System.out.println("Number of epochs: " + meanSquaredError.size());

            List<double[]> networkResults = new ArrayList<>(trainingData.size());
            trainingData.stream().forEach(
                    (InputRow row) -> networkResults.add(network.runNetwork(row.getValues()))
            );
            
            ResultsDialog results = new ResultsDialog((Frame) this.getParent(), trainingData, networkResults);
            results.setVisible(true);
        } catch (IOException | EmptyInputFieldException ex) {
            Logger.getLogger(TransformationDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_startExperimentButtonActionPerformed

    private void createNetworkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNetworkButtonActionPerformed
        try {
            int inputNeurons = networkCreationParamsPanel.getNetworkInputsNum();
            int outputNeurons = networkCreationParamsPanel.getNetworkOutputsNum();
            hiddenNeurons = networkCreationParamsPanel.getHiddenNeuronsNum();

            boolean useBias = biasCheckbox.isSelected();

            BackPropagationStrategy strategy = BackPropagationStrategy.getInstance();
            MultiLayerNetworkFactory factory = new MultiLayerNetworkFactory(
                    new int[]{inputNeurons, hiddenNeurons, outputNeurons}, strategy, useBias);
            network = factory.createNetwork();

            startExperimentButton.setEnabled(true);
            
            JOptionPane.showMessageDialog(this, "Tworzenie sieci zakończone sukcesem", "Sukces",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (EmptyInputFieldException | CannotCreateNetworkException ex) {
            Logger.getLogger(TransformationDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_createNetworkButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox biasCheckbox;
    private javax.swing.JPanel biasPanel;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton createNetworkButton;
    private javax.swing.JSeparator createNetworkSeparator;
    private javax.swing.JSeparator downSeparator;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JSeparator headerSeparator;
    private iad.ui.LearningParamsInputPanel learningParamsInputPanel;
    private iad.ui.NetworkCreationParamsPanel networkCreationParamsPanel;
    private javax.swing.JButton startExperimentButton;
    // End of variables declaration//GEN-END:variables
}
