package gui;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.Client;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FriendReviewScreen {

	private JFrame frame;

	private ReviewObject ro;
	private int uid;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FriendReviewScreen window = new FriendReviewScreen(1);
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
	public FriendReviewScreen(int uid) {
		this.uid = uid;
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
		frame.setBounds(100, 100, 450, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ro = new ReviewObject();
		ro.setLocation(12, 13);
		ro.setSize(408, 303);
		frame.getContentPane().add(ro);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnBack.setBounds(12, 329, 408, 36);
		frame.getContentPane().add(btnBack);
		
		loadReviews();
	}
	
	public void loadReviews() {
		String sql = "select U.username, R.stars, R.text, R.date from review R, user U where R.bid = " + uid + " and R.uid = U.uid";
		try {
			Statement stmt = Client.getConnection().createStatement();
			ResultSet r = stmt.executeQuery(sql);
			
			while(r.next()) {
				String username = r.getString("username");
				int stars = r.getInt("stars");
				String text = r.getString("text");
				Timestamp d = r.getTimestamp("date");
				ro.addReview(username, stars, text, d);
				System.out.println(username + " " + text);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

}
