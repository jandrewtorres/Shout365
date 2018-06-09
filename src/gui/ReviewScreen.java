package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.Business;
import model.User;

import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import client.Client;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReviewScreen {

	private JFrame frame;
	private Business b;
	private User u;
	private BusinessScreen bs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Business b = new Business(1, "The Burger Joint", "12345 Main St", "Los Angeles", "CA", "90410", "American");
					User u = new User(0, "test", new Timestamp(System.currentTimeMillis()), "test@test", "test",  new Timestamp(System.currentTimeMillis()));
					ReviewScreen window = new ReviewScreen(b, u, null);
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
	public ReviewScreen(Business b, User u, BusinessScreen bs) {
		this.b = b;
		this.u = u;
		this.bs = bs;
		initialize();
	}
	
	public void show() {
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 424);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblReviewing = new JLabel("Reviewing " +  b.getName());
		lblReviewing.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
		lblReviewing.setBounds(12, 13, 344, 62);
		frame.getContentPane().add(lblReviewing);
		
		StarRater avgRater = new StarRater(5);
		avgRater.setLocation(340, 94);
		avgRater.setSize(80, 23);
		frame.getContentPane().add(avgRater);
		
		JLabel lblRatingrequired = new JLabel("Rating (required):");
		lblRatingrequired.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRatingrequired.setBounds(12, 88, 210, 35);
		frame.getContentPane().add(lblRatingrequired);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 68, 432, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblComments = new JLabel("Comments:");
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblComments.setBounds(12, 136, 210, 35);
		frame.getContentPane().add(lblComments);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 184, 408, 134);
		frame.getContentPane().add(textArea);
		
		JButton btnSubmitReview = new JButton("Submit Review");
		btnSubmitReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int stars = avgRater.getSelection();
				if(stars > 0) {
					try {
						String sql = "insert into review(bid, uid, stars, date, text) values (" + b.getbID() + "," + u.getUid() + "," + stars + ",'" + new Timestamp(System.currentTimeMillis()) + "','" + textArea.getText() + "')";
						System.out.println(sql);
						Statement stmt = Client.getConnection().createStatement();
						stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(new JFrame(), "Review successfully submitted", "", JOptionPane.INFORMATION_MESSAGE);
						bs.loadReviews();
						bs.getHS().loadHomeReviews();
						bs.frame.validate();
						bs.frame.repaint();
						frame.dispose();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "At least one star required", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSubmitReview.setBounds(99, 331, 234, 32);
		frame.getContentPane().add(btnSubmitReview);
	}
}
