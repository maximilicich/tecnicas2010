package mat7510.playground.gui.alternate;

import java.net.URL;
import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends Frame
implements ActionListener, ItemListener
{
	Database db;
	java.awt.List driverList, stateList, actionList, eventList;
	TextArea query;
	Button Search, Quit;
	public MainWindow()
	{
		super("Smart Building Config Manager");
		setCloseClick();
		setGUI();

		db = new Database("sun.jdbc.odbc.JdbcOdbcDriver");
		db.Open("jdbc:odbc:groceries", null);

		String tnames[] = db.getTableNames();
		loadList(driverList, tnames);

		String queryText ="SELECT DISTINCTROW FoodName, StoreName, Price "+
		"FROM (Food INNER JOIN FoodPrice ON Food.FoodKey = FoodPrice.FoodKey) " +
		"INNER JOIN Stores ON FoodPrice.StoreKey = Stores.StoreKey "+
		"WHERE (((Food.FoodName)=\'Oranges\')) ORDER BY FoodPrice.Price;";

		query.setText(queryText);
	}
	//------------------------------------
	private void setGUI()
	{
		setBackground(Color.lightGray);
		setLayout(new BorderLayout());
		Panel pn = new Panel();
		add("North", pn);
		pn.setLayout(new GridLayout(1,4));
		pn.add(new Label("Drivers"));
		pn.add(new Label("Estados"));
		pn.add(new Label("Acciones"));
		pn.add(new Label("Eventos"));
		Panel pc = new Panel();
		add("Center", pc);
		pc.setLayout(new GridLayout(1,4));
		pc.add(driverList = new java.awt.List(15));
		pc.add(stateList = new java.awt.List(15));
		pc.add(actionList = new java.awt.List(15));
		pc.add(eventList = new java.awt.List(15));
		driverList.addItemListener(this);
		stateList.addItemListener(this);

		Panel ps = new Panel();
		add("South", ps);
		ps.add(query = new TextArea("", 3,40));
		addPanel(ps, Search = new Button("Run Query"));
		addPanel(ps, Quit = new Button("Quit"));
		Search.addActionListener(this);
		Quit.addActionListener(this);
		setBounds(100, 100, 500, 300);
		setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
		setVisible(true);
	}
	//------------------------------------
	private void addPanel(Panel ps, Component c)
	{
		Panel p = new Panel();
		ps.add(p);
		p.add(c);
	}
	//------------------------------------
	private void loadList(java.awt.List list, String[] s)
	{
		list.removeAll();
		for (int i=0; i< s.length; i++)
			list.add(s[i]);
	}
	//------------------------------------
	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();
		if (obj == Quit) 
			System.exit(0);
		if (obj == Search) 
			clickedSearch();
	}

	private void setCloseClick()
	{
		//create window listener to respond to window close click
		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});
	}

	//------------------------------------
	public void itemStateChanged(ItemEvent e)
	{
		Object obj = e.getSource();
		if (obj == driverList) 
			showColumns();
		if (obj == stateList)
			showData();
	}
	//------------------------------------
	private void showColumns()
	{
		String cnames[] = db.getColumnNames(driverList.getSelectedItem());
		loadList(stateList, cnames);
	}
	//------------------------------------
	private void showData()
	{
		String colname = stateList.getSelectedItem();
		String colval = db.getColumnValue(driverList.getSelectedItem(), colname);
		actionList.setVisible(false);
		actionList.removeAll();
		actionList.setVisible(true);

		while (colval.length()>0) 
		{
			actionList.add(colval);
			colval = db.getNextValue(stateList.getSelectedItem());
		}
	}
	//------------------------------------
	private void clickedSearch()
	{
		resultSet rs = db.Execute(query.getText());
		String cnames[] = rs.getMetaData();
		stateList.removeAll();
		queryDialog q = new queryDialog(this, rs);
		q.show();
	}
	//------------------------------------
	static public void main(String argv[])
	{
		new MainWindow();
	}
}

