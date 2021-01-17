package com.pharmacy.nam;

import java.awt.EventQueue;
import javax.swing.JFrame;
import com.SQLiteConnect.myPro.DBconnection;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.DropMode;
import java.awt.Font;

public class Login {

	protected JFrame frmPharmacySystem;
	protected JTextField txtUserName;
	protected JPasswordField txtPw;
	protected JButton btnLogin;
	private JSeparator separator_1;
	private JButton btnReset;
	private JButton btnExit;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmPharmacySystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		DBconnection.connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmPharmacySystem = new JFrame();
		frmPharmacySystem.setTitle("Pharmacy System V1.1");
		frmPharmacySystem.setResizable(false);
		frmPharmacySystem.getContentPane().setBackground(Color.WHITE);
		frmPharmacySystem.getContentPane().setLayout(null);

		JLabel lblTitle = new JLabel("Login Page");
		lblTitle.setBounds(153, 6, 107, 29);
		frmPharmacySystem.getContentPane().add(lblTitle);
		lblTitle.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 18));

		JLabel lblUserName = new JLabel("UserName");
		lblUserName.setBounds(10, 51, 117, 20);
		frmPharmacySystem.getContentPane().add(lblUserName);
		lblUserName.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16));

		JLabel lblPw = new JLabel("Password");
		lblPw.setBounds(12, 97, 83, 29);
		frmPharmacySystem.getContentPane().add(lblPw);
		lblPw.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16));

		txtUserName = new JTextField();
		txtUserName.setBounds(126, 46, 157, 29);
		txtUserName.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16));
		frmPharmacySystem.getContentPane().add(txtUserName);
		txtUserName.setColumns(10);

		txtPw = new JPasswordField();
		txtPw.setBounds(127, 97, 156, 29);
		txtPw.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16));
		frmPharmacySystem.getContentPane().add(txtPw);
		txtPw.setColumns(10);

		btnLogin = new JButton("Login");

		btnLogin.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 16));
		btnLogin.setBackground(new Color(0, 153, 255));
		btnLogin.setBounds(27, 163, 127, 29);
		frmPharmacySystem.getContentPane().add(btnLogin);

		JSeparator separator = new JSeparator();
		separator.setBounds(79, 33, 249, 2);
		frmPharmacySystem.getContentPane().add(separator);

		separator_1 = new JSeparator();
		separator_1.setBounds(79, 149, 249, 2);
		frmPharmacySystem.getContentPane().add(separator_1);

		btnReset = new JButton("Reset");
		
		//Reset button Event
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtUserName.setText(null);
				txtPw.setText(null);
				txtUserName.setFocusable(true);
			}
		});
		btnReset.setBounds(166, 166, 98, 26);
		frmPharmacySystem.getContentPane().add(btnReset);

		btnExit = new JButton("Exit");
		//Exit button event
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(276, 166, 98, 26);
		frmPharmacySystem.getContentPane().add(btnExit);
		
		label = new JLabel("");
		//it is Pharmacy Logo
		//label.setIcon(new ImageIcon(getClass().getResource("/img/Pharmacy2.png")));
		//label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/Pharmacy2.png")));
		label.setIcon(new ImageIcon("D:\\JAVA Training 2019\\Workspace2\\PharmacySW\\img\\pharmacy2.png"));
		label.setBounds(333, 38, 102, 101);
		frmPharmacySystem.getContentPane().add(label);
		
		JTextPane txtAbout = new JTextPane();
		txtAbout.setText(About.buildString());
		txtAbout.setFont(new Font("Simplified Arabic", Font.BOLD, 12));
		txtAbout.setEditable(false);
		txtAbout.setBounds(27, 201, 346, 209);
		frmPharmacySystem.getContentPane().add(txtAbout);

		frmPharmacySystem.setBackground(Color.WHITE);
		frmPharmacySystem.setBounds(100, 100, 450, 450);
		frmPharmacySystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Login button event
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//check method return boolean value for check fields valiation
				if (Validation.check(txtUserName.getText(), 8, 2) && Validation.check(txtPw.getText(), 8, 2)) {
					//Login class return object as user class
					users u = DBconnection.login(txtUserName.getText(), txtPw.getText());
					if (!u.equals(null)) {
						frmPharmacySystem.setVisible(false);
						ItemManage.main(null);
					} else
						JOptionPane.showMessageDialog(null, "The User is not register !!!");
				} else
					JOptionPane.showMessageDialog(null, "User name or password must be correct size !!!");
			}
		});
	}
}