package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import model.User;

public class FriendsObject extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private JPanel mainList;
	private User u;

	private HomeScreen h;
	private ArrayList<JPanel> activePanels;
	
	public FriendsObject(User u, HomeScreen h) {
		this.u = u;
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
	
		
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.gridwidth = GridBagConstraints.REMAINDER;
		gbc1.weightx = 1;
		gbc1.fill = GridBagConstraints.HORIZONTAL;
		gbc1.weighty = .05;
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("Test", SwingConstants.CENTER));
		panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
		mainList.add(panel, gbc1, 0);
								
		validate();
		repaint();
	}
	
	public void showFriends()
	{
		
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
