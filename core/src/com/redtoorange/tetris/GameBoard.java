package com.redtoorange.tetris;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redtoorange.tetris.tetraminos.Block;
import com.redtoorange.tetris.tetraminos.Tetramino;

/**
 * ${FILE_NAME}.java - Description
 *
 * @author
 * @version 21/May/2017
 */
public class GameBoard {
    private Block[][] board;

    private int width;
    private int height;

    public GameBoard(int width, int height){
        board = new Block[width][height];

        this.width = width;
        this.height = height;
    }

    public boolean isEmpty(int x, int y){
        return (board[x][y] == null);
    }

    public void clearSpace(int x, int y){
        board[x][y] = null;
    }

    public void draw( SpriteBatch batch){
        for(int i = 0; i < board.length; i++)
            for ( int j = 0; j < board[i].length; j++ )
                if(board[i][j] != null)
                    board[i][j].draw(batch);
    }

    public void update( float deltaTime){
        for(int i = 0; i < board.length; i++)
            for ( int j = 0; j < board[i].length; j++ )
                if(board[i][j] != null)
                    board[i][j].update();
    }

    public void addTetra( Tetramino tetra){
        for(Block b : tetra.getBlocks()) {
            if ( board[b.getX()][b.getY()] == null )
                board[b.getY()][b.getY()] = b;
        }
    }

    public boolean legalMove(Tetramino tetra, int dx, int dy){
        boolean isLegal = true;

        for(Block b : tetra.getBlocks()) {
            int newX = b.getX() + dx;
            int newY = b.getY() + dy;

            if ( !isEmpty( newX, newY) )
                isLegal = false;

            if(dx < 0 || dx > width || dy < 0 || dy > height+10)
                isLegal = false;
        }

        return isLegal;
    }
}
