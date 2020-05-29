package it.unipv.ingsw.progettoe20.client.enterColumn.model;

import java.io.IOException;

import javax.swing.JFrame;

import it.unipv.ingsw.progettoe20.client.enterColumn.controller.Controller;
import it.unipv.ingsw.progettoe20.client.enterColumn.view.EnterColumnGui;


public class test {
	
		public static void main(String[] args) throws IOException {
		 EnterColumn A1= new EnterColumn();
		 EnterColumnGui gui= new EnterColumnGui(A1);
		 A1.addObserver(gui);
		 Controller c= new Controller(gui, A1);
		 gui.setVisible(true);
		 gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     gui.pack(); 

		}
}
