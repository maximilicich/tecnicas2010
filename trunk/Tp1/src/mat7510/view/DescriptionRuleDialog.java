/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DescriptionRuleDialog.java
 *
 * Created on 23/11/2010, 03:47:50
 */

package mat7510.view;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author sergio
 */
@SuppressWarnings("serial")
public class DescriptionRuleDialog extends javax.swing.JDialog  implements ListSelectionListener{

    private javax.swing.JLabel actionLabel;
    private javax.swing.JLabel enabledLabel;
    private ListPanel eventsList;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JLabel typeLabel;

    /** Creates new form DescriptionRuleDialog */
    public DescriptionRuleDialog(java.awt.Frame parent,String ruleID,String actionName, String types, ArrayList<String> events, boolean isEnabled,String description){
        super(parent, true);
        initComponents(ruleID,actionName,types,events,isEnabled,description);
        setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    private void initComponents(String ruleID,String actionName, String types, ArrayList<String> events, boolean isEnabled,String description) {

        javax.swing.JLabel titleNameLabel = new javax.swing.JLabel();
        javax.swing.JLabel descriptionTitleLabel = new javax.swing.JLabel();
        javax.swing.JLabel actionTitleLabel = new javax.swing.JLabel();
        javax.swing.JLabel typeTitleLabel = new javax.swing.JLabel();
        javax.swing.JLabel eventTitleLabel = new javax.swing.JLabel();
        javax.swing.JLabel enabledTitleLabel = new javax.swing.JLabel();
        eventsList = new ListPanel();;
        javax.swing.JButton acceptBtn = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        actionLabel = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();
        enabledLabel = new javax.swing.JLabel();
        eventsList.addListSelectionListener(this);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Descripción");

        titleNameLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        titleNameLabel.setText("Nombre:");

        descriptionTitleLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        descriptionTitleLabel.setText("Descripción:");

        actionTitleLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        actionTitleLabel.setText("Acción:");

        typeTitleLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        typeTitleLabel.setText("Tipo:");

        eventTitleLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        eventTitleLabel.setText("Eventos:");

        enabledTitleLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        enabledTitleLabel.setText("Habilitado:");

        nameLabel.setText(ruleID);
        nameLabel.setFont(new Font("DejaVu Sans",Font.PLAIN,12));
        actionLabel.setText(actionName);
        actionLabel.setFont(new Font("DejaVu Sans",Font.PLAIN,12));
        typeLabel.setText(types);
        typeLabel.setFont(new Font("DejaVu Sans",Font.PLAIN,12));

        if(description.length()>40)
            descriptionLabel.setText(description.substring(0, 40)+"...");
        else descriptionLabel.setText(description);
        descriptionLabel.setFont(new Font("DejaVu Sans",Font.PLAIN,12));
        descriptionLabel.setSize(282,40);
        enabledLabel.setFont(new Font("DejaVu Sans",Font.PLAIN,12));

        eventsList.addAll(events.iterator());


        if(isEnabled)
            enabledLabel.setText("Si");
        else enabledLabel.setText("No");

        acceptBtn.setText("Aceptar");
        acceptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptBtnActionPerformed(evt);
            }
        });

        acceptBtn.setText("Aceptar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(descriptionTitleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                            .addComponent(eventTitleLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(titleNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(enabledTitleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enabledLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
                            .addComponent(eventsList, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(acceptBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(typeTitleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(typeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(actionTitleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(actionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descriptionTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(actionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(actionTitleLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(typeTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(typeLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(enabledLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(enabledTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(eventTitleLabel)
                .addGap(18, 18, 18)
                .addComponent(eventsList, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(acceptBtn)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }

    private void acceptBtnActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    public void valueChanged(ListSelectionEvent e) {
        eventsList.clearSelection();
    }

}
