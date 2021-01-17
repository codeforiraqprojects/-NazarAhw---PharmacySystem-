package com.pharmacy.nam;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.SQLiteConnect.myPro.DBconnection;


import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Color;

public class UserManagement {

	private JFrame frame;
	private JTextField txtUserIdSearch;
	private JTextField txtFullName;
	private JTextField txtUserName;
	private JTextField txtPass;
	
	private JLabel lblUserIdSearch;
	private JLabel lblFullName;
	private JLabel lblUserName;
	private JLabel lblPass;
	private JCheckBox cbAdmin;
	private JCheckBox cbUser;
	private JCheckBox cb;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnInsert;
	private JButton btnReset;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManagement window = new UserManagement();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserManagement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.text);
		frame.setBounds(100, 100, 658, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUserIdSearch = new JLabel("Enter User ID");
		lblUserIdSearch.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUserIdSearch.setBounds(55, 26, 105, 27);
		frame.getContentPane().add(lblUserIdSearch);
		
		txtUserIdSearch = new JTextField();
		txtUserIdSearch.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtUserIdSearch.setBounds(199, 26, 186, 24);
		frame.getContentPane().add(txtUserIdSearch);
		txtUserIdSearch.setColumns(10);
		
		
		
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblFullName.setBounds(55, 118, 105, 27);
		lblFullName.setVisible(false);
		frame.getContentPane().add(lblFullName);
		
		txtFullName = new JTextField();
		txtFullName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtFullName.setColumns(10);
		txtFullName.setBounds(199, 118, 115, 24);
		txtFullName.setVisible(false);
		frame.getContentPane().add(txtFullName);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUserName.setBounds(55, 156, 105, 27);
		lblUserName.setVisible(false);
		frame.getContentPane().add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtUserName.setColumns(10);
		txtUserName.setBounds(199, 156, 115, 24);
		txtUserName.setVisible(false);
		frame.getContentPane().add(txtUserName);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPass.setBounds(343, 156, 105, 27);
		lblPass.setVisible(false);
		frame.getContentPane().add(lblPass);
		
		txtPass = new JTextField();
		txtPass.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtPass.setColumns(10);
		txtPass.setBounds(487, 156, 115, 24);
		txtPass.setVisible(false);
		frame.getContentPane().add(txtPass);
		
		JCheckBox cbAdmin = new JCheckBox("Admin");
		cbAdmin.setBackground(SystemColor.activeCaption);
		cbAdmin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		cbAdmin.setBounds(199, 202, 115, 23);
		cbAdmin.setVisible(false);
		frame.getContentPane().add(cbAdmin);
		
		JCheckBox cbUser = new JCheckBox("User");
		cbUser.setBackground(SystemColor.activeCaption);
		cbUser.setToolTipText("");
		cbUser.setFont(new Font("Times New Roman", Font.BOLD, 16));
		cbUser.setBounds(485, 202, 117, 23);
		cbUser.setVisible(false);
		frame.getContentPane().add(cbUser);
		
		
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(UIManager.getColor("EditorPane.inactiveBackground"));
		btnUpdate.setBackground(SystemColor.activeCaption);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int Id = Integer.parseInt(txtUserIdSearch.getText());
				String FullName = txtFullName.getText();
				String UserName = txtUserName.getText();
				String Pass = txtPass.getText();
				int Admin = 0; 
				int User = 0;
				if (cbAdmin.isSelected()) Admin = 1;
				if (cbUser.isSelected()) User = 1;
				try {
					if (DBconnection.updateUserInfo(Id, FullName,UserName ,Pass ,Admin ,User )) {
						ClearData();
						lblFullName.setVisible(false);
						lblUserName.setVisible(false);
						lblPass.setVisible(false);
						cbAdmin.setVisible(false);
						cbUser.setVisible(false);
						txtFullName.setVisible(false);
						txtUserName.setVisible(false);
						txtPass.setVisible(false);
//						btnUpdate.setVisible(false);
//						btnDelete.setVisible(false);
//						btnInsert.setVisible(false);
						JOptionPane.showMessageDialog(null, "Updated Successful");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Fail");
				}
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnUpdate.setBounds(55, 291, 131, 26);
		btnUpdate.setVisible(false);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setForeground(UIManager.getColor("EditorPane.inactiveBackground"));
		btnInsert.setBackground(SystemColor.activeCaption);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((txtUserIdSearch.getText() != null || txtUserIdSearch.getText() != "")
						&&(txtFullName.getText() != null || txtFullName.getText() != "")
						&&(txtUserName.getText() != null || txtUserName.getText() != "")
						&&(txtPass.getText() != null || txtPass.getText() != "")
						&&(cbAdmin.isSelected() || cbUser.isSelected())
						) {
					
					String FullName = txtFullName.getText();
					String UserName = txtUserName.getText();
					String Pass = txtPass.getText();
					int Admin = 0; 
					int User = 0;
					if (cbAdmin.isSelected()) Admin = 1;
					if (cbUser.isSelected()) User = 1;
					try {
						if (DBconnection.InsertUserInfo(FullName,UserName ,Pass ,Admin ,User )) {
							ClearData();
							lblFullName.setVisible(false);
							lblUserName.setVisible(false);
							lblPass.setVisible(false);
							cbAdmin.setVisible(false);
							cbUser.setVisible(false);
							txtFullName.setVisible(false);
							txtUserName.setVisible(false);
							txtPass.setVisible(false);
//							btnUpdate.setVisible(false);
//							btnDelete.setVisible(false);
//							btnInsert.setVisible(false);
							JOptionPane.showMessageDialog(null, "Insert Info Successful");
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Fail");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Fill All Fields");
				}
			}
		});
		btnInsert.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnInsert.setBounds(55, 328, 292, 26);
		btnInsert.setVisible(false);
		frame.getContentPane().add(btnInsert);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(UIManager.getColor("EditorPane.inactiveBackground"));
		btnDelete.setBackground(SystemColor.activeCaption);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (DBconnection.TranferToUsersHistory(Integer.parseInt(txtUserIdSearch.getText()))) {
						if (DBconnection.DeleteUserInfo(Integer.parseInt(txtUserIdSearch.getText()))) {
							JOptionPane.showMessageDialog(null, "Deleted Successful");
						}
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Fail");
				}
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDelete.setBounds(216, 291, 131, 26);
		btnDelete.setVisible(false);
		frame.getContentPane().add(btnDelete);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setForeground(UIManager.getColor("EditorPane.inactiveBackground"));
		btnReset.setBackground(SystemColor.activeCaption);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClearData();
				if (cbAdmin.isSelected()) {
					cbAdmin.setSelected(false);
				}
				if (cbUser.isSelected()) {
					cbUser.setSelected(false);
				} 
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnReset.setBounds(359, 291, 260, 63);
		frame.getContentPane().add(btnReset);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("EditorPane.foreground"));
		separator.setBounds(35, 93, 584, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(35, 278, 584, 2);
		frame.getContentPane().add(separator_1);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setForeground(UIManager.getColor("EditorPane.inactiveBackground"));
		btnSearch.setBackground(SystemColor.activeCaption);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtUserIdSearch.getText() != null || txtUserIdSearch.getText() != "") {
				
				if (Validation.IsInteger(txtUserIdSearch.getText())) {
					DBconnection.login(Integer.parseInt(txtUserIdSearch.getText()));
					
					int id = DBconnection.um._UserId;
					String fullname = DBconnection.um._FullName.toString();
					String username = DBconnection.um._UserName.toString();
					String pass = DBconnection.um._Password.toString();
					int admin = DBconnection.um._IsAdmin;
					int user = DBconnection.um._IsUser;
					
					
					
					lblFullName.setVisible(true);
					lblUserName.setVisible(true);
					lblPass.setVisible(true);
					cbAdmin.setVisible(true);
					cbUser.setVisible(true);
					txtFullName.setVisible(true);
					txtUserName.setVisible(true);
					txtPass.setVisible(true);
					btnUpdate.setVisible(true);
					btnDelete.setVisible(true);
					btnInsert.setVisible(false);
					
					txtFullName.setText(fullname);
					txtUserName.setText(username);
					txtPass.setText(pass);
					
					
					//VisibleForm();
					if (admin == 1) {
						cbAdmin.setSelected(true);
					}
					if (user == 1) {
						cbUser.setSelected(true);
					}
				} else
					JOptionPane.showMessageDialog(null, "Please Enter Numbers Only");
				
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Fill User ID Field");
				}
			}
		});
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnSearch.setBounds(452, 26, 108, 26);
		frame.getContentPane().add(btnSearch);
		
		JButton btnNew = new JButton("New User");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblFullName.setVisible(true);
				lblUserName.setVisible(true);
				lblPass.setVisible(true);
				cbAdmin.setVisible(true);
				cbUser.setVisible(true);
				txtFullName.setVisible(true);
				txtUserName.setVisible(true);
				txtPass.setVisible(true);
				btnInsert.setVisible(true);
				btnReset.setVisible(true);
			}
		});
		btnNew.setForeground((Color) null);
		btnNew.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNew.setBackground(SystemColor.activeCaption);
		btnNew.setBounds(452, 55, 108, 26);
		frame.getContentPane().add(btnNew);
		
		JButton btnBack = new JButton("Back To Items Management");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ItemManage.main(null);
			}
		});
		btnBack.setForeground((Color) null);
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnBack.setBackground(Color.GRAY);
		btnBack.setBounds(55, 55, 330, 26);
		frame.getContentPane().add(btnBack);
	}
	void VisibleForm()
	{
		txtFullName.setVisible(true);
		txtUserName.setVisible(true);
		txtPass.setVisible(true);
		btnUpdate.setVisible(true);
		btnDelete.setVisible(true);
		btnInsert.setVisible(true);
		btnReset.setVisible(true);
	}
	
	void ClearData()
	{
		txtUserIdSearch.setText(null);
		txtFullName.setText(null);
		txtUserName.setText(null);
		txtPass.setText(null);
	}
}
