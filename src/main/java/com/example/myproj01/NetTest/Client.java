package com.example.myproj01.NetTest;

import com.example.myproj01.JClass.ChessGame;
import com.example.myproj01.JClass.ChessStep;
import com.example.myproj01.JClass.GoInfo;
import com.example.myproj01.JClass.TransInfo;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    public ChessGame game;
    ObjectOutputStream oos;

    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
            System.out.println("Connected to server: " + host + ":" + port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            SwingUtilities.invokeLater(() -> {
                game=new ChessGame(1);
                game.setVisible(true);

                game.getGameBoard().transListener=(goInfo)-> sendInfo(goInfo);//发送落子信息
            });

            Thread receiveThread = new Thread(() -> receiveMessages());
            receiveThread.start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendInfo(GoInfo info) {
        try {
            info.cpr=game.login.cpr;//这儿可能会出错
            oos.writeObject(info);
            oos.flush();
            oos.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveMessages() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ChessStep cs;
            while(true){
                cs=(ChessStep)ois.readObject();
                game.getGameBoard().board[cs.getX()][cs.getY()]=cs.getChess();
                game.getGameBoard().addMouseListener(game.getGameBoard().mouselistener);
                game.repaint();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client("localhost", 1234);
    }
}
