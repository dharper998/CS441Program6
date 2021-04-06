package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenu implements Screen {
    private Game game;
    private Stage stage;

    public MainMenu(Game gameIn){
        game = gameIn;
        this.stage = new Stage(new ScreenViewport());
        final TextField usernameInput = new TextField("Enter Username:", GameActivity.skin);
        usernameInput.setSize(400f, 100f);
        usernameInput.setMaxLength(5);
        usernameInput.setPosition(Gdx.graphics.getWidth()/2-usernameInput.getWidth()/2,Gdx.graphics.getHeight()-usernameInput.getHeight()*5);
        usernameInput.addListener(new ClickListener(){
           @Override
           public void clicked(InputEvent event, float x, float y){
               super.clicked(event, x, y);
               usernameInput.setText("");
           }
        });

        TextButton easyButton = new TextButton("Play Easy", GameActivity.skin);
        easyButton.setWidth(400f);
        easyButton.setHeight(100f);
        easyButton.setColor(0, 1, 1, 1);
        easyButton.getLabel().setFontScale(2f);
        easyButton.setPosition(Gdx.graphics.getWidth()/2-easyButton.getWidth()/2,Gdx.graphics.getHeight()/2);
        easyButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                if(usernameInput.getText().contains("Enter Username:")){
                    usernameInput.setText("");
                }
                game.setScreen(new BreakoutScreen(game, 16, usernameInput.getText(), -5f, 1, 2));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        TextButton mediumButton = new TextButton("Play Medium", GameActivity.skin);
        mediumButton.setWidth(400f);
        mediumButton.setHeight(100f);
        mediumButton.setColor(0, 1, 1, 1);
        mediumButton.getLabel().setFontScale(2f);
        mediumButton.setPosition(Gdx.graphics.getWidth()/2-mediumButton.getWidth()/2,Gdx.graphics.getHeight()/2-mediumButton.getHeight()*2);
        mediumButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                if(usernameInput.getText().contains("Enter Username:")){
                    usernameInput.setText("");
                }
                game.setScreen(new BreakoutScreen(game, 36, usernameInput.getText(), -8f, 1, 4));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        TextButton hardButton = new TextButton("Play Hard", GameActivity.skin);
        hardButton.setWidth(400f);
        hardButton.setHeight(100f);
        hardButton.setColor(0, 1, 1, 1);
        hardButton.getLabel().setFontScale(2f);
        hardButton.setPosition(Gdx.graphics.getWidth()/2-hardButton.getWidth()/2,Gdx.graphics.getHeight()/2-hardButton.getHeight()*4);
        hardButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                if(usernameInput.getText().contains("Enter Username:")){
                    usernameInput.setText("");
                }
                game.setScreen(new BreakoutScreen(game, 36, usernameInput.getText(), -10f, 2, 4));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        TextButton howToButton = new TextButton("How To Play", GameActivity.skin);
        howToButton.setWidth(400f);
        howToButton.setHeight(100f);
        howToButton.getLabel().setFontScale(2f);
        howToButton.setPosition(Gdx.graphics.getWidth()/2-howToButton.getWidth()/2,howToButton.getHeight() * 4);
        howToButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                game.setScreen(new HowToScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(easyButton);
        stage.addActor(mediumButton);
        stage.addActor(hardButton);
        stage.addActor(howToButton);
        stage.addActor(usernameInput);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
