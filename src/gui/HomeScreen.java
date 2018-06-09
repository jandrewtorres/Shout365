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
import model.Business;
import model.Review;
import model.User;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;

public class HomeScreen {
	
	/**
	 * 	select coalesce(full_reviews.count, 0) as num_reviews, coalesce(full_reviews.avg_stars, 0) as avg_stars, business.bid, business.name, business.address, business.city, business.state, business.postal_code, business.category
	 *	from
	 *	(select B1.bid as cbid, count_and_stars.count, count_and_stars.avg_stars
	 *	from business B1, (
	 *	select R.bid, count(*) as count, avg(R.stars) as avg_stars
	 *	from review R, business B
	 *	where B.bid = R.bid
	 *	group by R.bid) as count_and_stars
	 *	where B1.bid = count_and_stars.bid) as full_reviews
	 *	right join business on business.bid = full_reviews.cbid
	 */
	
	/**
	 * select * 
from (
select coalesce(full_reviews.count, 0) as num_reviews, coalesce(full_reviews.avg_stars, 0) as avg_stars, business.bid, business.name, business.address, business.city, business.state, business.postal_code, business.category
from
(select B1.bid as cbid, count_and_stars.count, count_and_stars.avg_stars
from business B1, (
select R.bid, count(*) as count, avg(R.stars) as avg_stars
from review R, business B
where B.bid = R.bid
group by R.bid) as count_and_stars
where B1.bid = count_and_stars.bid) as full_reviews
right join business on business.bid = full_reviews.cbid) as full_table
where full_table.category = 'American' and full_table.name like '%burg%' and full_table.num_reviews >= 4 and full_table.avg_stars >= 4
	 */

	private JFrame frame;
	private User u;
	private JTextField emailField;
	private JTextField pwordField;
	private JTextField restaurantNameField;
	
	private StarRater starRater;
	private JSpinner rSpinner;
	private JComboBox categoryBox;
	private JCheckBox exactChck;
	private JScrollPane scrollPane;
	private BusinessObject b;
	private JTextField rNameField;
	private JTextField rAddressField;
	private JTextField rCityField;
	private JTextField rPostalCode;
	private JComboBox rCategoryBox;
	private JComboBox rStateBox; 
	private YourReviewsObject yro;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User u = new User(0, "test", new Timestamp(System.currentTimeMillis()), "test@test", "test", new Timestamp(System.currentTimeMillis()));
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
		model.setMinimum(0);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 974, 686);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 953, 639);
		frame.getContentPane().add(tabbedPane);
		
		JPanel homePanel = new JPanel();
		tabbedPane.addTab("Home", null, homePanel, null);
		homePanel.setLayout(null);
		
		JLabel lblGreetings = new JLabel("Greetings, " +  u.getUsername());
		lblGreetings.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
		lblGreetings.setBounds(12, 13, 344, 62);
		homePanel.add(lblGreetings);
		
		System.out.println(u.getLastAccess().getTime());
		
		long hour = 3600 * 1000;
		Timestamp t = new Timestamp(u.getLastAccess().getTime() + 7 * hour);
		
		JLabel lblYouLastAccessed = new JLabel("You last accessed Shout! on " + t);
		lblYouLastAccessed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblYouLastAccessed.setBounds(12, 88, 532, 22);
		homePanel.add(lblYouLastAccessed);
		
		yro = new YourReviewsObject(this);
		yro.setLocation(12, 229);
		yro.setSize(924, 367);
		homePanel.add(yro);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 139, 948, 2);
		homePanel.add(separator_3);
		
		JLabel lblYourReviews = new JLabel("Your Reviews");
		lblYourReviews.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
		lblYourReviews.setBounds(12, 154, 344, 62);
		homePanel.add(lblYourReviews);
		
		JPanel searchPanel = new JPanel();
		tabbedPane.addTab("Search Restaurants", null, searchPanel, null);
		searchPanel.setLayout(null);
		
		JLabel lblRestaurantName = new JLabel("Name:");
		lblRestaurantName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRestaurantName.setBounds(12, 76, 77, 35);
		searchPanel.add(lblRestaurantName);
		
		restaurantNameField = new JTextField();
		restaurantNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(!restaurantNameField.getText().isEmpty()) {
					//starRater.setEnabled(false);
					//rSpinner.setEnabled(false);
					
				}
			}
		});
		restaurantNameField.setToolTipText("Leave blank for wildcard");
		restaurantNameField.setBounds(209, 82, 247, 22);
		searchPanel.add(restaurantNameField);
		restaurantNameField.setColumns(10);
		
		JPanel raterPanel = new JPanel();
		starRater = new StarRater(5,0,0);	
		starRater.setLocation(-1, 142);
		raterPanel.add(starRater);
		raterPanel.setBounds(209, 139, 77, 22);
		searchPanel.add(raterPanel);
		
		JLabel lblRating = new JLabel("Rating At Least:");
		lblRating.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRating.setBounds(12, 139, 185, 22);
		searchPanel.add(lblRating);
		
		JLabel lblAtLeast = new JLabel("At Least ");
		lblAtLeast.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAtLeast.setBounds(494, 76, 105, 35);
		searchPanel.add(lblAtLeast);
		rSpinner = new JSpinner(model);
		rSpinner.setValue(0);
		rSpinner.setBounds(626, 75, 38, 36);
		searchPanel.add(rSpinner);
		
		JLabel lblReviews = new JLabel("Reviews");
		lblReviews.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReviews.setBounds(739, 76, 185, 35);
		searchPanel.add(lblReviews);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int stars = starRater.getSelection();
				String name = restaurantNameField.getText();
				int spinnerVal = (int) rSpinner.getValue();
				String category = (String) categoryBox.getSelectedItem();
				boolean exactChecked = exactChck.isSelected();
				b.clearEntries();
				System.out.println("Name: " + name + ", stars: " + stars + ", spinner val: " + spinnerVal + ", category: " + category);
				
				if(exactChecked) {
					try {
						Statement stmt = Client.getConnection().createStatement();
						String sql = "select * from business where name = '" + name + "'";
						System.out.println(sql);
						ResultSet r  = stmt.executeQuery(sql);
						while(r.next()) {
							int rBid = r.getInt("bid");
							String rName = r.getString("name");
							String rAddress = r.getString("address");
							String rCity = r.getString("city");
							String rState = r.getString("state");
							String rPC = r.getString("postal_code");
							String rCategory = r.getString("category");
							Business biz = new Business(rBid, rName, rAddress, rCity, rState, rPC, rCategory);
							b.addBusiness(biz);
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					StringBuilder sql = new StringBuilder();
					sql.append("select * from business B, review R where");
					if(!name.isEmpty()) {
						sql.append(" name like '%" + name + "%' and");
					}
					if(!category.equals("")) {
						sql.append(" category = '" + category + "' and");
					}
					
					StringBuilder fullBuilder = new StringBuilder();
					fullBuilder.append("select coalesce(full_reviews.count, 0) as num_reviews, coalesce(full_reviews.avg_stars, 0) as avg_stars, business.bid, business.name, business.address, business.city, business.state, business.postal_code, business.category ");
					fullBuilder.append("from ");
					fullBuilder.append("(select B1.bid as cbid, count_and_stars.count, count_and_stars.avg_stars ");
					fullBuilder.append("from business B1, ( ");
					fullBuilder.append("select R.bid, count(*) as count, avg(R.stars) as avg_stars ");
					fullBuilder.append("from review R, business B ");
					fullBuilder.append("where B.bid = R.bid ");
					fullBuilder.append("group by R.bid) as count_and_stars ");
					fullBuilder.append("where B1.bid = count_and_stars.bid) as full_reviews ");
					fullBuilder.append("right join business on business.bid = full_reviews.cbid");
					String builderString = fullBuilder.toString();
					String fullSql;
					if(!name.isEmpty() || !category.equals("") || spinnerVal > 0 || stars > 0) {
						StringBuilder finalBuilder = new StringBuilder();
						finalBuilder.append("select * ");
						finalBuilder.append("from ( ");
						finalBuilder.append(builderString);
						finalBuilder.append(") as full_table ");
						finalBuilder.append("where ");
						if(!category.equals("")) {
							finalBuilder.append("full_table.category = '");
							finalBuilder.append(category);
							finalBuilder.append("'");
							if(!name.isEmpty() || spinnerVal > 0 || stars > 0) {
								finalBuilder.append(" and ");
							}
						}
						if(!name.isEmpty()) {
							finalBuilder.append("full_table.name like '%");
							finalBuilder.append(name);
							finalBuilder.append("%'");
							if(spinnerVal > 0 || stars > 0) {
								finalBuilder.append(" and ");
							}
						}
						if(spinnerVal > 0) {
							finalBuilder.append("full_table.num_reviews >= ");
							finalBuilder.append(spinnerVal);
							if(stars > 0) {
								finalBuilder.append(" and ");
							}
						}
						if(stars > 0) {
							finalBuilder.append("full_table.avg_stars >= ");
							finalBuilder.append(stars);
						}
						fullSql = finalBuilder.toString();
					} else {
						System.out.println("Complete wildcard search");
						fullSql = builderString;
					}
					System.out.println(fullSql);
					try {
						Statement stmt = Client.getConnection().createStatement();
						ResultSet r = stmt.executeQuery(fullSql);
						int results = 0;
						while(r.next()) {
							int rBid = r.getInt("bid");
							String rName = r.getString("name");
							String rAddress = r.getString("address");
							String rCity = r.getString("city");
							String rState = r.getString("state");
							String rPC = r.getString("postal_code");
							String rCategory = r.getString("category");
							Business biz = new Business(rBid, rName, rAddress, rCity, rState, rPC, rCategory);
							b.addBusiness(biz);
							results++;
						}
						System.out.println(results);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnSearch.setBounds(338, 187, 272, 50);
		searchPanel.add(btnSearch);
		
		JLabel lblSearchFilters = new JLabel("Restaurant Search Filters");
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
		
		
		
		
		
		
//		scrollPane = new JScrollPane();
//		scrollPane.setBounds(12, 325, 924, 271);
//		searchPanel.add(scrollPane);
		
		b = new BusinessObject(u);
		b.setBounds(12, 325, 924, 271);
		searchPanel.add(b);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(473, 50, 9, 124);
		searchPanel.add(separator_2);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCategory.setBounds(494, 133, 105, 35);
		searchPanel.add(lblCategory);
		
		categoryBox = new JComboBox(Client.RESTAURANT_CATEGORIES);
		categoryBox.setToolTipText("Leave blank for any category");
		categoryBox.setBounds(626, 139, 205, 22);
		searchPanel.add(categoryBox);
		
		exactChck = new JCheckBox("Exact");
		exactChck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(exactChck.isSelected()) {
					rSpinner.setEnabled(false);
					starRater.setEnabled(false);
					categoryBox.setEnabled(false);
				} else {
					rSpinner.setEnabled(true);
					starRater.setEnabled(true);
					categoryBox.setEnabled(true);
				}
			}
		});
		exactChck.setBounds(131, 76, 70, 35);
		searchPanel.add(exactChck);
		
		JButton btnNewButton = new JButton("Clear Stars");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				starRater.setSelection(0);
			}
		});
		btnNewButton.setBounds(338, 138, 118, 25);
		searchPanel.add(btnNewButton);
		
		JPanel reviewPanel = new JPanel();
		tabbedPane.addTab("Add Restaurant", null, reviewPanel, null);
		reviewPanel.setLayout(null);
		
		JLabel lblInsertRestaurantInformation = new JLabel("Insert Restaurant Information");
		lblInsertRestaurantInformation.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
		lblInsertRestaurantInformation.setBounds(12, 13, 344, 62);
		reviewPanel.add(lblInsertRestaurantInformation);
		
		JLabel label_1 = new JLabel("Name:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_1.setBounds(12, 88, 77, 35);
		reviewPanel.add(label_1);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddress.setBounds(12, 136, 111, 35);
		reviewPanel.add(lblAddress);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCity.setBounds(12, 184, 77, 35);
		reviewPanel.add(lblCity);
		
		JLabel lblState = new JLabel("State:");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblState.setBounds(12, 232, 77, 35);
		reviewPanel.add(lblState);
		
		JLabel lblPostalCode = new JLabel("Postal Code:");
		lblPostalCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPostalCode.setBounds(12, 280, 141, 35);
		reviewPanel.add(lblPostalCode);
		
		JLabel lblFoodCategory = new JLabel("Food Category:");
		lblFoodCategory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFoodCategory.setBounds(12, 328, 167, 35);
		reviewPanel.add(lblFoodCategory);
		
		rNameField = new JTextField();
		rNameField.setBounds(264, 94, 241, 22);
		reviewPanel.add(rNameField);
		rNameField.setColumns(10);
		
		rAddressField = new JTextField();
		rAddressField.setColumns(10);
		rAddressField.setBounds(264, 142, 241, 22);
		reviewPanel.add(rAddressField);
		
		rCityField = new JTextField();
		rCityField.setColumns(10);
		rCityField.setBounds(264, 190, 241, 22);
		reviewPanel.add(rCityField);
		
		rPostalCode = new JTextField();
		rPostalCode.setColumns(10);
		rPostalCode.setBounds(264, 286, 241, 22);
		reviewPanel.add(rPostalCode);
		
		rCategoryBox = new JComboBox(Client.NO_DEFAULT_RESTAURANT_CATEGORIES);
		rCategoryBox.setBounds(264, 334, 241, 22);
		reviewPanel.add(rCategoryBox);
		
		JButton btnAddRestaurant = new JButton("Add Restaurant");
		btnAddRestaurant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String rName = rNameField.getText();
				String rAddress = rAddressField.getText();
				String rCity = rCityField.getText();
				String rPC = rPostalCode.getText();
				String rCategory = (String) rCategoryBox.getSelectedItem();
				String rState = (String) rStateBox.getSelectedItem();
				if(!rName.isEmpty() && !rAddress.isEmpty() && !rCity.isEmpty() && !rPC.isEmpty() && !rCategory.isEmpty() && !rState.isEmpty()) {
					String sql = "insert into business(name, address, city, state, postal_code, category) values ('" + rName + "','" + rAddress + "','" + rCity + "','" + rState + "','" + rPC + "','" + rCategory + "')";
					try {
						System.out.println(sql);
						Statement stmt = Client.getConnection().createStatement();
						stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(new JFrame(), "Restaurant successfully added", "", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "All fields must be filled out", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddRestaurant.setBounds(338, 394, 272, 50);
		reviewPanel.add(btnAddRestaurant);
		
		rStateBox = new JComboBox(Client.STATES);
		rStateBox.setBounds(264, 238, 241, 22);
		reviewPanel.add(rStateBox);
		
		
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
		
		Timestamp t12 = new Timestamp(u.getJoined().getTime() + 7 * hour);
		
		JLabel djLbl = new JLabel(t12.toString());
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
		
		loadHomeReviews();
	}
	
	public void loadHomeReviews() {
		yro.clearEntries();
		
		String sql = "select B.name, R.rid, R.stars, R.date, R.text from review R, business B where R.uid = " + u.getUid() + " and R.bid = B.bid";
		try {
			Statement stmt = Client.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String name = rs.getString("name");
				int rid = rs.getInt("rid");
				int stars = rs.getInt("stars");
				Timestamp t = rs.getTimestamp("date");
				String text = rs.getString("text");
				
				Review r = new Review(rid, -1, -1, stars, t, text);
				yro.addReview(r, name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
