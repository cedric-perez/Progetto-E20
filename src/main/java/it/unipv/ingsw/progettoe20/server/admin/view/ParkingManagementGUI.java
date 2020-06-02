package it.unipv.ingsw.progettoe20.server.admin.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.unipv.ingsw.progettoe20.server.admin.controller.ParkingListener;

/*
 * Classe per la gestione del parcheggi.
 * Si può modificare il numero di parcheggi disponibili nella struttura.
 */
public class ParkingManagementGUI extends JFrame {

	private JComboBox<String> combo;
	private JTextField levelname, parkinglots;
	private JLabel title, inserthereLevel, insertherePLots;
	private JPanel panel, panel2, panel3;
	private JButton confirm, home;
	private ParkingListener listener;

	public ParkingManagementGUI() {

		panel = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		title = new JLabel("PARKING MANAGEMENT");
		inserthereLevel = new JLabel("Level name:  ");
		insertherePLots = new JLabel("Parking lots:");
		levelname = new JTextField(10);
		parkinglots = new JTextField(10);
		confirm = new JButton("Confirm");
		home = new JButton("Home");

		String[] items = { "Add parkings", "Remove parkings" };
		combo = new JComboBox<>(items);

		// frame settings
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setTitle("PARKING MANAGEMENT GUI");

		// panel settings
		panel.setBackground(new Color(30, 30, 30));
		panel.setLayout(null);

		// panel2 settings
		panel2.setBackground(new Color(30, 30, 30));
		panel2.setLayout(new FlowLayout());

		// panel3 settings
		panel3.setBackground(new Color(30, 30, 30));
		panel3.setLayout(new FlowLayout());

		// title settings
		title.setFont(new Font(Font.MONOSPACED, 3, 30));
		title.setForeground(new Color(196, 10, 255));

		// inserthere1 settings
		inserthereLevel.setFont(new Font(Font.MONOSPACED, 1, 16));
		inserthereLevel.setForeground(new Color(196, 10, 255));
		inserthereLevel.setAlignmentX(LEFT_ALIGNMENT);

		// inserthere2 settings
		insertherePLots.setFont(new Font(Font.MONOSPACED, 1, 16));
		insertherePLots.setForeground(new Color(196, 10, 255));
		insertherePLots.setAlignmentX(LEFT_ALIGNMENT);

		// confirm settings
		confirm.setFont(new Font(Font.MONOSPACED, 1, 20));
		confirm.setBackground(new Color(222, 177, 255));

		// home settings
		home.setFont(new Font(Font.MONOSPACED, 0, 10));
		home.setBackground(new Color(222, 177, 255));

		// field1 settings
		levelname.setFont(new Font(Font.MONOSPACED, 0, 12));
		levelname.setAlignmentX(RIGHT_ALIGNMENT);
		levelname.setForeground(new Color(196, 10, 255));

		// field2 settings
		parkinglots.setFont(new Font(Font.MONOSPACED, 0, 12));
		parkinglots.setAlignmentX(RIGHT_ALIGNMENT);
		parkinglots.setForeground(new Color(196, 10, 255));

		// combobox settings
		combo.setFont(new Font(Font.MONOSPACED, 0, 16));

		// locations settings
		title.setBounds(250, 10, 500, 40);
		confirm.setBounds(190, 250, 200, 40);
		panel2.setBounds(140, 150, 300, 30);
		panel3.setBounds(140, 200, 300, 30);
		combo.setBounds(130, 100, 300, 30);
		home.setBounds(25, 20, 100, 20);

		panel.add(title);
		panel.add(combo);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(confirm);
		panel.add(home);

		panel2.add(inserthereLevel);
		panel2.add(levelname);

		panel3.add(insertherePLots);
		panel3.add(parkinglots);

		// listener settings
		listener = new ParkingListener(this);
		confirm.addActionListener(listener);
		home.addActionListener(listener);
	}

	public JComboBox<String> getCombo() {
		return combo;
	}

	public JButton getHome() {
		return home;
	}

	public JTextField getField() {
		return levelname;
	}

	public JTextField getField2() {
		return parkinglots;
	}

	public JButton getConfirm() {
		return confirm;
	}

}
