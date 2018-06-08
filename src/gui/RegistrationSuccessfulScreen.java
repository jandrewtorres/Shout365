package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrationSuccessfulScreen {

	private JFrame frame;
	/**
	 * Create the application.
	 */
	public RegistrationSuccessfulScreen() {
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRegistrationSuccessful = new JLabel("Registration successful!");
		lblRegistrationSuccessful.setForeground(new Color(0, 128, 0));
		lblRegistrationSuccessful.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrationSuccessful.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
		lblRegistrationSuccessful.setBounds(42, 13, 347, 71);
		frame.getContentPane().add(lblRegistrationSuccessful);
		
		JButton btnReturnTo = new JButton("Return to home screen");
		btnReturnTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnReturnTo.setBounds(76, 155, 283, 45);
		frame.getContentPane().add(btnReturnTo);
		
		JLabel lblYouMayNow = new JLabel("You may now log in on the home screen");
		lblYouMayNow.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouMayNow.setForeground(new Color(0, 128, 0));
		lblYouMayNow.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
		lblYouMayNow.setBounds(42, 71, 347, 71);
		frame.getContentPane().add(lblYouMayNow);
	}

}
