/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iad.ui;

import iad.network.MultiLayerNetwork;
import iad.network.exceptions.CannotCreateNetworkException;
import iad.network.factory.MultiLayerNetworkFactory;
import iad.network.input.ClassificationDataProvider;
import iad.network.input.InputRow;
import iad.network.output.ClassificationMatrixGenerator;
import iad.network.strategy.bp.BackPropagationStrategy;
import iad.network.training.ThresholdEpochNetworkTrainer;
import iad.ui.exceptions.EmptyInputFieldException;
import iad.ui.exceptions.IncorrectParamsStringException;
import iad.ui.plot.PlotGenerator;
import iad.ui.plot.PlotNamer;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author PiotrGrzelak
 */
public class ClassificationDialog extends javax.swing.JDialog {

    private final PlotGenerator plotGenerator;

    private final ClassificationMatrixGenerator matrixGenerator;

    private MultiLayerNetwork network;

    private int inputNeurons;

    private int outputNeurons;

    private int hiddenNeurons;

    /**
     * Creates new form ClassificationDialog
     */
    public ClassificationDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        plotGenerator = new PlotGenerator();
        matrixGenerator = new ClassificationMatrixGenerator();

        networkCreationParamsPanel.fixNetworkOutputField(3);
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
        startButtonPanel = new javax.swing.JPanel();
        trainNetworkButton = new javax.swing.JButton();
        testNetworkButton = new javax.swing.JButton();
        downSeparator = new javax.swing.JSeparator();
        networkCreationParamsPanel = new iad.ui.NetworkCreationParamsPanel();
        createNetworkPanel = new javax.swing.JPanel();
        createNetworkButton = new javax.swing.JButton();
        createNetworkSeparator = new javax.swing.JSeparator();
        learningParamsInputPanel = new iad.ui.LearningParamsInputPanel();
        jLabel1 = new javax.swing.JLabel();
        objectParamsTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Klasyfikacja");

        headerLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        headerLabel.setText("Klasyfikacja");
        headerPanel.add(headerLabel);

        trainNetworkButton.setText("Trenuj sieć");
        trainNetworkButton.setEnabled(false);
        trainNetworkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainNetworkButtonActionPerformed(evt);
            }
        });
        startButtonPanel.add(trainNetworkButton);

        testNetworkButton.setText("Testuj sieć");
        testNetworkButton.setEnabled(false);
        testNetworkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testNetworkButtonActionPerformed(evt);
            }
        });
        startButtonPanel.add(testNetworkButton);

        createNetworkButton.setText("Stwórz sieć");
        createNetworkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNetworkButtonActionPerformed(evt);
            }
        });
        createNetworkPanel.add(createNetworkButton);

        jLabel1.setText("Wybrane cechy obiektu do analizy:");

        objectParamsTextField.setMinimumSize(new java.awt.Dimension(100, 25));
        objectParamsTextField.setPreferredSize(new java.awt.Dimension(100, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(headerSeparator)
            .addComponent(startButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(downSeparator)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(learningParamsInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(networkCreationParamsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createNetworkPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createNetworkSeparator))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(objectParamsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(headerSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(networkCreationParamsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createNetworkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createNetworkSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(learningParamsInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(objectParamsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(downSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void trainNetworkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainNetworkButtonActionPerformed
        if (network == null) {
            return;
        }

        try {
            JFileChooser trainingDataFileChooser = new JFileChooser(".");
            int result = trainingDataFileChooser.showOpenDialog(this);

            if (result == JFileChooser.CANCEL_OPTION) {
                return;
            }

            File chosenFile = trainingDataFileChooser.getSelectedFile();

            int maxEpochNum = learningParamsInputPanel.getMaximumEpochNumber();
            double learningRate = learningParamsInputPanel.getLearningRate();
            double momentumFactor = learningParamsInputPanel.getMomentumFactor();
            double error = learningParamsInputPanel.getErrorThreshold();

            int[] classificationInputs = readClassificationInputsNumbers();

            ClassificationDataProvider provider = new ClassificationDataProvider(
                    chosenFile, outputNeurons, " ", classificationInputs);

            List<InputRow> trainingData = provider.provideAllRows();

            ThresholdEpochNetworkTrainer trainer
                    = new ThresholdEpochNetworkTrainer(maxEpochNum, error, learningRate, momentumFactor);
            List<Double> meanSquaredError = trainer.trainNetwork(network, trainingData);

            PlotNamer plotNamer = new PlotNamer();
            plotNamer.setBaseName("error").setEpochs(meanSquaredError.size())
                    .setLearningRate(learningRate).setMomentumFactor(momentumFactor);

            plotGenerator.generateErrorChart(meanSquaredError, plotNamer.generateName());

            List<double[]> networkResults = new ArrayList<>(trainingData.size());
            trainingData.stream().forEach(
                    (InputRow row) -> networkResults.add(network.runNetwork(row.getValues()))
            );

            List<double[]> expectedResults = trainingData.stream()
                    .map((InputRow row) -> row.getExpectedOutput()).collect(Collectors.toList());

            int[][] classificationMatrix
                    = matrixGenerator.generateClassificationMatrix(expectedResults, networkResults, outputNeurons);
            matrixGenerator.saveMatrixToFile("classifMatrixTraining.txt", classificationMatrix);

            ClassificationMatrixDialog matrixDialog = new ClassificationMatrixDialog((Frame) this.getParent(), classificationMatrix);
            matrixDialog.setVisible(true);
        } catch (EmptyInputFieldException | IncorrectParamsStringException | IOException ex) {
            Logger.getLogger(ClassificationDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_trainNetworkButtonActionPerformed

    private void testNetworkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testNetworkButtonActionPerformed
        try {
            if (network == null) {
                return;
            }

            JFileChooser trainingDataFileChooser = new JFileChooser(".");
            int result = trainingDataFileChooser.showOpenDialog(this);
            if (result == JFileChooser.CANCEL_OPTION) {
                return;
            }
            File chosenFile = trainingDataFileChooser.getSelectedFile();

            int[] classificationInputs = readClassificationInputsNumbers();

            ClassificationDataProvider provider = new ClassificationDataProvider(
                    chosenFile, outputNeurons, " ", classificationInputs);
            List<InputRow> data = provider.provideAllRows();

            List<double[]> networkResults = new ArrayList<>(data.size());
            data.stream().forEach((InputRow row) -> networkResults.add(network.runNetwork(row.getValues())));
            List<double[]> expectedResults = data.stream()
                    .map((InputRow row) -> row.getExpectedOutput()).collect(Collectors.toList());

            int[][] classificationMatrix
                    = matrixGenerator.generateClassificationMatrix(expectedResults, networkResults, outputNeurons);
            matrixGenerator.saveMatrixToFile("classifMatrixTest.txt", classificationMatrix);

            ClassificationMatrixDialog matrixDialog = new ClassificationMatrixDialog((Frame) this.getParent(), classificationMatrix);
            matrixDialog.setVisible(true);
        } catch (IOException | IncorrectParamsStringException ex) {
            Logger.getLogger(ClassificationDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_testNetworkButtonActionPerformed

    private void createNetworkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNetworkButtonActionPerformed
        try {
            inputNeurons = networkCreationParamsPanel.getNetworkInputsNum();
            outputNeurons = networkCreationParamsPanel.getNetworkOutputsNum();
            hiddenNeurons = networkCreationParamsPanel.getHiddenNeuronsNum();

            BackPropagationStrategy strategy = BackPropagationStrategy.getInstance();
            MultiLayerNetworkFactory factory = new MultiLayerNetworkFactory(
                    new int[]{inputNeurons, hiddenNeurons, outputNeurons}, strategy, true);
            network = factory.createNetwork();

            JOptionPane.showMessageDialog(this, "Tworzenie sieci zakończone sukcesem", "Sukces",
                    JOptionPane.INFORMATION_MESSAGE);

            trainNetworkButton.setEnabled(true);
            testNetworkButton.setEnabled(true);
        } catch (EmptyInputFieldException | CannotCreateNetworkException ex) {
            Logger.getLogger(ClassificationDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_createNetworkButtonActionPerformed

    private int[] readClassificationInputsNumbers() throws IncorrectParamsStringException {
        String classifiedObjectParamsText = objectParamsTextField.getText();
        int networkInputsNum = network.getInputLayer().getNeurons().size();
        if (!classifiedObjectParamsText.matches("([0-3]\\s){" + (networkInputsNum - 1) + "}[0-3]")) {
            throw new IncorrectParamsStringException();
        }
        String[] objectParamsStringArray = classifiedObjectParamsText.split(" ");
        int[] paramsForClassification = new int[networkInputsNum];
        for (int i = 0; i < objectParamsStringArray.length; ++i) {
            paramsForClassification[i] = Integer.parseInt(objectParamsStringArray[i]);
        }

        return paramsForClassification;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createNetworkButton;
    private javax.swing.JPanel createNetworkPanel;
    private javax.swing.JSeparator createNetworkSeparator;
    private javax.swing.JSeparator downSeparator;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JSeparator headerSeparator;
    private javax.swing.JLabel jLabel1;
    private iad.ui.LearningParamsInputPanel learningParamsInputPanel;
    private iad.ui.NetworkCreationParamsPanel networkCreationParamsPanel;
    private javax.swing.JTextField objectParamsTextField;
    private javax.swing.JPanel startButtonPanel;
    private javax.swing.JButton testNetworkButton;
    private javax.swing.JButton trainNetworkButton;
    // End of variables declaration//GEN-END:variables
}
