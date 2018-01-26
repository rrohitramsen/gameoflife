package com.test.gameoflife.game;

import com.test.gameoflife.helper.DisplayUtils;

/**
 * Created by rohitkumar on 31/12/17.
 */
public class Seed {

    private int ticks;
    private int row;
    private int col;
    private byte [][] pattern;


    public Seed() {
    }

    public Seed(int row, int col, byte[][] pattern) {
        this.row = row;
        this.col = col;
        this.pattern = pattern;
    }

    public Seed(int ticks, int row, int col, byte[][] pattern) {
        this.ticks = ticks;
        this.row = row;
        this.col = col;
        this.pattern = pattern;
    }

    public int getTicks() {
        return ticks;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public byte[][] getPattern() {
        return pattern;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setPattern(byte[][] pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return "Seed{" +
                "ticks=" + ticks +
                ", row=" + row +
                ", col=" + col +
                ", pattern=" + DisplayUtils.display(pattern) +
                '}';
    }


}
