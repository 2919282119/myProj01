package com.example.myproj01.JClass;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.Timer;

public class Loads extends JPanel {
    LoadBack loadBack;
    Timer timer;
    Loads(){
        File f = new File("src\\main\\java\\com\\example\\myproj01\\Txt");
        File[] files = f.listFiles((dir, name) -> name.startsWith("match") && name.endsWith(".txt"));

        setLayout(new GridLayout(0, 1)); // 设置网格布局，纵向排列
        setBorder(new EmptyBorder(10, 100, 10, 100)); // 设置面板的边框，左右边距为20


        for (int i = 0; i < files.length; i++) {
            JButton button = new JButton(files[i].getName().replaceAll(".txt",""));
            int finalI = i;
            button.addActionListener(e->{
                loadBack.onLoadBack(files[finalI]);
            });
            button.setPreferredSize(new Dimension(300, 40));
            button.setMargin(new Insets(20,40,20,40));
            add(button);
            add(Box.createVerticalStrut(10)); // 添加垂直间距
        }

        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
}
