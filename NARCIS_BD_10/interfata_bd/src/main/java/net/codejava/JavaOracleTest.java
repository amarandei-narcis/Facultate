package net.codejava;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;

import java.sql. *;



public class JavaOracleTest {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaOracleTest window = new JavaOracleTest();
					window.frmMagazinOnlineDe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	/**
	 * Create the application.
	 */
	Connection connection = null;
	
	private JTable table;
	private JFrame frmMagazinOnlineDe;
	private JTable table_1;
	
	public JavaOracleTest() {
		
		String dbURL="jdbc:oracle:thin:@ bd-dc.cs.tuiasi.ro:1539:orcl";
		String username="bd110";
		String password="bd110";
		
		try {
			connection = DriverManager.getConnection(dbURL,username, password);
			System.out.println("Conectarea s-a realizat cu succes");
		} 
		catch (SQLException e)
		{
			System.out.println("Eroare, nu s-a reusit conectarea");
			e.printStackTrace();
		}
	
		frmMagazinOnlineDe = new JFrame();
		
		
		frmMagazinOnlineDe.getContentPane().setEnabled(false);
		frmMagazinOnlineDe.getContentPane().setFocusable(false);
		frmMagazinOnlineDe.getContentPane().setFocusTraversalKeysEnabled(false);
		frmMagazinOnlineDe.setTitle("Magazin online de jocuri");
		frmMagazinOnlineDe.setBounds(100, 100, 968, 695);
		frmMagazinOnlineDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMagazinOnlineDe.getContentPane().setLayout(null);
		
		JButton btnNewButton_4 = new JButton("Clienti");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
				
				String query = " select * from Clienti";
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				table_1.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch (SQLException e1)
				{
					System.out.println("Eroare, nu s-a reusit conectarea");
					e1.printStackTrace();
				}
				
			}
		});

		btnNewButton_4.setBackground(Color.LIGHT_GRAY);
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton_4.setBounds(29, 36, 110, 35);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		table = new JTable();
		table.setBounds(170, 389, 0, 0);
		frmMagazinOnlineDe.getContentPane().add(table);
		frmMagazinOnlineDe.getContentPane().add(btnNewButton_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 90, 860, 540);
		frmMagazinOnlineDe.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JButton btnNewButton = new JButton("Detalii clienti");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					
					String query = " select * from Detalii_clienti";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					}
					catch (SQLException e1)
					{
						System.out.println("Eroare, nu s-a reusit conectarea");
						e1.printStackTrace();
					}
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(175, 36, 110, 35);
		frmMagazinOnlineDe.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Relation_1");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					
					String query = " select * from Relation_1";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					}
					catch (SQLException e1)
					{
						System.out.println("Eroare, nu s-a reusit conectarea");
						e1.printStackTrace();
					}
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(329, 36, 110, 35);
		frmMagazinOnlineDe.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Jocuri");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					
					String query = " select * from Jocuri";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					}
					catch (SQLException e1)
					{
						System.out.println("Eroare, nu s-a reusit conectarea");
						e1.printStackTrace();
					}
			}
		});
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton_2.setBounds(478, 36, 110, 35);
		frmMagazinOnlineDe.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Angajati");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					
					String query = " select * from Angajati";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					}
					catch (SQLException e1)
					{
						System.out.println("Eroare, nu s-a reusit conectarea");
						e1.printStackTrace();
					}
			}
		});
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton_3.setBounds(633, 36, 110, 35);
		frmMagazinOnlineDe.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_5 = new JButton("Specificatii");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					
					String query = " select * from Specificatii";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					}
					catch (SQLException e1)
					{
						System.out.println("Eroare, nu s-a reusit conectarea");
						e1.printStackTrace();
					}
			}
		});
		btnNewButton_5.setBackground(Color.LIGHT_GRAY);
		btnNewButton_5.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton_5.setBounds(779, 36, 110, 35);
		frmMagazinOnlineDe.getContentPane().add(btnNewButton_5);
		frmMagazinOnlineDe.setVisible(true);
	
	}
}


