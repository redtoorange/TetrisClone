package com.redtoorange.tetris;

import com.badlogic.gdx.Gdx;
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
        boolean empty = true;

        if(x >=0 && x < width && y >=0 && y < height)
            empty = (board[x][y] == null);

        return empty;
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
        Gdx.app.log( "GameBoard", "Adding Tetra" );
        for(Block b : tetra.getBlocks()) {
            if ( board[b.getX()][b.getY()] == null ) {
                board[b.getX()][b.getY()] = b;
                Gdx.app.log( "GameBoard", "added block at (" + b.getX() + ", " + b.getY() + ")." );
            }
        }

        checkRows();
    }

    private void checkRows(){

        for(int y = 0; y < height; y++){
            boolean noEmpty = true;

            for(int x = 0; x < width && noEmpty; x++)
                noEmpty = !(board[x][y] == null);

            if( noEmpty ) {
                clearRow( y );
                y--;
            }
        }
    }

    private void clearRow( int row ){
        for(int x = 0; x < width; x++){
            board[x][row] = null;
        }

        for(int y = row+1; y < height; y++){
            for(int x = 0; x < width; x++){
                if( !isEmpty( x, y )){
                    board[x][y-1] = board[x][y];
                    board[x][y].translate( 0, -1 );
                    board[x][y] = null;
                }
            }
        }
    }

    public boolean legalMove(Tetramino tetra, int dx, int dy){
        boolean isLegal = true;

        for(Block b : tetra.getBlocks()) {
            int newX = b.getX() + dx;
            int newY = b.getY() + dy;

            if ( !isEmpty( newX, newY) )
                isLegal = false;

            if(newX < 0 || newX >= width || newY < 0 || newY > height+10)
                isLegal = false;
        }

        return isLegal;
    }

    public boolean legalRotation( MoveVector[] moves ){
        boolean isLegal = true;

        for(MoveVector m : moves){
            if(m.getX() < 0 || m.getX() >= width || m.getY() < 0 ||
                    !isEmpty( m.getX(), m.getY() )) {
                isLegal = false;
            }
        }


        return isLegal;
    }
}
