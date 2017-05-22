package com.redtoorange.tetris.tetraminos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * ${FILE_NAME}.java - Description
 *
 * @author
 * @version 20/May/2017
 */
public class Block {
    private Sprite sprite;
    private int x;
    private int y;

    public Block(int x, int y){
        sprite = new Sprite( new Texture( "block.png" ) );
        sprite.setPosition( x, y );
        sprite.setSize( 1, 1 );

        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void translate( int dx, int dy){
        x += dx;
        y += dy;
    }

    public void update(){
        sprite.setPosition( x, y );
    }

    public void draw( SpriteBatch batch ){
        sprite.draw( batch );
    }
}
