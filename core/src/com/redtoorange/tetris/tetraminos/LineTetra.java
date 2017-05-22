package com.redtoorange.tetris.tetraminos;

import com.redtoorange.tetris.GameBoard;

/**
 * ${FILE_NAME}.java - Description
 *
 * @author
 * @version 20/May/2017
 */
public class LineTetra extends Tetramino {
    private boolean isVertical = true;

    public LineTetra( GameBoard board, int x, int y ) {
        super( board, x, y );
    }

    @Override
    protected void initBlocks( int x, int y ) {
        blocks[0] = new Block( x, y+2 );
        blocks[1] = new Block( x, y+1 );
        blocks[2] = new Block( x, y );
        blocks[3] = new Block( x, y-1 );
    }

    @Override
    public void rotate() {
        if( isVertical)
            makeHorizontal();
        else
            makeVertical();
    }

    private void makeHorizontal(){
        isVertical = false;

        blocks[0].translate( -1, -2 );
        blocks[1].translate( 1, -1 );
        blocks[3].translate( 2, 1 );
    }

    private void makeVertical() {
        isVertical = true;

        blocks[0].translate( 1, 2 );
        blocks[1].translate( -1, 1 );
        blocks[3].translate( -2, -1 );
    }
}
