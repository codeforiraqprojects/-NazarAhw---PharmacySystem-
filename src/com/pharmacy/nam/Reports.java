package com.pharmacy.nam;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.SQLiteConnect.myPro.DBconnection;

import net.proteanit.sql.DbUtils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class Reports {

	private JFrame frame;
	private JTable tbReport;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reports window = new Reports();
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
	public Reports() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 899, 516);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 116, 863, 285);
		frame.getContentPane().add(scrollPane);

		tbReport = new JTable();
		scrollPane.setViewportView(tbReport);

		JLabel lblChooseReport = new JLabel("Choose Report");
		lblChooseReport.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblChooseReport.setBounds(12, 46, 124, 30);
		frame.getContentPane().add(lblChooseReport);
		
		JButton btnBack = new JButton("Back To Item Manage Page");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ItemManage.main(null);
			}
		});
		btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
		btnBack.setBackground(Color.ORANGE);
		btnBack.setBounds(614, 41, 257, 38);
		btnBack.setIcon(null);
		frame.getContentPane().add(btnBack);
		
		JComboBox cbReport = new JComboBox();
		cbReport.setBackground(Color.ORANGE);
		cbReport.setFont(new Font("Times New Roman", Font.BOLD, 16));
		cbReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
					switch (cbReport.getSelectedIndex()) {
					case 1:
						tbReport.setModel(DbUtils.resultSetToTableModel(DBconnection.GetReportAll()));
						break;
					case 2:
						tbReport.setModel(DbUtils.resultSetToTableModel(DBconnection.GetReportLessQuantity()));
						break;
					case 3:
						tbReport.setModel(DbUtils.resultSetToTableModel(DBconnection.GetReportLessExpire()));
						break;
					case 0:
						//JOptionPane.showMessageDialog(null, "Please Select Report Type");
						break;
					}
			}
		});
		cbReport.setBounds(140, 43, 285, 38);
		cbReport.addItem("---");
		cbReport.addItem("Report All");
		cbReport.addItem("Report Quantity Less Than 10 pcs");
		cbReport.addItem("Report Expire Less Than One Month");
		frame.getContentPane().add(cbReport);
	}
}
