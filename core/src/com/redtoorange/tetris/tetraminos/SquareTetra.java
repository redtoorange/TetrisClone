package com.redtoorange.tetris.tetraminos;

import com.badlogic.gdx.Gdx;
import com.redtoorange.tetris.GameBoard;

/**
 * ${FILE_NAME}.java - Description
 *
 * @author
 * @version 20/May/2017
 */
public class SquareTetra extends Tetramino {
    public static final String TAG = SquareTetra.class.getSimpleName();

    public SquareTetra( GameBoard board, int x, int y ) {
        super( board, x, y );  //Builds the internal array and then calls initBlocks
    }

    @Override
    protected void initBlocks(int x, int y) {
        blocks[0] = new Block( x, y );
        blocks[1] = new Block( x+1, y );
        blocks[2] = new Block( x+1, y+1 );
        blocks[3] = new Block( x, y+1 );
    }

    @Override
    public void rotate() {
        Gdx.app.log( TAG, "Block Rotated" );
    }
}
