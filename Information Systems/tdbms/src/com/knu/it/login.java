package com.knu.it;

import com.sun.javafx.tools.packager.Log;

import javax.swing.*;
import java.awt.*;

public class login {
    private JPanel panel;
    private JTextField loginTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel loginLabel;
    private JLabel passwordLabel;

    public login() {
        loginButton.addActionListener(e -> {
            System.out.println("Login: " + loginTextField.getText() + " and password: " + passwordField.getText());
        });
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("login");
        frame.setContentPane(new login().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setName("Login Window");
        frame.setVisible(true);
    }
}
