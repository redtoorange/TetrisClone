package com.redtoorange.tetris;

/**
 * MoveVector.java - Description
 *
 * @author Andrew McGuiness
 * @version 5/22/2017
 */
public class MoveVector {
    private int x;
    private int y;

    public MoveVector( int x, int y ) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX( int x ) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY( int y ) {
        this.y = y;
    }
}
