package com.redtoorange.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

public class MainGame extends Game {
	private GameState state = GameState.MAIN_MENU;

	private Screen mainMenu;
	private Screen currentScreen;

	@Override
	public void create () {
		//STUB
		mainMenu = new MainMenu();
		setScreen( mainMenu );
		currentScreen = mainMenu;
	}

	@Override
	public void render () {
		clearWindow();
		super.render();
	}

	private void clearWindow() {
		Color cc = Constants.CLEAR_COLOR;
		Gdx.gl.glClearColor(cc.r, cc.g, cc.b, cc.a);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT);
	}


	@Override
	public void dispose () {

	}

	public void changeState( GameState newState ){
		state = newState;
	}

	public GameState getState(){
		return state;
	}
}
