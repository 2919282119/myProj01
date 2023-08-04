package com.example.myproj01.JClass;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Login extends JPanel {
    JTextField txtUsername;
    JTextField txtPassword;
    JButton btnLogin;
    LoginSuccessListener loginListener;
    public ChessPlayer cpr;

    Login(){
        setLayout(null);
        Font newfont = new Font("楷体", Font.BOLD, 16);

        JLabel lblUsername = new JLabel("用户名:");
        lblUsername.setBounds(100, 100, 80, 30);
        lblUsername.setFont(newfont);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(200, 100, 200, 30);
        add(txtUsername);

        JLabel lblPassword = new JLabel("密码:");
        lblPassword.setBounds(100, 150, 80, 30);
        lblPassword.setFont(newfont);
        add(lblPassword);

        txtPassword = new JTextField();
        txtPassword.setBounds(200, 150, 200, 30);
        add(txtPassword);

        btnLogin = new JButton("登录");
        btnLogin.addActionListener(e->{
            String uname = txtUsername.getText();
            String pswd = txtPassword.getText();
            ArrayList<ChessPlayer> list = Functions.getAllPlayers();
            ChessPlayer cpr2 = new ChessPlayer("", uname, pswd, 0);
            boolean contains = list.contains(cpr2);
            if(contains){
                cpr = list.get(list.indexOf(cpr2));
                if(loginListener!=null){
                    loginListener.onLoginSuccess();
                }
            }


        });
        btnLogin.setFont(newfont);
        btnLogin.setBounds(200, 200, 100, 40);
        add(btnLogin);

    }

}
