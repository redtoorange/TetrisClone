package com.redtoorange.tetris.tetraminos;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redtoorange.tetris.GameBoard;

/**
 * ${FILE_NAME}.java - Description
 *
 * @author
 * @version 20/May/2017
 */
public abstract class Tetramino {
    protected Block[] blocks;
    protected boolean falling = true;
    protected GameBoard board;

    public Tetramino( GameBoard board, int x, int y){
        blocks = new Block[4];
        this.board = board;
        initBlocks(x, y);
    }

    public void update(){
        for(Block b : blocks){
            b.update();
        }
    }

    public void draw( SpriteBatch batch ){
        for(Block b : blocks){
            b.draw( batch );
        }
    }

    public boolean translate(int x, int y){
        boolean canMove = board.legalMove( this, x, y );
        if( canMove )
            for(Block b : blocks)
                b.translate( x, y );
        return canMove;
    }

    public boolean isFalling(){
        return falling;
    }

    public void setFalling(boolean falling){
        this.falling = falling;
    }

    public Block[] getBlocks(){
        return blocks;
    }
    protected abstract void initBlocks(int x, int y);
    public abstract void rotate();
}
