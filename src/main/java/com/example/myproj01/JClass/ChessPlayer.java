package com.example.myproj01.JClass;

import java.io.Serializable;

public class ChessPlayer implements Comparable, Serializable {
    private String playerid;
    private String playername;
    private String password;
    private int point;

    public ChessPlayer() {
    }

    public ChessPlayer(String playerid, String playername, String password, int point) {
        this.playerid = playerid;
        this.playername = playername;
        this.password = password;
        this.point = point;
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }

    /**
     * 获取
     * @return playerid
     */
    public String getPlayerid() {
        return playerid;
    }

    /**
     * 设置
     * @param playerid
     */
    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    /**
     * 获取
     * @return playername
     */
    public String getPlayername() {
        return playername;
    }

    /**
     * 设置
     * @param playername
     */
    public void setPlayername(String playername) {
        this.playername = playername;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return point
     */
    public int getPoint() {
        return point;
    }

    /**
     * 设置
     * @param point
     */
    public void setPoint(int point) {
        this.point = point;
    }

    public String toString() {
        return "ChessPlayer{playerid = " + playerid + ", playername = " + playername + ", password = " + password + ", point = " + point + "}";
    }
    public boolean equals(Object o){
        ChessPlayer cpr=(ChessPlayer) o;
        return ((playername.equals(cpr.getPlayername())&&(password.equals(cpr.getPassword()))));
    }
}
