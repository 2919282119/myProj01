package com.example.myproj01.JClass;

import java.io.Serializable;

public class GoInfo implements Serializable {
    public ChessStep chessStep;
    public boolean isWinner;
    public ChessPlayer cpr;

    public GoInfo(ChessStep chessStep, boolean isWinner,ChessPlayer cpr) {
        this.chessStep = chessStep;
        this.isWinner = isWinner;
        this.cpr=cpr;
    }

    public GoInfo() {
    }
}
