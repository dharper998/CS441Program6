package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameActivity extends Game {
	SpriteBatch batch;
	Texture img;
	private Game game;
	public static Skin skin;

	public GameActivity(){
		game = this;
	}

	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("skin/quantum-horizon-ui.json"));
		this.setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	public Skin getSkin(){
		return skin;
	}
}
