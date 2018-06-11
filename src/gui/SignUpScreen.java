package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.SwingConstants;

import client.Client;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class SignUpScreen {

	private JFrame frame;
	private JTextField usernameField;
	private JTextField emailField;
	private JLabel lblEmail;
	private JLabel lblReenterPassword;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JPasswordField passwordField1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpScreen window = new SignUpScreen();
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
	public SignUpScreen() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 357, 323);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblShoutSignup = new JLabel("Shout! Sign-up");
		lblShoutSignup.setHorizontalAlignment(SwingConstants.CENTER);
		lblShoutSignup.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 26));
		lblShoutSignup.setBounds(7, 13, 324, 72);
		frame.getContentPane().add(lblShoutSignup);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(19, 89, 102, 16);
		frame.getContentPane().add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(156, 87, 175, 22);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(156, 122, 175, 22);
		frame.getContentPane().add(emailField);
		emailField.setColumns(10);
		
		JButton btnSubmitRegistration = new JButton("Submit Registration");
		btnSubmitRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean flag = false;
				String errorMsg = "";
				
				String username = usernameField.getText();
				String email = emailField.getText();
				String p1 = String.valueOf(passwordField.getPassword());
				String p2 = String.valueOf(passwordField1.getPassword());
				
				if(username.isEmpty()) {
					errorMsg  = "Username field empty";
					flag = true;
				} else if(email.isEmpty()) {
					errorMsg = "Email field empty";
					flag = true;
				} else if(p1.isEmpty() || (!p1.equals(p2))) {
					errorMsg = "Password is empty or does not match";
					flag = true;
				}
				
				if(!flag) {
					System.out.println("Valid registration");
					String sql = "insert into user(username, date_joined, email, password) values ('" + username + "','" + new Timestamp(System.currentTimeMillis()) + "','" + email + "','" + p1 + "');";
					try {
						Statement stmt = Client.getConnection().createStatement();
						stmt.executeUpdate(sql);
						RegistrationSuccessfulScreen rs = new RegistrationSuccessfulScreen();
						rs.show();
						frame.dispose();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), errorMsg, "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSubmitRegistration.setBounds(82, 238, 175, 25);
		frame.getContentPane().add(btnSubmitRegistration);
		
		lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(19, 124, 102, 16);
		frame.getContentPane().add(lblEmail);
		
		lblReenterPassword = new JLabel("Re-enter Password");
		lblReenterPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblReenterPassword.setBounds(19, 194, 125, 16);
		frame.getContentPane().add(lblReenterPassword);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(19, 160, 102, 16);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(156, 157, 175, 22);
		frame.getContentPane().add(passwordField);
		
		passwordField1 = new JPasswordField();
		passwordField1.setBounds(156, 192, 175, 22);
		frame.getContentPane().add(passwordField1);
	}
}
