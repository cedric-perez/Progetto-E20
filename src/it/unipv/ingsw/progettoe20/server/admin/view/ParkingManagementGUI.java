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
	private JTextField field1, field2;
	private JLabel title, inserthere1, inserthere2;
	private JPanel panel, panel2, panel3;
	private JButton confirm, home;
	private ParkingListener listener;

	public ParkingManagementGUI() {

		panel = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		title = new JLabel("PARKING MANAGEMENT");
		inserthere1 = new JLabel("Level name:  ");
		inserthere2 = new JLabel("Parking lots:");
		field1 = new JTextField(10);
		field2 = new JTextField(10);
		confirm = new JButton("Confirm");
		home = new JButton("Home");

		String[] items = { "Add parkings", "Remove parkings" };
		combo = new JComboBox<>(items);

		// frame settings
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(600, 400);
		setLocation(340, 240);
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
		inserthere1.setFont(new Font(Font.MONOSPACED, 1, 16));
		inserthere1.setForeground(new Color(196, 10, 255));
		inserthere1.setAlignmentX(LEFT_ALIGNMENT);

		// inserthere2 settings
		inserthere2.setFont(new Font(Font.MONOSPACED, 1, 16));
		inserthere2.setForeground(new Color(196, 10, 255));
		inserthere2.setAlignmentX(LEFT_ALIGNMENT);

		// confirm settings
		confirm.setFont(new Font(Font.MONOSPACED, 1, 20));
		confirm.setBackground(new Color(222, 177, 255));

		// home settings
		home.setFont(new Font(Font.MONOSPACED, 0, 10));
		home.setBackground(new Color(222, 177, 255));

		// field1 settings
		field1.setFont(new Font(Font.MONOSPACED, 0, 24));
		field1.setAlignmentX(RIGHT_ALIGNMENT);
		field1.setForeground(new Color(196, 10, 255));

		// field2 settings
		field2.setFont(new Font(Font.MONOSPACED, 0, 24));
		field2.setAlignmentX(RIGHT_ALIGNMENT);
		field2.setForeground(new Color(196, 10, 255));

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

		panel2.add(inserthere1);
		panel2.add(field1);

		panel3.add(inserthere2);
		panel3.add(field2);

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
		return field1;
	}

	public JTextField getField2() {
		return field2;
	}

	public JButton getConfirm() {
		return confirm;
	}

}
