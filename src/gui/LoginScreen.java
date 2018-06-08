package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import client.Client;
import model.User;

public class LoginScreen {

	private JFrame frame;
	private JTextField userField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
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
	public LoginScreen() {
		initialize();
	}

	public void show() {
		frame.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 380, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogIn = new JButton("Sign in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = userField.getText();
				String password = String.valueOf(passwordField.getPassword());
				
				try {
					Statement stmt = Client.getConnection().createStatement();
					String sql = "select * from user where username = '" + username + "'";
					ResultSet r = stmt.executeQuery(sql);
					while(r.next()) {
						System.out.println("runing result set");
						int id = r.getInt("uid");
						String u = r.getString("username");
						String p = r.getString("password");
						Date d = r.getDate("date_joined");
						String em = r.getString("email");
						if(u.equals(username) && p.equals(password)) {
							System.out.println("Login Successful");
							User user = new User(id, u, d, em, p);
							HomeScreen h = new HomeScreen(user);
							h.show();
							frame.dispose();
						} else {
							JOptionPane.showMessageDialog(new JFrame(), "Invalid username or password", "", JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnLogIn.setBounds(93, 179, 176, 34);
		frame.getContentPane().add(btnLogIn);
		
		userField = new JTextField();
		userField.setBounds(195, 75, 155, 22);
		frame.getContentPane().add(userField);
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(195, 127, 155, 22);
		frame.getContentPane().add(passwordField);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(25, 68, 122, 34);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(25, 122, 122, 33);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblNewLabel = new JLabel("Shout!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
		lblNewLabel.setBounds(82, 13, 197, 49);
		frame.getContentPane().add(lblNewLabel);
	}
}
