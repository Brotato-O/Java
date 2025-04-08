/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.awt.Color;

import javax.swing.JFrame;

import GUI.view.EmployeeManagement;
import GUI.view.GiamGia;
import GUI.view.QLHD;
import GUI.view.SupermarketUI;
import GUI.view.loginForm;

public class main extends JFrame {
	public static int width = 400;
	public static int height = 500;

	public main() {
		setSize(width, height);
		getContentPane().setBackground(Color.decode("#cdffff"));
                add(new loginForm());
//		add(new EmployeeManagement());
//		add(new CustomerManagement());
//		add(new QLHD());
//                add(new GiamGia());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		        setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
//		        new main();

		SupermarketUI sm = new SupermarketUI();
		sm.createAndShowGUI();
		sm.getSuperMarketUI();
	}
}
