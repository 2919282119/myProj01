package com.example.myproj01.JClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ChessGame extends JFrame {

    public Login login;
    private GameBoard gameBoard;
    private Loads loads;

    public ChessGame(int cpr) {
        setTitle("五子棋游戏");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500,400);

        login = new Login();
        gameBoard = new GameBoard(cpr);
        loads = new Loads();
        Container container = getContentPane();
        container.add(login);

        login.loginListener=()->{//自制监听器，当登录成功后执行下面代码出现棋盘
            gameBoard.transListener.onTransInfo(new GoInfo(null,false,login.cpr));
            container.remove(login);
            JButton btn = new JButton("重新开始");
            JButton btn2 = new JButton("读档");

            JPanel controlPanel = new JPanel();
            controlPanel.add(btn);
            controlPanel.add(btn2);
            CardLayout cardLayout = new CardLayout();
            JPanel CenterInner = new JPanel(cardLayout);
            CenterInner.add(gameBoard,"gameBoard");
            CenterInner.add(loads,"loads");
            cardLayout.show(CenterInner,"gameBoard");

            container.add(controlPanel, BorderLayout.SOUTH);
            container.add(CenterInner,BorderLayout.CENTER);

            btn.addActionListener(e -> {
                cardLayout.show(CenterInner,"gameBoard");
                if(loads.timer!=null){
                    loads.timer.cancel();
                }
                gameBoard.startGame();
            });
            btn2.addActionListener(e -> {
                cardLayout.show(CenterInner,"loads");
            });
            loads.loadBack=(f)->{
                cardLayout.show(CenterInner,"gameBoard");
                ArrayList<ChessStep> rs = getLoad(f);//加载存档
                slowPlay(rs);//回放
                gameBoard.startGame();
            };

            pack();//使窗口适应内容自动调整大小
            handleOnClose();
            setLocationRelativeTo(null);//可以使窗口居中
        };
        setLocationRelativeTo(null);
    }

    private ArrayList<ChessStep> getLoad(File f) {
        ArrayList<ChessStep> rs = null;
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            rs = (ArrayList<ChessStep>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private void slowPlay(ArrayList<ChessStep> rs){
        Timer timer = new Timer();
        loads.timer=timer;
        final int[] i = {0};
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(i[0]<=rs.size()-1){
                    ChessStep cs = rs.get(i[0]);
                    gameBoard.board[cs.getX()][cs.getY()]=cs.getChess();
                    gameBoard.repaint();
                    i[0]++;
                }
            }
        },0,500);
    }

    public void handleOnClose() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!gameBoard.isEnded) {
                    int i = JOptionPane.showConfirmDialog(gameBoard, "游戏还未结束，您要保存吗？");
                    if (i == 0) {
                        gameBoard.savePlay();
                    }
                }
            }
        });
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }


}
