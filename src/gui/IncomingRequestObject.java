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

public class IncomingRequestObject extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel mainList;
	private HomeScreen h; 

	private ArrayList<JPanel> activePanels;
	
	public IncomingRequestObject(HomeScreen h) {
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
	
	public void addIncomingRequest(String username_from, int uid_from) {
		GridLayout gLayout = new GridLayout(0, 3);
		JPanel panel = new JPanel(gLayout);
		panel.add(new JLabel(username_from));
			
		JButton a = new JButton("Accept");
		
		a.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String sql = "insert into friend values(" + h.getUser().getUid() + "," + uid_from + ", '" + h.getUser().getUsername() + "','" + username_from + "')";
				try {
					Statement stmt = Client.getConnection().createStatement();
					stmt.executeUpdate(sql);
					
					String del = "delete from friend_request where uid_from = " + uid_from + " and uid_to = " + h.getUser().getUid();
					Statement stmt2 = Client.getConnection().createStatement();
					stmt2.executeUpdate(del);
					h.loadIncoming();
					h.loadFriends();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		panel.add(a);
		
		JButton d = new JButton("Decline");
		
		d.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String del = "delete from friend_request where uid_from = " + uid_from + " and uid_to = " + h.getUser().getUid();
				Statement stmt2;
				try {
					stmt2 = Client.getConnection().createStatement();
					stmt2.executeUpdate(del);
					h.loadIncoming();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		panel.add(d);
		
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