package com.redtoorange.tetris.tetraminos;

import com.redtoorange.tetris.GameBoard;

/**
 * ${FILE_NAME}.java - Description
 *
 * @author
 * @version 20/May/2017
 */
public class JTetra extends Tetramino {
    private Orientation orientation = Orientation.DOWN;

    public JTetra( GameBoard board, int x, int y ) {
        super( board, x, y );
    }

    @Override
    protected void initBlocks( int x, int y ) {
        blocks[0] = new Block( x, y+2 );
        blocks[1] = new Block( x, y+1 );
        blocks[2] = new Block( x, y );
        blocks[3] = new Block( x-1, y );
    }

    @Override
    public void rotate() {
        switch(orientation){
            case DOWN:
                rotateRight();
                break;
            case RIGHT:
                rotateUp();
                break;
            case UP:
                rotateLeft();
                break;
            case LEFT:
                rotateDown();
                break;
        }
    }

    /**
     *
     *  0x2
     *    3
     *
     */
    private void rotateRight(){
        orientation = Orientation.RIGHT;

        blocks[0].translate(-1, -1  );
        blocks[2].translate(1, 1 );
        blocks[3].translate(2, 0 );

    }

    /**
     *       23
     *       x
     *       0
     */
    private void rotateUp(){
        orientation = Orientation.UP;

        blocks[0].translate(1, -1  );
        blocks[2].translate(-1, 1 );
        blocks[3].translate(0, 2 );
    }

    /**
     *      3
     *      2x0
     *
     */
    private void rotateLeft(){
        orientation = Orientation.LEFT;

        blocks[0].translate(1, 1  );
        blocks[2].translate(-1, -1 );
        blocks[3].translate(-2, 0 );
    }

    /**
     *      0
     *      x
     *     32
     */
    private void rotateDown(){
        orientation = Orientation.DOWN;

        blocks[0].translate(-1, 1  );
        blocks[2].translate(1, -1 );
        blocks[3].translate(0, -2 );
    }
}
