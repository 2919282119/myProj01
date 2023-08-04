package com.example.myproj01.JClass;

import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Functions {
    private Functions() {
    }
    public static Connection getCon(String db){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = null;
        String url="jdbc:mysql://127.0.0.1/"+db+"?"+"useSSL=false&serverTimezone=GMT&characterEncoding=utf-8&allowPublicKeyRetrieval=true";
        try {
            con= DriverManager.getConnection(url,"root","sql2008");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static void CprUpdate(ChessPlayer cpr){
        Connection con=getCon("mysales");
        try {
            Statement stmt = con.createStatement();
            int point=cpr.getPoint();
            point+=20;
            stmt.executeUpdate("update chess set point="+point+" where playerid='"+cpr.getPlayerid()+"'");
            System.out.println("亲爱的玩家"+cpr.getPlayername()+",您的积分已经增加到"+point+"!");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static String RsToJson(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<Object> rows = new ArrayList<>();
        while (resultSet.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = resultSet.getObject(i);
            }
            rows.add(row);
        }
        Gson gson = new Gson();
        return gson.toJson(rows);
    }
    public static ArrayList<ChessPlayer> getAllPlayers(){
        ArrayList<ChessPlayer> list = new ArrayList<>();
        Connection con = getCon("mysales");
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from chess");
            while(rs.next()){
                ChessPlayer cpr = new ChessPlayer(rs.getString("playerid"), rs.getString("playername"), rs.getString("password"), rs.getInt("point"));
                list.add(cpr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
