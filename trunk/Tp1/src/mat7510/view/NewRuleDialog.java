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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import mat7510.smartBuilding.model.SmartBuildingException;

/**
 *
 * @author sergio
 */
public class NewRuleDialog extends javax.swing.JDialog implements ItemListener,ListSelectionListener{

    class Event extends Object{
        private String eventID;
        private String driverID;
        public Event(String driverID,String eventID){
            this.eventID=eventID;
            this.driverID=driverID;
        }

        public String getDriverID(){ return driverID;}
        public String getEventID(){return eventID;}

        @Override
	public String toString() {
		return eventID+" ("+driverID+")";
	}

    }

    Mediator mediator;
    private javax.swing.JComboBox actionList;    
    private javax.swing.JCheckBox continuoCheck;
    private javax.swing.JCheckBox discontinuoCheck;
    private javax.swing.JCheckBox orderCheck;
    private javax.swing.JCheckBox unOrderCheck;
    private ListPanel driverList;
    private ListPanel eventList;
    private ListPanel addEventList;
    private JTextField nameRule;
    private ArrayList<Event> listEventsSelected;


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
        continuoCheck = new javax.swing.JCheckBox();
        discontinuoCheck = new javax.swing.JCheckBox();
        orderCheck = new javax.swing.JCheckBox();
        unOrderCheck = new javax.swing.JCheckBox();

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
        continuoCheck.addItemListener(this);
        continuoCheck.setActionCommand("continuo");

        discontinuoCheck.setText("Discontinuo");
        discontinuoCheck.addItemListener(this);
        discontinuoCheck.setActionCommand("discontinuo");

        orderCheck.setText("Con Orden");
        orderCheck.addItemListener(this);
        orderCheck.setActionCommand("order");

        unOrderCheck.setText("Sin Orden");
        unOrderCheck.addItemListener(this);
        unOrderCheck.setActionCommand("unorder");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nameRuleLabel)
                                    .addComponent(actionLabel)
                                    .addComponent(typeLabel)
                                    .addComponent(nameRule, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(continuoCheck)
                                        .addGap(18, 18, 18)
                                        .addComponent(discontinuoCheck))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(orderCheck)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(unOrderCheck)))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(driverList, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(driverLabel)))
                            .addComponent(actionList, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addGap(18, 18, 18)
                        .addComponent(nameRule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(typeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(continuoCheck)
                            .addComponent(discontinuoCheck))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(orderCheck)
                            .addComponent(unOrderCheck))
                        .addGap(37, 37, 37)
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

        Event event = new Event(driverID,eventID);
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

        if(!orderCheck.isSelected() && !unOrderCheck.isSelected() && !continuoCheck.isSelected() && !discontinuoCheck.isSelected()){
            JOptionPane.showMessageDialog(this ,"Debe seleccionar al menos un tipo","Warning", JOptionPane.WARNING_MESSAGE);
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
      
    }

    public void itemStateChanged(ItemEvent e) {
        JCheckBox jc = (JCheckBox) e.getItem();

        if(jc.getActionCommand().equals("order")){
            if(orderCheck.isSelected())
                unOrderCheck.setSelected(false);
        }else if(jc.getActionCommand().equals("unorder")){
            if(unOrderCheck.isSelected())
                orderCheck.setSelected(false);
        }else if(jc.getActionCommand().equals("continuo")){
            if(continuoCheck.isSelected())
                discontinuoCheck.setSelected(false);
        }else if(jc.getActionCommand().equals("discontinuo")){
            if(discontinuoCheck.isSelected())
                continuoCheck.setSelected(false);
        }

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