package com.example.myproj01.JClass;

import java.io.Serializable;

public class chessRound implements Serializable {
    private int roundno;
    private boolean isEnded;

    public chessRound() {
    }

    public chessRound(int roundno, boolean isEnded) {
        this.roundno = roundno;
        this.isEnded = isEnded;
    }

    /**
     * 获取
     * @return roundno
     */
    public int getRoundno() {
        return roundno;
    }

    /**
     * 设置
     * @param roundno
     */
    public void setRoundno(int roundno) {
        this.roundno = roundno;
    }

    /**
     * 获取
     * @return isEnded
     */
    public boolean isIsEnded() {
        return isEnded;
    }

    /**
     * 设置
     * @param isEnded
     */
    public void setIsEnded(boolean isEnded) {
        this.isEnded = isEnded;
    }

    public String toString() {
        return "chessRound{roundno = " + roundno + ", isEnded = " + isEnded + "}";
    }
}
