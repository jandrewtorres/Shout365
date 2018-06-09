package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import client.Client;
import model.Review;

public class YourReviewsObject extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel mainList;
	private HomeScreen h; 

	private ArrayList<JPanel> activePanels;
	
	public YourReviewsObject(HomeScreen h) {
		this.h = h;
		activePanels = new ArrayList<>();
		setLayout(new BorderLayout());

		mainList = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		mainList.add(new JPanel(), gbc);

		add(new JScrollPane(mainList));
	}
	
	public void addReview(Review r, String bName) {
		GridLayout gLayout = new GridLayout(0, 4);
		JPanel panel = new JPanel(gLayout);
		panel.add(new JLabel(bName));
		StarRater r1 = new StarRater(5, r.getStars());
		r1.setEnabled(false);
		panel.add(r1);
		long hour = 3600 * 1000;
		Timestamp t = new Timestamp(r.getDate().getTime() + 7 * hour);
		panel.add(new JLabel(t.toString()));
		JButton b = new JButton("Remove Review");
		
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String sql = "delete from review where rid = " + r.getReviewID();
				try {
					Statement stmt = Client.getConnection().createStatement();
					stmt.executeUpdate(sql);
					h.loadHomeReviews();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		panel.add(b);
		panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		mainList.add(panel, gbc, 0);
		validate();
		repaint();
		activePanels.add(panel);
	}
	
	public void clearEntries() {
		for(JPanel j : activePanels) {
			mainList.remove(j);
		}
		validate();
		repaint();
		activePanels.clear();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}
}