package com.redtoorange.tetris.tetraminos;

/**
 * Type.java - Description
 *
 * @author Andrew McGuiness
 * @version 5/22/2017
 */
public enum Type {
    JTetra(0), LTetra(1), LineTetra(2), SquareTetra(3), COUNT(4);

    private int index;
    Type( int i){
        this.index = i;
    }

    public int getIndex(){
        return index;
    }
}
