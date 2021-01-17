package com.pharmacy.nam;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTable;

import com.SQLiteConnect.myPro.DBconnection;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class ItemManage {

	//for use it out of class
	private JFrame frmItemManage;
	private JTable table;
	public static JTextField txtItemName;
	public static JTextField txtPrice;
	public static JTextField txtQantity;
	public static JTextField txtExDate;
	public static JComboBox<String> cbCountry;
	public static int row = -1;
	public static JPanel panel, panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemManage window = new ItemManage();
					//this condition for check if there is user logged, else will open Login page automatically
					if (users.Get_userId() > 0)
						window.frmItemManage.setVisible(true);
					else {JOptionPane.showMessageDialog(null, "Can not use this page, must be Login at first !!!");
					Login.main(null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ItemManage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmItemManage = new JFrame();
		frmItemManage.getContentPane().setBackground(Color.WHITE);
		frmItemManage.setTitle("Items Management Page");
		frmItemManage.setBounds(100, 100, 950, 478);
		frmItemManage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmItemManage.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 166, 756, 262);
		frmItemManage.getContentPane().add(scrollPane);
		
		//To build the Title string with user full name and permission
		JLabel lblTitle = new JLabel("Items Management Page \\ User Name: " + users.Get_FullName()
				+ ", the permission is " + ItemsDataFlow.check());
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setBounds(115, 10, 579, 17);
		frmItemManage.getContentPane().add(lblTitle);

		table = new JTable();
		table.setEnabled(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			//this event in table for determine the row number
			public void mouseClicked(MouseEvent evt) {
				row = table.rowAtPoint(evt.getPoint());
				if (row >= 0) {
					ItemsDataFlow.getDataRow(table, row);
				}
			}
		});

		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(table);
		
		table.setEditingColumn(1);

		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblItemName.setBounds(116, 51, 91, 17);
		frmItemManage.getContentPane().add(lblItemName);

		JLabel lblPrice = new JLabel("Price/IQD");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrice.setBounds(341, 51, 91, 17);
		frmItemManage.getContentPane().add(lblPrice);

		JLabel lblQantity = new JLabel("Qantity");
		lblQantity.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQantity.setBounds(597, 51, 67, 17);
		frmItemManage.getContentPane().add(lblQantity);

		JLabel lblCountry = new JLabel("Country");
		lblCountry.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCountry.setBounds(116, 82, 60, 17);
		frmItemManage.getContentPane().add(lblCountry);

		JLabel lblDateExpire = new JLabel("Date Expire (dd/MM/yyyy)");
		lblDateExpire.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDateExpire.setBounds(341, 82, 222, 17);
		frmItemManage.getContentPane().add(lblDateExpire);

		txtItemName = new JTextField();
		txtItemName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtItemName.setBounds(202, 48, 134, 23);
		frmItemManage.getContentPane().add(txtItemName);
		txtItemName.setColumns(10);

		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPrice.setColumns(10);
		txtPrice.setBounds(429, 48, 102, 23);
		frmItemManage.getContentPane().add(txtPrice);

		txtQantity = new JTextField();
		txtQantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQantity.setColumns(10);
		txtQantity.setBounds(663, 48, 103, 23);
		frmItemManage.getContentPane().add(txtQantity);

		txtExDate = new JTextField();
		txtExDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtExDate.setColumns(10);
		txtExDate.setBounds(530, 80, 134, 23);
		frmItemManage.getContentPane().add(txtExDate);

		cbCountry = new JComboBox();
		cbCountry.setFont(new Font("Tahoma", Font.BOLD, 14));
		cbCountry.setBounds(202, 79, 134, 23);
		frmItemManage.getContentPane().add(cbCountry);
		//this method for load all country in combo box
		ItemsDataFlow.putCountry();

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 116, 756, 2);
		frmItemManage.getContentPane().add(separator);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(117, 40, 649, 4);
		frmItemManage.getContentPane().add(separator_2);

		panel = new JPanel();
		panel.setBounds(775, 40, 157, 388);

		frmItemManage.getContentPane().add(panel);
		panel.setLayout(null);

		String Users_Management = "<html>" + "Users" + "<br>" + "Management" + "</html>";
		
		JButton btnUsersManagement = new JButton(Users_Management);
		//this event of User Management button 
		btnUsersManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmItemManage.dispose();
				UserManagement.main(null);
			}
		});
		btnUsersManagement.setFont(new Font("Dialog", Font.BOLD, 14));
		btnUsersManagement.setBounds(10, 11, 137, 78);
		panel.add(btnUsersManagement);

		JButton btnReports = new JButton("Reports");
		//this event of Report button 
		btnReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmItemManage.dispose();
				Reports.main(null);
			}
		});
		btnReports.setFont(new Font("Dialog", Font.BOLD, 14));
		btnReports.setBounds(10, 100, 137, 78);
		panel.add(btnReports);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 120, 756, 40);
		frmItemManage.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		JButton btn_Delete = new JButton("Delete Item");
		btn_Delete.setBounds(370, 10, 121, 23);
		panel_1.add(btn_Delete);

		JButton btn_UpdateItem = new JButton("Update Item");
		btn_UpdateItem.setBounds(237, 10, 121, 23);
		panel_1.add(btn_UpdateItem);

		JButton btnAddNewItem = new JButton("Add New Item");
		btnAddNewItem.setBounds(12, 10, 121, 23);
		panel_1.add(btnAddNewItem);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(145, 10, 80, 23);
		panel_1.add(btnClear);
		////this event of Clear Text fields button 
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtItemName.setText("");
				txtPrice.setText("");
				txtQantity.setText("");
				txtExDate.setText("");
			}
		});
		//this event of Add new Item button 
		btnAddNewItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//the check method used for transfer data to ItemsDataFlow class and return boolean value
				if (check(txtItemName, txtPrice, txtQantity, txtExDate, cbCountry)) {
					//the itemTemplate class used for save current or new item when we need do any action 
					itemTemplate.set_ItemName(txtItemName.getText().trim());
					itemTemplate.set_Price(Double.parseDouble(txtPrice.getText().trim()));
					itemTemplate.set_Qantity(Integer.parseInt(txtQantity.getText().trim()));
					itemTemplate.set_ExpireDate(txtExDate.getText().trim());
					itemTemplate.set_Country(cbCountry.getItemAt(cbCountry.getSelectedIndex()).toString().trim());
					itemTemplate.set_EntryDate();
					//InsertItem method manipulation with ItemTemplate class for get data and return boolean value
					//there is overload in InsertItem method
					if (!DBconnection.InsertItem())
						JOptionPane.showMessageDialog(null, "Error in the Insert !!!");
					else
						//this statement for load data again to the table after successfully insert 
						table.setModel(DbUtils.resultSetToTableModel(DBconnection.LoadData()));
				} else
					JOptionPane.showMessageDialog(null, "Error in the Validation !!!");
			}

		});
		//this event of Update item button
		btn_UpdateItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (row >= 0) {
					//it is for set new data to Item Template by use ItemsDataFlow class
					ItemsDataFlow.getDataRow();
					//get the item id from the table when selected a row
					int id = Integer.parseInt(table.getValueAt(row, 0).toString());
					//check validation of text fields
					if (check(txtItemName, txtPrice, txtQantity, txtExDate, cbCountry)) {
						//InsertItem method by id for insert to History table and it is return boolean value
						//there is overload in InsertItem method
						if (DBconnection.InsertItem(id)) {
							//this method for update item when parameter is 1
							//too, can use it for delete when parameter is 2
							DBconnection.UpdateDeleteItem(1, id);
							//this statement for load data again to the table after successfully update
							table.setModel(DbUtils.resultSetToTableModel(DBconnection.LoadData()));
						} else JOptionPane.showMessageDialog(null, "Can not update !!!");
						//set row by -1 that mention there is no selected row
						row = -1;
					} else
						JOptionPane.showMessageDialog(null, "Error in the Validation !!!");
				} else {
					JOptionPane.showMessageDialog(null, "Please, select row !!!");
				}
			}
		});
		//this event of delete item button
		btn_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if row not equal -1 that is mean there are selected value
				if (row >= 0) {
					//it is for set new data to Item Template by use ItemsDataFlow class
					ItemsDataFlow.getDataRow();
					//get the item id from the table when selected a row
					int id = Integer.parseInt(table.getValueAt(row, 0).toString());
					//this method for update item when parameter is 1
					//too, can use it for delete when parameter is 2
					DBconnection.UpdateDeleteItem(2, id);
					//this statement for load data again to the table after successfully deleted
					table.setModel(DbUtils.resultSetToTableModel(DBconnection.LoadData()));
					row = -1;
				} else {
					JOptionPane.showMessageDialog(null, "Please, select row !!!");
				}
			}
		});

		//this method for chenge the frame bound and visible panels depend of user permission
		setBound();
		//this statement for load data in the first run to the table
		table.setModel(DbUtils.resultSetToTableModel(DBconnection.LoadData()));
		
		JLabel lblLbllogo = new JLabel("");
		//lblLbllogo.setIcon(new ImageIcon(getClass().getResource("/img/Pharmacy2.png")));
		lblLbllogo.setIcon(new ImageIcon("D:\\JAVA Training 2019\\Workspace2\\PharmacySW\\img\\pharmacy2.png"));
		lblLbllogo.setBounds(10, 11, 102, 101);
		frmItemManage.getContentPane().add(lblLbllogo);
		
		JButton btn_Logoff = new JButton("Logoff");
		//log off button event
		btn_Logoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				users.reset();
				frmItemManage.dispose();
				Login.main(null);
			}
		});
		btn_Logoff.setBounds(696, 8, 70, 23);
		frmItemManage.getContentPane().add(btn_Logoff);
	}

	//method for get tools and pass the data to ItemDataFlow class for check the validation 
	private boolean check(JTextField txtItemName, JTextField txtPrice, JTextField txtQantity, JTextField txtExDate,
			JComboBox cbCountry) {
		if (!ItemsDataFlow.check(txtItemName.getText().trim(), "txtItemName")) {
			JOptionPane.showMessageDialog(null, "There are error in Item Name");
			return false;
		} else if (!ItemsDataFlow.check(txtPrice.getText().trim(), "txtPrice")) {
			JOptionPane.showMessageDialog(null, "There are error in Price");
			return false;
		} else if (!ItemsDataFlow.check(txtQantity.getText().trim(), "txtQantity")) {
			JOptionPane.showMessageDialog(null, "There are error in Qantity");
			return false;
		} else if (!ItemsDataFlow.check(txtExDate.getText().trim(), "txtExDate")) {
			JOptionPane.showMessageDialog(null, "There are error in Expire Date");
			return false;
		} else if (!ItemsDataFlow.check(cbCountry.getItemAt(cbCountry.getSelectedIndex()).toString().trim(),
				"cbCountry")) {
			JOptionPane.showMessageDialog(null, "There are error in Country");
			return false;
		} else
			return true;
	}
	
	//this method for chenge the frame bound and visible panels depend of user permission
	private void setBound() {
		if (ItemsDataFlow.check() == "Admin and Entry") {
			frmItemManage.setBounds(100, 100, 950, 478);
			panel.setVisible(true);
			panel_1.setVisible(true);
		} else if (ItemsDataFlow.check() == "Admin") {
			panel.setVisible(true);
			panel_1.setVisible(false);
		} else if (ItemsDataFlow.check() == "Entry") {
			panel.setVisible(false);
			panel_1.setVisible(true);
			frmItemManage.setBounds(100, 100, 785, 478);
		} else {
			panel.setVisible(false);
			panel_1.setVisible(false);
		}
	}
}

class ItemsDataFlow extends ItemManage {
//overload the check method
	//for build the title string depend the user permission
	static String check() {
		return (users.Get_IsAdmin() == 1 && users.Get_IsUser() == 1) ? "Admin and Entry"
				: (users.Get_IsAdmin() == 1 && users.Get_IsUser() != 1) ? "Admin" : "Entry";
	}

	//check validation of passed data
	static boolean check(String txt, String type) {
		switch (type) {
		case "txtItemName":
			return (Validation.check(txt, 25, 2) && !Validation.IsDouble(txt) && !Validation.IsInteger(txt));
		case "txtPrice":
			return (Validation.check(txt, 8, 1) && Validation.IsDouble(txt));
		case "txtQantity":
			return (Validation.check(txt, 4, 1) && Validation.IsInteger(txt));
		case "txtExDate":
			return (Validation.check(txt, 10, 10) && Validation.IsDate(txt, "dd/MM/yyyy"));
		case "cbCountry":
			return (Validation.check(txt, 25, 2) && !Validation.IsDouble(txt) && !Validation.IsInteger(txt));
		default:
			return false;
		}
	}
//overload getDataRow
	//for set select data row to the tools
	public static void getDataRow(JTable tb, int row) {
		txtItemName.setText(tb.getValueAt(row, 1).toString());
		txtPrice.setText(tb.getValueAt(row, 2).toString());
		txtQantity.setText(tb.getValueAt(row, 3).toString());
		txtExDate.setText(tb.getValueAt(row, 5).toString());

		String country = tb.getValueAt(row, 4).toString();
		cbCountry.setSelectedItem(country);
	}

	//for get data from Tools and set it to itemTemplate
	public static void getDataRow() {

		itemTemplate.set_ItemName(txtItemName.getText());
		itemTemplate.set_Price(Double.parseDouble(txtPrice.getText()));
		itemTemplate.set_Qantity(Integer.parseInt(txtQantity.getText()));
		itemTemplate.set_Country(cbCountry.getItemAt(cbCountry.getSelectedIndex()).toString().trim());
		itemTemplate.set_ExpireDate(txtExDate.getText());
		itemTemplate.set_EntryDate();
		itemTemplate.set_EntryUserid(users.Get_userId());

	}

	//for bring country data from DBconnection class
	static void putCountry() {
		try {
			ResultSet conRS = DBconnection.LoadCountry();
			while (conRS.next()) {
				ItemManage.cbCountry.addItem(conRS.getString("Country"));
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

}