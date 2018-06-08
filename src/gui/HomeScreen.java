package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import client.Client;
import model.User;

import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class HomeScreen {

	private JFrame frame;
	private User u;
	private JTextField emailField;
	private JTextField pwordField;
	private JTextField restaurantNameField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User u = new User("test", new Date(new Timestamp(System.currentTimeMillis()).getTime()), "test@test", "test");
					HomeScreen window = new HomeScreen(u);
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
		SpinnerNumberModel model = new SpinnerNumberModel();
		model.setMinimum(1);
		
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
		searchPanel.setLayout(null);
		
		JLabel lblRestaurantName = new JLabel("Restaurant Name:");
		lblRestaurantName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRestaurantName.setBounds(12, 76, 185, 35);
		searchPanel.add(lblRestaurantName);
		
		restaurantNameField = new JTextField();
		restaurantNameField.setToolTipText("Leave blank for wildcard");
		restaurantNameField.setBounds(209, 82, 247, 22);
		searchPanel.add(restaurantNameField);
		restaurantNameField.setColumns(10);
		
		JPanel raterPanel = new JPanel();
		StarRater starRater = new StarRater(5,0,0);
		starRater.addStarListener(new StarRater.StarListener() {
			
			@Override
			public void handleSelection(int selection) {
				System.out.println(selection);
				
			}
		});
		
		raterPanel.add(starRater);
		raterPanel.setBounds(209, 139, 77, 22);
		searchPanel.add(raterPanel);
		
		JLabel lblRating = new JLabel("Rating:");
		lblRating.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRating.setBounds(12, 139, 185, 22);
		searchPanel.add(lblRating);
		
		JLabel lblAtLeast = new JLabel("At Least ");
		lblAtLeast.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAtLeast.setBounds(494, 76, 105, 35);
		searchPanel.add(lblAtLeast);
		JSpinner spinner = new JSpinner(model);
		spinner.setValue(1);
		spinner.setBounds(626, 75, 38, 36);
		searchPanel.add(spinner);
		
		JLabel lblReviews = new JLabel("Reviews");
		lblReviews.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReviews.setBounds(739, 76, 185, 35);
		searchPanel.add(lblReviews);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(338, 187, 272, 50);
		searchPanel.add(btnSearch);
		
		JLabel lblSearchFilters = new JLabel("Search Filters");
		lblSearchFilters.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
		lblSearchFilters.setBounds(12, 13, 344, 62);
		searchPanel.add(lblSearchFilters);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 250, 948, 8);
		searchPanel.add(separator_1);
		
		JLabel lblResults = new JLabel("Results");
		lblResults.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
		lblResults.setBounds(12, 271, 344, 62);
		searchPanel.add(lblResults);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(115, 417, -79, 35);
		searchPanel.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 325, 924, 271);
		searchPanel.add(scrollPane_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(473, 50, 9, 124);
		searchPanel.add(separator_2);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCategory.setBounds(494, 133, 105, 35);
		searchPanel.add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(626, 139, 205, 22);
		searchPanel.add(comboBox);
		
		JPanel reviewPanel = new JPanel();
		tabbedPane.addTab("Add Restaurant", null, reviewPanel, null);
		
		
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
