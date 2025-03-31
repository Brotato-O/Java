/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.awt.Color;

import javax.swing.JFrame;

import GUI.CustomerManagement;
import GUI.QLHD;
import GUI.SupermarketUI;

public class main extends JFrame {
	public static int width = 400;
	public static int height = 500;

	public main() {
		setSize(width, height);
		getContentPane().setBackground(Color.decode("#cdffff"));
		add(new CustomerManagement());
		add(new QLHD());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//        setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		//        new main();

		SupermarketUI sm = new SupermarketUI();
		sm.createAndShowGUI();
		sm.getSuperMarketUI();
	}
}
