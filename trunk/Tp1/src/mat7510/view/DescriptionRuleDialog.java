/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DescriptionRuleDialog.java
 *
 * Created on 22/11/2010, 21:59:58
 */

package mat7510.view;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class DescriptionRuleDialog extends javax.swing.JDialog {
    private Mediator mediator;
    private javax.swing.JLabel actionLabel;
    private ListPanel eventsList;
    private javax.swing.JLabel nameLabel;
    private ListPanel typeList;


    public DescriptionRuleDialog(java.awt.Frame parent,String ruleID,String actionName, ArrayList<String> types, ArrayList<String> events){
        super(parent, true);
        initComponents(ruleID,actionName,types,events);
        setLocationRelativeTo(parent);
        this.setVisible(true);
    }


    @SuppressWarnings("unchecked")
    private void initComponents(String ruleID,String actionName, ArrayList<String> types, ArrayList<String> events) {

        javax.swing.JLabel titleNameLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        javax.swing.JLabel titleActionLabel = new javax.swing.JLabel();
        actionLabel = new javax.swing.JLabel();
        javax.swing.JLabel titleTypeLabel = new javax.swing.JLabel();
        javax.swing.JLabel titleEventLabel = new javax.swing.JLabel();
        typeList = new ListPanel();
        eventsList = new ListPanel();
        javax.swing.JButton acceptbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Descripción");

        titleNameLabel.setText("Nombre:");
        titleActionLabel.setText("Acción:");
        titleTypeLabel.setText("Tipo:");
        titleEventLabel.setText("Events:");

        nameLabel.setText(ruleID);
        actionLabel.setText(actionName);
        typeList.addAll(types.iterator());
        eventsList.addAll(events.iterator());

        typeList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                typeListValueChanged(evt);
            }
        });


        eventsList.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                eventsListVetoableChange(evt);
            }
        });

        acceptbtn.setText("Aceptar");
        acceptbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(titleActionLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(titleNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(titleTypeLabel)
                                    .addComponent(typeList, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(titleEventLabel)
                                    .addComponent(eventsList, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(acceptbtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleNameLabel)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(actionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleActionLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleTypeLabel)
                    .addComponent(titleEventLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(typeList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventsList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acceptbtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void acceptbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptbtnActionPerformed
        dispose();
    }

    private void typeListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_typeListValueChanged
        typeList.clearSelection();
    }

    private void eventsListVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_eventsListVetoableChange
        eventsList.clearSelection();
    }

}
