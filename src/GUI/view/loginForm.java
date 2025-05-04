/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
package GUI.view;
import BLL.EmpBLL;
import main.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.DimensionUIResource;
public class loginForm extends JPanel{
    
    int width= main.width;
    int height= main.height;
    JTextField account= new JTextField();
    JTextField password= new JTextField();
    JButton login= new JButton("Đăng nhập");
    JButton exit= new JButton("Thoát");

    public loginForm (){
        setLayout(new BorderLayout());
        
        JPanel leftPadding = new JPanel();
        leftPadding.setPreferredSize(new Dimension((int)((5.0/100) * width), 0));
        leftPadding.setOpaque(false);

        JPanel rightPadding = new JPanel();
        rightPadding.setPreferredSize(new Dimension((int)((5.0/100) * width), 0));
        rightPadding.setOpaque(false);

        add(leftPadding, BorderLayout.WEST);
        add(rightPadding, BorderLayout.EAST);
        
        add(mainLogin(), BorderLayout.CENTER);
        setBackground(Color.decode("#cdffff"));
    }

    public JPanel mainLogin(){
        
        account.setMaximumSize(new Dimension((int)(0.9* width), 0));
        account.setPreferredSize(new Dimension(0, 35)); 
        account.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setMaximumSize(new Dimension((int)(0.9* width), 20));
        password.setPreferredSize(new Dimension(0, 35)); 
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        
        JPanel p= new JPanel();
        p.setLayout(new BorderLayout());

        JPanel header= new JPanel(new FlowLayout(FlowLayout.CENTER));
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(20, 0, 50, 0));
        header.add(new JLabel("ĐĂNG NHẬP") {{ setFont(new Font("Arial", Font.BOLD, 18)); }});

        JPanel mainLogin= new JPanel();
        mainLogin.setLayout(new BoxLayout(mainLogin, BoxLayout.Y_AXIS));
        
        JPanel pAccount= new JPanel(new FlowLayout(FlowLayout.LEFT));
        pAccount.add(new JLabel("Tên đăng nhập") {{setFont(new Font("Arial", Font.PLAIN, 14));}});
        pAccount.setMaximumSize(new Dimension((int)(0.9* width), 0));
        mainLogin.add(pAccount);
        mainLogin.add(account);
        pAccount.setOpaque(false);
        
        mainLogin.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JPanel pPassword = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pPassword.add(new JLabel("Mật khẩu") {{setFont(new Font("Arial", Font.PLAIN, 14));}});
        pPassword.setMaximumSize(new Dimension((int)(0.9* width), 0));
        mainLogin.add(pPassword);
        mainLogin.add(password);
        pPassword.setOpaque(false);
        
        mainLogin.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel buttons= new JPanel();
        
        login.setBackground(Color.decode("#009900"));
        login.setPreferredSize(new Dimension(100, 40));
        buttons.add(login);
        
        exit.setBackground(Color.decode("#ff0100"));
        exit.setPreferredSize(new Dimension(100, 40));
        buttons.add(exit);
        buttons.setOpaque(false);

        mainLogin.add(buttons);
        mainLogin.setOpaque(false);
        
        p.add(header, BorderLayout.NORTH);
        p.add(mainLogin, BorderLayout.CENTER);
        p.setOpaque(false);
        
        
        login.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                EmpBLL empbll= new EmpBLL();
                String maNV= account.getText();
                String mk= password.getText();
                boolean rs= empbll.checkLogin(maNV, maNV);
                if (rs){
                    SupermarketUI sm = new SupermarketUI();
                    sm.createAndShowGUI();
                    sm.getSuperMarketUI();
                }
                else JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu sai");
            }
        });
        
        return p;
    }
}
