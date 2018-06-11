package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.sun.jmx.remote.internal.ClientNotifForwarder;

import client.Client;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class SplashScreen {

	private JFrame frame;

	private boolean signUpScreenExists = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen window = new SplashScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public SplashScreen() throws SQLException {
		initialize();
		frame.setVisible(true);
		//testConnection();
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	public void testConnection() throws SQLException {
		Connection con =  Client.getConnection();
		Statement stmt = con.createStatement();
		String sql;
		sql = "SELECT * from business";
		// Is a set of tuples
		ResultSet rs = stmt.executeQuery(sql);

		// rs.next to advance to the next tuple ( each row returned) in the set.
		while (rs.next()) {
			// Retrieve by column name
			int id = rs.getInt("bid");
			String name = rs.getString("name");
			String city = rs.getString("city");

			// Display values
			System.out.println("ID: " + id);
			System.out.println("name: " + name);
			System.out.println("city :" + city);

		}

		//con.close();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 508, 382);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblShout = new JLabel("Shout!");
		lblShout.setHorizontalAlignment(SwingConstants.CENTER);
		lblShout.setFont(new Font("Segoe UI Emoji", Font.BOLD, 26));
		lblShout.setBounds(80, 13, 329, 78);
		frame.getContentPane().add(lblShout);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginScreen l = new LoginScreen();
				l.show();
				frame.dispose();
			}
		});
		btnLogin.setBounds(167, 117, 155, 39);
		frame.getContentPane().add(btnLogin);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					System.out.println("Clicking sign-up");
					SignUpScreen S = new SignUpScreen();
					signUpScreenExists = true;
			}
		});
		btnSignUp.setBounds(165, 190, 155, 39);
		frame.getContentPane().add(btnSignUp);
		
		JLabel lblCopyrightShoutLtd = new JLabel("Copyright Shout! Ltd, 2018 - Not a Yelp ripoff, we promise");
		lblCopyrightShoutLtd.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyrightShoutLtd.setBounds(75, 306, 340, 16);
		frame.getContentPane().add(lblCopyrightShoutLtd);
	}
}
