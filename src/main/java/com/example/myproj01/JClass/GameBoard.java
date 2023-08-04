package com.example.myproj01.JClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

public class GameBoard extends JPanel {//棋盘

    private static final int ROWS = 20;
    private static final int COLS = 20;
    private static final int CELL_SIZE = 40;//棋盘格子的大小

    private static final int EMPTY = 0;//表示未落子状态
    private static final int PLAYER1 = 1;
    private static final int PLAYER2 = 2;

    public int[][] getBoard() {
        return board;
    }

    public int[][] board;
    private int currentPlayer;
    private ArrayList<ChessStep> result=new ArrayList<>();
    public boolean isEnded;
    public MouseAdapter mouselistener;
    public TransInfo transListener;

    public GameBoard(int cpr) {
        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));//棋盘首选大小
        setBackground(Color.decode("#d2d2d2"));
        currentPlayer = cpr;//规定黑子先走,如果cpr为2则执白后走
        initializeBoard();

        mouselistener = new MouseAdapter() {//鼠标事件适配器，只需要重写点击方法
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentPlayer == PLAYER1) {
                    int row = e.getY() / CELL_SIZE;
                    int col = e.getX() / CELL_SIZE;

                    if (isValidMove(row, col)) {
                        board[row][col] = PLAYER1;
                        result.add(new ChessStep(row, col, PLAYER1));
                        repaint();//修改后重绘

                        if (checkWin(row, col, PLAYER1)) {//每下完一子都判断是否分出胜负
                            handleAfterEnd(PLAYER1);
                        }
                        waitOponent(result.get(result.size() - 1));//发送落子信息，等待对手回应
                    }
                }
                if (currentPlayer == PLAYER2) {
                    int row = e.getY() / CELL_SIZE;
                    int col = e.getX() / CELL_SIZE;

                    if (isValidMove(row, col)) {
                        board[row][col] = PLAYER2;
                        result.add(new ChessStep(row, col, PLAYER2));
                        repaint();

                        if (checkWin(row, col, PLAYER2)) {
                            handleAfterEnd(PLAYER2);
                        }
                        waitOponent(result.get(result.size() - 1));//发送落子信息，等待对手回应
                    }
                }
            }
        };
        if(currentPlayer==PLAYER1){
            addMouseListener(mouselistener);
        }
    }
    public void waitOponent(ChessStep cs){
        removeMouseListener(mouselistener);
        GoInfo goInfo = new GoInfo(cs, isEnded,null);
        if(transListener!=null){
            transListener.onTransInfo(goInfo);
        }
    }
    private void handleAfterEnd(int winner){
        isEnded=true;
        JOptionPane.showMessageDialog(this, winner==currentPlayer?"获胜！":"败北！");
        //因为是在内部类中，所以不能直接写this，可在前面加上外部类类名
        initializeBoard();
        repaint();
    }

    private void initializeBoard() {
        board = new int[ROWS][COLS];//整形二维数组默认就都是零，不需要再=EMPTY
//        currentPlayer = PLAYER1;
    }

    public void savePlay(){
        int newroundno = updateRound();
        File f2 = new File("src\\main\\java\\com\\example\\myproj01\\Txt\\match"+newroundno+".txt");
        if(!f2.exists()){
            try {
                f2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(f2);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(result);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//游戏存档

    private int updateRound(){
        File f=new File("src\\main\\java\\com\\example\\myproj01\\Txt\\round.txt");
        chessRound newround=null;
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            chessRound round=(chessRound)ois.readObject();

            newround = new chessRound(round.getRoundno() + 1, isEnded);

            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(newround);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newround.getRoundno();
    }

    private boolean isValidMove(int row, int col) {//判断是否是有效落子
        return board[row][col] == EMPTY;
    }

    public boolean checkWin(int row, int col, int player) {
        int count = 0;

        // 检查当前落子所在行
        for (int c = 0; c < COLS; c++) {
            if (board[row][c] == player) {
                count++;
                if (count == 5) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        // 检查当前落子所在列
        count = 0;
        for (int r = 0; r < ROWS; r++) {
            if (board[r][col] == player) {
                count++;
                if (count == 5) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        // 检查对角线
        count = 0;
        for (int i = -4; i <= 4; i++) {
            int r = row + i;
            int c = col + i;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLS) {
                if (board[r][c] == player) {
                    count++;
                    if (count == 5) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }

        // 检查反对角线
        count = 0;
        for (int i = -4; i <= 4; i++) {
            int r = row - i;
            int c = col + i;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLS) {
                if (board[r][c] == player) {
                    count++;
                    if (count == 5) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }

        return false;//return false要写在这里，以上的全找一遍如果找不到五连子表示未分出胜负
    }

    @Override
    public void paintComponent(Graphics g) {//g相当于一个画笔，可对其进行配置
        super.paintComponent(g);

        // 绘制棋盘网格
        g.setColor(Color.BLACK);
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                int x = c * CELL_SIZE;
                int y = r * CELL_SIZE;
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
            }
        }

        // 绘制棋子
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                int x = c * CELL_SIZE;
                int y = r * CELL_SIZE;
                if (board[r][c] == PLAYER1) {
                    g.setColor(Color.BLACK);
                    g.fillOval(x + 2, y + 2, CELL_SIZE - 4, CELL_SIZE - 4);//使棋子居中
                } else if (board[r][c] == PLAYER2) {
                    g.setColor(Color.WHITE);
                    g.fillOval(x + 2, y + 2, CELL_SIZE - 4, CELL_SIZE - 4);
                }
            }
        }
    }

    public void startGame() {
        initializeBoard();
        repaint();
    }
}
