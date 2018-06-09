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

import model.Review;

public class ReviewObject extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel mainList;

	private ArrayList<JPanel> activePanels;
	
	public ReviewObject() {
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
	
	public void addReview(String username, int stars, String text, Timestamp date) {
		GridLayout gLayout = new GridLayout(0, 3);
		GridLayout tLayout = new GridLayout(0, 1);
		tLayout.setVgap(5);
		tLayout.setHgap(10);
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel topPanel = new JPanel(gLayout);
		
		topPanel.add(new JLabel(username, SwingConstants.LEADING));
		System.out.println("username");
		
		StarRater r = new StarRater(5, stars);
		r.setEnabled(false);
		r.setAlignmentX(Component.CENTER_ALIGNMENT);
		topPanel.add(r);
		long hour = 3600 * 1000;
		Timestamp tFinal = new Timestamp(date.getTime() + 7 * hour);
		topPanel.add(new JLabel(tFinal.toString(), SwingConstants.TRAILING));
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		JTextArea t = new JTextArea(text);
		t.setEditable(false);
		t.setLineWrap(true);
		bottomPanel.add(t);
		
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(bottomPanel, BorderLayout.SOUTH);
		
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