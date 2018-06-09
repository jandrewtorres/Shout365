package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import model.Business;
import model.Review;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import client.Client;

import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class BusinessScreen {

	JFrame frame;
	private Business b;
	private final JSeparator separator = new JSeparator();
	private User u;
	private ReviewObject rObject;
	private StarRater avgRater;
	
	private BusinessScreen me;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Business b = new Business(1, "The Burger Joint", "12345 Main St", "Los Angeles", "CA", "90410", "American");
					User u = new User(0, "test", new Timestamp(System.currentTimeMillis()), "test@test", "test", new Timestamp(System.currentTimeMillis()));
					BusinessScreen window = new BusinessScreen(b, u);
					window.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BusinessScreen(Business b, User u) {
		this.b = b;
		this.u = u;
		initialize();
		me = this;
	}
	
	public void show() {
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 639, 609);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label_1 = new JLabel(b.getAddress() + ", " + b.getCity() + " " + b.getState() + " " + b.getZIP());
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_1.setBounds(12, 88, 452, 35);
		frame.getContentPane().add(label_1);
		
		JLabel lblName = new JLabel(b.getCategory());
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(484, 88, 125, 35);
		frame.getContentPane().add(lblName);
		
		rObject = new ReviewObject();
		rObject.setLocation(12, 199);
		rObject.setSize(597, 265);
		frame.getContentPane().add(rObject);
		separator.setBounds(12, 136, 597, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblAverageRating = new JLabel("Average Rating:");
		lblAverageRating.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAverageRating.setBounds(12, 151, 210, 35);
		frame.getContentPane().add(lblAverageRating);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnBack.setBounds(196, 522, 234, 32);
		frame.getContentPane().add(btnBack);
		
		JLabel label = new JLabel(b.getName());
		label.setBounds(12, 13, 344, 62);
		frame.getContentPane().add(label);
		label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
		
		avgRater = new StarRater(5);
		avgRater.setLocation(529, 157);
		avgRater.setSize(80, 23);
		avgRater.setEnabled(false);
		frame.getContentPane().add(avgRater);
		
		JButton btnWriteReview = new JButton("Write Review");
		btnWriteReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReviewScreen rScreen = new ReviewScreen(b, u, me);
				rScreen.show();
			}
		});
		btnWriteReview.setBounds(196, 477, 234, 32);
		frame.getContentPane().add(btnWriteReview);
		
		loadReviews();
	}

	public void loadReviews() {
		int totalStars = 0;
		int totalReviews = 0;
		String sql = "select U.username, R.stars, R.text, R.date from review R, user U where R.bid = " + b.getbID() + " and R.uid = U.uid";
		rObject.clearEntries();
		try {
			Statement stmt = Client.getConnection().createStatement();
			ResultSet r = stmt.executeQuery(sql);
			
			while(r.next()) {
				String username = r.getString("username");
				int stars = r.getInt("stars");
				String text = r.getString("text");
				Timestamp d = r.getTimestamp("date");
				rObject.addReview(username, stars, text, d);
				totalReviews++;
				totalStars += stars;
			}
			
			avgRater.setRating((int) Math.floor((totalStars/ (double)totalReviews)));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
}
