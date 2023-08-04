package com.example.myproj01.JClass;

import java.io.Serializable;

public class ChessStep implements Serializable {
    private int x;
    private int y;
    private int chess;

    public ChessStep() {
    }

    public ChessStep(int x, int y, int chess) {
        this.x = x;
        this.y = y;
        this.chess = chess;
    }

    /**
     * 获取
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * 设置
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 获取
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * 设置
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 获取
     * @return chess
     */
    public int getChess() {
        return chess;
    }

    /**
     * 设置
     * @param chess
     */
    public void setChess(int chess) {
        this.chess = chess;
    }

    public String toString() {
        return "ChessStep{x = " + x + ", y = " + y + ", chess = " + chess + "}";
    }
}
