package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import model.Business;
import model.User;

public class BusinessObject extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel mainList;
	private User u;
	
	private ArrayList<JPanel> activePanels;
	
	public BusinessObject(User u) {
		this.u = u;
		activePanels = new ArrayList<>();
		setLayout(new BorderLayout());

		mainList = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		mainList.add(new JPanel(), gbc);

		add(new JScrollPane(mainList));

		/**JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				panel.add(new JLabel("Hello"));
				panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				gbc.weightx = 1;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				mainList.add(panel, gbc, 0);

				validate();
				repaint();
			}
		});

		add(add, BorderLayout.SOUTH);**/
	}
	
	public void addBusiness(Business b) {
		GridLayout gLayout = new GridLayout(0, 3);
		JPanel panel = new JPanel(gLayout);
		//lblUsername.setHorizontalAlignment(SwingConstants.CENTER);;
		panel.add(new JLabel(b.getName(), SwingConstants.CENTER));
		panel.add(new JLabel(b.getAddress(), SwingConstants.CENTER));
		panel.add(new JLabel(b.getCategory(), SwingConstants.CENTER));
		panel.add(new JLabel(""));
		
		JButton butt = new JButton("Go to business page");
		butt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BusinessScreen bs = new BusinessScreen(b, u);
				bs.show();
			}
		});
		
		panel.add(butt);
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