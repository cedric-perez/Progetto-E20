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

import it.unipv.ingsw.progettoe20.server.admin.controller.PriceListener;

/*
 * Classe per la gestione delle tariffe.
 * Permette di modificare i valori delle diverse tariffe disponibili.
 */

public class PriceManagementGUI extends JFrame {

	private JComboBox<String> combo;
	private JTextField price;
	private JLabel title, insertherePrice;
	private JPanel panel, panel2;
	private JButton confirm, home;
	private PriceListener listener;

	public PriceManagementGUI() {

		panel = new JPanel();
		panel2 = new JPanel();
		title = new JLabel("PRICE MANAGEMENT");
		insertherePrice = new JLabel("Price:  ");
		price = new JTextField(10);
		confirm = new JButton("Change");
		home = new JButton("Home");

		String[] items = { "Hourly price", "Maximum price", "Minimum price" };
		combo = new JComboBox<>(items);

		// frame settings
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setTitle("PRICE MANAGEMENT GUI");

		// panel settings
		panel.setBackground(new Color(30, 30, 30));
		panel.setLayout(null);

		// panel2 settings
		panel2.setBackground(new Color(30, 30, 30));
		panel2.setLayout(new FlowLayout());

		// title settings
		title.setFont(new Font(Font.MONOSPACED, 3, 30));
		title.setForeground(new Color(196, 10, 255));

		// inserthere settings
		insertherePrice.setFont(new Font(Font.MONOSPACED, 1, 16));
		insertherePrice.setForeground(new Color(196, 10, 255));
		insertherePrice.setAlignmentX(LEFT_ALIGNMENT);

		// confirm settings
		confirm.setFont(new Font(Font.MONOSPACED, 1, 20));
		confirm.setBackground(new Color(222, 177, 255));

		// goback settings
		home.setFont(new Font(Font.MONOSPACED, 0, 10));
		home.setBackground(new Color(222, 177, 255));

		// textfield settings
		price.setFont(new Font(Font.MONOSPACED, 0, 12));
		price.setAlignmentX(RIGHT_ALIGNMENT);
		price.setForeground(new Color(196, 10, 255));

		// combobox settings
		combo.setFont(new Font(Font.MONOSPACED, 0, 16));

		// locations settings
		title.setBounds(275, 10, 500, 40);
		confirm.setBounds(190, 230, 200, 40);
		panel2.setBounds(140, 180, 300, 30);
		combo.setBounds(140, 130, 300, 30);
		home.setBounds(25, 20, 100, 20);

		// add
		panel.add(title);
		panel.add(combo);
		panel.add(panel2);
		panel.add(confirm);
		panel.add(home);

		panel2.add(insertherePrice);
		panel2.add(price);

		// listener settings
		listener = new PriceListener(this);
		confirm.addActionListener(listener);
		home.addActionListener(listener);
	}

	public JComboBox<String> getCombo() {
		return combo;
	}

	public JTextField getField() {
		return price;
	}

	public JButton getConfirm() {
		return confirm;
	}

	public JButton getHome() {
		return home;
	}

}
