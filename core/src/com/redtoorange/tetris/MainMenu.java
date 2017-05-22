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
import com.redtoorange.tetris.tetraminos.JTetra;
import com.redtoorange.tetris.tetraminos.LTetra;
import com.redtoorange.tetris.tetraminos.Tetramino;

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

    @Override
    public void render( float delta ) {
        super.render( delta );

        update( delta );
        draw();
    }

    public void update( float deltaTime ){
        handleInput();
        updateBlocks( deltaTime );
    }

    @Override
    public void show() {
        super.show();

        batch = new SpriteBatch(  );
        camera = new OrthographicCamera( Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        viewport = new FitViewport( Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera );

        shapeRenderer = new ShapeRenderer(  );

        board = new GameBoard( Constants.WORLD_WIDTH, Constants.WINDOW_HEIGHT );
//        testTetra = new SquareTetra( 0, MathUtils.ceil(Constants.WORLD_HEIGHT/2.0f)+1);
        testTetra = new JTetra( board, 0, MathUtils.ceil(Constants.WORLD_HEIGHT/2.0f)+1);
    }

    public void draw(){
        camera.update();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor( Color.RED );
        shapeRenderer.box( 0, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1 );

        shapeRenderer.end();

        batch.setProjectionMatrix( camera.combined );
        batch.begin();
        //Draw shit
        testTetra.draw( batch );
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
        }
        if( Gdx.input.isKeyJustPressed( Input.Keys.S )){
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
                testTetra = new LTetra( board, 0, MathUtils.ceil(Constants.WORLD_HEIGHT/2.0f)+1 );
            }

            currentTime = coolDown;
        }
        else
        {
            currentTime -= deltaTime;
        }
    }
}
