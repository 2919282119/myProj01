package com.example.myproj01.NetTest;

import com.example.myproj01.JClass.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private ServerSocket serverSocket;
    private CopyOnWriteArrayList<Socket> list;
    private CopyOnWriteArrayList<ObjectOutputStream> oosList;
    ArrayList<ChessStep> result;
    ChessPlayer player1;
    ChessPlayer player2;
    boolean hasWinner;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);

            list=new CopyOnWriteArrayList<>();
            oosList=new CopyOnWriteArrayList<>();
            result=new ArrayList<>();
            System.out.println("Server started on port " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                list.add(clientSocket);
                oosList.add(new ObjectOutputStream(clientSocket.getOutputStream()));
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                Thread clientHandler = new Thread(() -> handleClient(clientSocket));
                clientHandler.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleClient(Socket socket) {
        try {

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            GoInfo info;
            ChessStep cs;
            while (true) {
                info=(GoInfo) ois.readObject();
                if(info.isWinner){
                    hasWinner=true;
                    savePlay();
                    Functions.CprUpdate(info.cpr);
                }

                if((cs=info.chessStep)!=null){
                    System.out.println("棋子的落点为("+cs.getX()+","+cs.getY()+","+cs.getChess()+")");
                }
                if(player1==null){
                    player1=info.cpr;
                }else{
                    player2=info.cpr;
                }
                if(player1!=null){
                    System.out.println("玩家一"+player1);
                }
                if(player2!=null){
                    System.out.println("玩家二"+player2);
                }
                if(info.chessStep!=null){
                    ChessStep step = new ChessStep(info.chessStep.getX(), info.chessStep.getY(), info.chessStep.getChess());
                    for (int i = 0; i < oosList.size(); i++) {
                        if(list.get(i)!=socket){
                            result.add(step);
                            ObjectOutputStream oos = oosList.get(i);
                            oos.writeObject(info.chessStep);
                            oos.flush();
                        }
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

            newround = new chessRound(round.getRoundno() + 1, hasWinner);

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

    public static void main(String[] args) {
        Server server = new Server(1234);
        server.start();
    }
}
