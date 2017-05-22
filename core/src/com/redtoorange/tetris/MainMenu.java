package com.redtoorange.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redtoorange.tetris.tetraminos.*;

import java.util.Random;

/**
 * ${FILE_NAME}.java - Description
 *
 * @author
 * @version 20/May/2017
 */
public class MainMenu extends ScreenAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    private ShapeRenderer shapeRenderer;

    private float coolDown = 1.0f;
    private float currentTime = 0.0f;

    Tetramino testTetra;
    GameBoard board;
    float widthPPU;
    float heightPPU;

    @Override
    public void render( float delta ) {
        super.render( delta );

        update( delta );
        draw();
    }

    public void update( float deltaTime ){
        handleInput();
        updateBlocks( deltaTime );
        board.update( deltaTime );
    }

    @Override
    public void show() {
        super.show();

        widthPPU = Constants.WINDOW_WIDTH / Constants.WORLD_WIDTH;
        heightPPU = Constants.WINDOW_HEIGHT/ Constants.WORLD_HEIGHT;

        batch = new SpriteBatch(  );
        camera = new OrthographicCamera( Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        viewport = new FitViewport( Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera );
        camera.translate( MathUtils.floor(Constants.WORLD_WIDTH/2.0f), MathUtils.ceil(Constants.WORLD_HEIGHT/2.0f) );

        shapeRenderer = new ShapeRenderer(  );

        board = new GameBoard( Constants.COLUMNS, Constants.ROWS );

        testTetra = generateBlock();
    }

    public void draw(){
        camera.update();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor( Color.RED );
        shapeRenderer.box( 0, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1 );

        shapeRenderer.setColor( Color.BLUE );
        shapeRenderer.box( widthPPU/2, 0, 0, Constants.COLUMNS * widthPPU, Constants.ROWS * heightPPU, 1 );


        shapeRenderer.end();

        batch.setProjectionMatrix( camera.combined );
        batch.begin();

        //Draw shit
        testTetra.draw( batch );
        board.draw( batch );

        batch.end();
    }

    @Override
    public void resize( int width, int height ) {
        super.resize( width, height );

        viewport.update( width, height );
        camera.update();
    }

    public void handleInput(){
        if( Gdx.input.isKeyJustPressed( Input.Keys.W )){
            //Instant drop
            while( testTetra.translate( 0, -1 ) ) {
                //Stubb
            }
        }
        if( Gdx.input.isKeyPressed( Input.Keys.S )){
            //Increase velocity
            testTetra.translate( 0, -1 );
        }
        if( Gdx.input.isKeyJustPressed( Input.Keys.A )){
            //Move Left
            testTetra.translate( -1, 0 );
        }
        if( Gdx.input.isKeyJustPressed( Input.Keys.D )){
            //Move Right
            testTetra.translate( 1, 0);
        }
        if(Gdx.input.isKeyJustPressed( Input.Keys.SPACE )){
            testTetra.rotate();
        }

        if(Gdx.input.isKeyPressed( Input.Keys.ESCAPE ))
            Gdx.app.exit();
    }

    public void updateBlocks( float deltaTime){
        testTetra.update();
        if(currentTime <= 0){
            boolean moved = testTetra.translate( 0, -1 );

            if( !moved ){
                board.addTetra( testTetra );
                testTetra = generateBlock();
            }

            currentTime = coolDown;
        }
        else
        {
            currentTime -= deltaTime;
        }
    }

    public Tetramino generateBlock(){
        Random r = new Random( System.currentTimeMillis()  );
        int i = r.nextInt( Type.COUNT.getIndex() );

        Tetramino t = null;

        if(i == Type.JTetra.getIndex()){
            t = new JTetra( board, Constants.COLUMNS/2, Constants.ROWS );
        }
        else if(i == Type.LTetra.getIndex()){
            t = new LTetra( board, Constants.COLUMNS/2, Constants.ROWS );
        }
        else if(i == Type.LineTetra.getIndex()){
            t = new LineTetra( board, Constants.COLUMNS/2, Constants.ROWS );
        }
        else if(i == Type.SquareTetra.getIndex()){
            t = new SquareTetra( board, Constants.COLUMNS/2, Constants.ROWS );
        }

       return t;
    }
}
