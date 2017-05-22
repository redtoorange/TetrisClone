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

    public Block(int x, int y){
        sprite = new Sprite( new Texture( "block.png" ) );
        sprite.setPosition( x, y );
        sprite.setSize( 1, 1 );
    }

    public int getX(){
        return (int)sprite.getX();
    }

    public int getY(){
        return (int)sprite.getY();
    }

    public void translate( int dx, int dy){
        sprite.translate( dx, dy );
    }

    public void update(){
//        sprite.setPosition( x, y );
    }

    public void draw( SpriteBatch batch ){
        sprite.draw( batch );
    }
}
