/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewRuleDialog.java
 *
 * Created on 22/11/2010, 15:00:40
 */

package mat7510.view;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import mat7510.smartBuilding.model.SmartBuildingException;

/**
 *
 * @author sergio
 */
public class NewRuleDialog extends javax.swing.JDialog implements ListSelectionListener{

    Mediator mediator;
    private javax.swing.JComboBox actionList;    
    private JRadioButton continuoCheck;
    private JRadioButton discontinuoCheck;
    private JRadioButton orderCheck;
    private JRadioButton unOrderCheck;
    private ListPanel driverList;
    private ListPanel eventList;
    private ListPanel addEventList;
    private JTextField nameRule;
    private ArrayList<EventItem> listEventsSelected;
    private javax.swing.JTextField descriptionLabel;


    public NewRuleDialog(java.awt.Frame parent, Mediator med) {
        super(parent, true);
        try {
            mediator = med;
            listEventsSelected = new ArrayList();
            initComponents();
            ArrayList<String> drivers = mediator.getDriversId();
            driverList.addAll(drivers.iterator());
            System.out.println("adsfdfsdfds");
        } catch (SmartBuildingException ex) {
            System.out.println("Error");
        }

        setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        javax.swing.JLabel nameRuleLabel = new javax.swing.JLabel();
        nameRule = new javax.swing.JTextField();
        javax.swing.JLabel typeLabel = new javax.swing.JLabel();
        actionList = new javax.swing.JComboBox();
        javax.swing.JLabel actionLabel = new javax.swing.JLabel();
        javax.swing.JLabel driverLabel = new javax.swing.JLabel();
        javax.swing.JLabel eventLabel = new javax.swing.JLabel();
        javax.swing.JLabel eventAddLabel = new javax.swing.JLabel();
        eventList = new ListPanel();
        addEventList = new ListPanel();
        driverList = new ListPanel();
        javax.swing.JButton addBtn = new javax.swing.JButton();
        javax.swing.JButton removeBtn = new javax.swing.JButton();
        javax.swing.JButton cancelBtn = new javax.swing.JButton();
        javax.swing.JButton acceptBtn = new javax.swing.JButton();
        
        continuoCheck = new JRadioButton();
        discontinuoCheck = new JRadioButton();;
        orderCheck = new JRadioButton();
        unOrderCheck = new JRadioButton();

        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(continuoCheck);
        bg1.add(discontinuoCheck);

        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(orderCheck);
        bg2.add(unOrderCheck);

        JLabel descriptionTitleLabel = new JLabel();
        descriptionLabel = new javax.swing.JTextField();

        discontinuoCheck.setSelected(true);
        unOrderCheck.setSelected(true);
        
        driverList.addListSelectionListener(this);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nameRuleLabel.setText("Nombre:");

        typeLabel.setText("Tipo:");
        try {
            ArrayList<String> actions = mediator.getAction();
            actionList.setModel(new javax.swing.DefaultComboBoxModel(actions.toArray()));
            actionList.setFont(new Font("Dialog",Font.PLAIN,10));
            actionList.setSelectedIndex(-1);
        } catch (SmartBuildingException ex) {}


        actionLabel.setText("Accion:");
        driverLabel.setText("Driver:");
        eventLabel.setText("Eventos:");
        eventAddLabel.setText("Eventos a Agregar:");
        addBtn.setText("Agregar");
        descriptionTitleLabel.setText("Descripción:");
        
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        removeBtn.setText("Eliminar");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });

        cancelBtn.setText("Cancelar");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        acceptBtn.setText("Aceptar");
        acceptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptBtnActionPerformed(evt);
            }
        });

        continuoCheck.setText("Continuo");
        continuoCheck.setActionCommand("continuo");

        discontinuoCheck.setText("Discontinuo");
        discontinuoCheck.setActionCommand("discontinuo");

        orderCheck.setText("Con Orden");
        orderCheck.setActionCommand("order");

        unOrderCheck.setText("Sin Orden");
        unOrderCheck.setActionCommand("unorder");

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(nameRuleLabel)
                                .addComponent(actionLabel)
                                .addComponent(descriptionTitleLabel)
                                .addComponent(typeLabel)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(continuoCheck)
                                    .addGap(18, 18, 18)
                                    .addComponent(discontinuoCheck))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(orderCheck)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(unOrderCheck))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(descriptionLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nameRule, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)))
                            .addGap(28, 28, 28)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(driverList, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(driverLabel)))
                        .addComponent(actionList, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cancelBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(eventLabel)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(eventList, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(addBtn)
                                .addGap(55, 55, 55)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(addEventList, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(eventAddLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(removeBtn))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(acceptBtn)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameRuleLabel)
                    .addComponent(driverLabel)
                    .addComponent(eventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventAddLabel))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(nameRule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descriptionTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(typeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(continuoCheck)
                            .addComponent(discontinuoCheck))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(orderCheck)
                            .addComponent(unOrderCheck))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(actionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(actionList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(driverList, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eventList, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addEventList, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addBtn)
                            .addComponent(removeBtn))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(acceptBtn)
                            .addComponent(cancelBtn))))
                .addGap(24, 24, 24))
        );

        pack();
    }

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String driverID = (String) driverList.getSelectedValue();
        String eventID = (String) eventList.getSelectedValue();

        if(driverID==null || eventID==null){
            JOptionPane.showMessageDialog(this ,"Driver o evento no seleccionado","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        EventItem event = new EventItem(driverID,eventID);
        addEventList.addItem(event);
        listEventsSelected.add(event);
    }

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {
        int index = addEventList.getSelectedIndex();
        if(index < 0){
            JOptionPane.showMessageDialog(this ,"Evento no seleccionado","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        addEventList.removeItem(index);
        listEventsSelected.remove(index);
    }

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    private void acceptBtnActionPerformed(java.awt.event.ActionEvent evt) {

        if(nameRule.getText().equals("")){
            JOptionPane.showMessageDialog(this ,"Debe agregarle un nombre a la regla","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(actionList.getSelectedIndex()<0){
            JOptionPane.showMessageDialog(this ,"Debe asignarle una accion","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(listEventsSelected.isEmpty()){
            JOptionPane.showMessageDialog(this ,"Debe asignarle al menos un evento","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }


        mediator.addNewRule(this);
      
    }

    public String getNameRule(){
        return nameRule.getText();
    }

    public String getDescriptionRule(){
        return nameRule.getText();
    }

    public String getNameAction(){
        return (String) actionList.getSelectedItem();
    }

    public boolean isContinuos(){
        return continuoCheck.isSelected();
    }

    public boolean isOrder(){
        return orderCheck.isSelected();
    }

    public ArrayList<EventItem> getEvents(){
        return listEventsSelected;
    }

    public void valueChanged(ListSelectionEvent e) {
          if(!e.getValueIsAdjusting()){

               JList theList = (JList)e.getSource();
               if (!theList.isSelectionEmpty()){
                try {
                    eventList.clear();
                    ArrayList<String> events = mediator.getEventsForDriverID((String) theList.getSelectedValue());
                    eventList.addAll(events.iterator());
                } catch (SmartBuildingException ex) {}
               }

          }
    }

}
