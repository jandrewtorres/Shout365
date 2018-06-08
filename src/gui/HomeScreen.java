package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import classes.User;
import client.Client;

import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeScreen {

	private JFrame frame;
	private User u;
	private JTextField emailField;
	private JTextField pwordField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User u = new User("test", new Date(new Timestamp(System.currentTimeMillis()).getTime()), "test@test", "test");
					HomeScreen window = new HomeScreen(u);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeScreen(User u) {
		this.u = u;
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
		frame.setBounds(100, 100, 974, 686);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 953, 639);
		frame.getContentPane().add(tabbedPane);
		
		JPanel homePanel = new JPanel();
		tabbedPane.addTab("Home", null, homePanel, null);
		
		JPanel searchPanel = new JPanel();
		tabbedPane.addTab("Search Restaurants", null, searchPanel, null);
		
		JPanel reviewPanel = new JPanel();
		tabbedPane.addTab("Write Review", null, reviewPanel, null);
		
		JPanel userInfoPanel = new JPanel();
		tabbedPane.addTab("User Information", null, userInfoPanel, null);
		userInfoPanel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(12, 88, 134, 35);
		userInfoPanel.add(lblUsername);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 308, 948, 8);
		userInfoPanel.add(separator);
		
		JLabel lblCurrentUserInformation = new JLabel("Current User Information");
		lblCurrentUserInformation.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
		lblCurrentUserInformation.setBounds(12, 13, 344, 62);
		userInfoPanel.add(lblCurrentUserInformation);
		
		JLabel label = new JLabel(u.getUsername());
		label.setForeground(new Color(25, 25, 112));
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(168, 88, 378, 35);
		userInfoPanel.add(label);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(12, 147, 134, 35);
		userInfoPanel.add(lblEmail);
		
		JLabel lblDateJoined = new JLabel("Date Joined:");
		lblDateJoined.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDateJoined.setBounds(12, 203, 134, 35);
		userInfoPanel.add(lblDateJoined);
		
		JLabel emailLbl = new JLabel(u.getEmail());
		emailLbl.setForeground(new Color(25, 25, 112));
		emailLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		emailLbl.setBounds(168, 147, 378, 35);
		userInfoPanel.add(emailLbl);
		
		JLabel djLbl = new JLabel(u.getJoined().toString());
		djLbl.setForeground(new Color(25, 25, 112));
		djLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		djLbl.setBounds(168, 203, 378, 35);
		userInfoPanel.add(djLbl);
		
		JLabel lblChangeUserSettings = new JLabel("Change User Settings");
		lblChangeUserSettings.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
		lblChangeUserSettings.setBounds(12, 329, 344, 62);
		userInfoPanel.add(lblChangeUserSettings);
		
		JLabel lblNewUsername = new JLabel("New E-mail:");
		lblNewUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewUsername.setBounds(12, 406, 134, 35);
		userInfoPanel.add(lblNewUsername);
		
		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewPassword.setBounds(12, 478, 147, 35);
		userInfoPanel.add(lblNewPassword);
		
		emailField = new JTextField();
		emailField.setToolTipText("Leave blank to keep the same");
		emailField.setBounds(209, 419, 201, 22);
		userInfoPanel.add(emailField);
		emailField.setColumns(10);
		
		pwordField = new JTextField();
		pwordField.setToolTipText("Leave blank to keep the same");
		pwordField.setBounds(209, 487, 201, 22);
		userInfoPanel.add(pwordField);
		pwordField.setColumns(10);
		
		JButton btnApplyChanges = new JButton("Apply Changes");
		btnApplyChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!emailField.getText().isEmpty() || !pwordField.getText().isEmpty()) {
					StringBuilder sqlBuilder = new StringBuilder();
					sqlBuilder.append("update user ");
					if(!emailField.getText().isEmpty()) {
						sqlBuilder.append("set email = '" + emailField.getText() + "'");
					}
					if(!pwordField.getText().isEmpty()) {
						if(!emailField.getText().isEmpty()) {
							sqlBuilder.append(", password = '" + pwordField.getText() + "'");
						} else {
							sqlBuilder.append("set password = '" + pwordField.getText() + "'");
						}
					}
					sqlBuilder.append(" where username = '" + u.getUsername() + "'");
					String sql = sqlBuilder.toString();
					System.out.println(sql);
					try {
						Statement stmt = Client.getConnection().createStatement();
						stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(new JFrame(), "Account changes made successfully", "", JOptionPane.INFORMATION_MESSAGE);
						emailField.setText("");
						pwordField.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else  {
					JOptionPane.showMessageDialog(new JFrame(), "No changes to be made", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnApplyChanges.setBounds(338, 546, 272, 50);
		userInfoPanel.add(btnApplyChanges);
	}
}
