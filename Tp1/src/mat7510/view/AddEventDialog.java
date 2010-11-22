package mat7510.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author sergio
 */
public class AddEventDialog extends JDialog implements ActionListener{

    private JComboBox actions;
    private JCheckBox continuosBtn;
    private JCheckBox discontinuosBtn;
    private JTextField nameEvent;
    private JCheckBox ordenBtn;
    private JCheckBox unOrderedBtn;
    private ListPanel listPanel;
    private ListPanel addPanel;
    private Mediator mediator;


    public AddEventDialog(JFrame parent, Mediator med) {
        super(parent, true);
        mediator=med;
        initComponents();
        this.setBounds(400, 200, 585, 420);
        setLocationRelativeTo(parent);        
        this.setVisible(true);
    }


    private void initComponents() {

        javax.swing.JButton cancelBtn = new javax.swing.JButton();
        javax.swing.JButton acceptBtn = new javax.swing.JButton();
        ordenBtn = new javax.swing.JCheckBox();
        unOrderedBtn = new javax.swing.JCheckBox();
        continuosBtn = new javax.swing.JCheckBox();
        discontinuosBtn = new javax.swing.JCheckBox();
        actions = new javax.swing.JComboBox();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        nameEvent = new javax.swing.JTextField();

        listPanel = new ListPanel();
        addPanel = new ListPanel();
        javax.swing.JButton addBtn = new javax.swing.JButton();
        javax.swing.JButton removeBtn = new javax.swing.JButton();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cancelBtn.setText("Cancelar");
        cancelBtn.addActionListener(this);
        cancelBtn.setActionCommand("cancel");

        acceptBtn.setText("Aceptar");
        acceptBtn.addActionListener(this);
        acceptBtn.setActionCommand("accept");

        ordenBtn.setText("Con Orden");

        unOrderedBtn.setText("Sin Orden");

        continuosBtn.setText("Continuo");

        discontinuosBtn.setText("Discontinuo");

        actions.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Tipo:");

        jLabel2.setText("Acción:");

        jLabel3.setText("Nombre:");

        addBtn.setText("Agregar");
        addBtn.setActionCommand("add");
        addBtn.addActionListener(this);

        removeBtn.setText("Eliminar");
        removeBtn.setActionCommand("remove");
        removeBtn.addActionListener(this);

        jLabel4.setText("Eventos:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(cancelBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameEvent, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(ordenBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(unOrderedBtn))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(continuosBtn)
                                .addGap(18, 18, 18)
                                .addComponent(discontinuosBtn))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(actions, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acceptBtn)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(listPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(removeBtn))
                                    .addComponent(addPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(addBtn)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(listPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addBtn)
                            .addComponent(removeBtn)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ordenBtn)
                            .addComponent(unOrderedBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(continuosBtn)
                            .addComponent(discontinuosBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(actions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn)
                    .addComponent(acceptBtn))
                .addGap(23, 23, 23))
        );

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="accept"){

        }else if(e.getActionCommand()=="cancel"){
            dispose();
        }else if(e.getActionCommand()=="add"){
           // int index=listPanel.getSelectedIndex();
           // addPanel.addItem();

        }else if(e.getActionCommand()=="remove"){
              int index=addPanel.getSelectedIndex();
              if(index>=0)
                addPanel.remove(index);
        }

        mediator.addEvent2(e.getActionCommand());
    }


}
