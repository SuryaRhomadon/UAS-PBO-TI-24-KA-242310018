package com.hrd.saw.ui;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel.setBounds(164, 64, 62, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(161, 87, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Inter", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(162, 124, 59, 16);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(158, 146, 130, 26);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = new String(passwordField.getPassword());

				String sql = "SELECT id_hrd, nama FROM hrd WHERE username = ? AND password = ?";

				try (Connection conn = DriverManager.getConnection(
				        "jdbc:mysql://localhost:8889/db_tugasakhir",
				        "root",
				        "root");
				     PreparedStatement ps = conn.prepareStatement(sql)) {

				    ps.setString(1, username);
				    ps.setString(2, password);

				    ResultSet rs = ps.executeQuery();

				    if (rs.next()) {
				        String namaHrd = rs.getString("nama");

				        JOptionPane.showMessageDialog(
				                LoginFrame.this,
				                "Login successful\nWelcome " + namaHrd
				        );

				        new DashboardPanel().setVisible(true);
				        LoginFrame.this.dispose();

				    } else {
				        JOptionPane.showMessageDialog(
				                LoginFrame.this,
				                "Invalid username or password"
				        );
				    }

				} catch (Exception ex) {
				    ex.printStackTrace();
				    JOptionPane.showMessageDialog(
				            LoginFrame.this,
				            "Database error"
				    );
				}


			}
		});
		
		btnNewButton.setBounds(162, 187, 117, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Login Form");
		lblNewLabel_2.setFont(new Font("Inter", Font.BOLD, 14));
		lblNewLabel_2.setBounds(180, 17, 104, 16);
		contentPane.add(lblNewLabel_2);

	}
}
