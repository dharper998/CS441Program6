package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HowToScreen implements Screen {

    private Game game;
    private Stage stage;

    public HowToScreen(Game gameIn){
        game = gameIn;
        this.stage = new Stage(new ScreenViewport());
        TextButton menuButton = new TextButton("Main Menu", GameActivity.skin);
        menuButton.setWidth(400f);
        menuButton.setHeight(100f);
        menuButton.getLabel().setFontScale(2f);
        menuButton.setPosition(Gdx.graphics.getWidth()/2-menuButton.getWidth()/2,menuButton.getHeight() * 4);
        menuButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                game.setScreen(new MainMenu(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        Label howToLabel = new Label("How To Play", GameActivity.skin);
        setLabelPosition(howToLabel, (int) (Gdx.graphics.getHeight() - howToLabel.getHeight() * 6));

        Label howTo1 = new Label("Touch the paddle to drag\nit across the screen", GameActivity.skin);
        howTo1.setFontScale(3f);
        howTo1.setWidth(Gdx.graphics.getWidth());
        howTo1.setPosition(0,(int) (Gdx.graphics.getHeight() - howTo1.getHeight() * 12));
        howTo1.setAlignment(Align.center);

        Label howTo2 = new Label("Bounce the ball into the\nbricks to break them and\nscore points!", GameActivity.skin);
        howTo2.setFontScale(3f);
        howTo2.setWidth(Gdx.graphics.getWidth());
        howTo2.setPosition(0,(int) (Gdx.graphics.getHeight() - howTo1.getHeight() * 24));
        howTo2.setAlignment(Align.center);

        Label howTo3 = new Label("If the ball reaches the\nbottom of the screen,\nit's game over", GameActivity.skin);
        howTo3.setFontScale(3f);
        howTo3.setWidth(Gdx.graphics.getWidth());
        howTo3.setPosition(0,(int) (Gdx.graphics.getHeight() - howTo1.getHeight() * 36));
        howTo3.setAlignment(Align.center);

        Label howTo4 = new Label("When you run out of\nbricks to break, more will\nappear and the ball\nwill get faster", GameActivity.skin);
        howTo4.setFontScale(3f);
        howTo4.setWidth(Gdx.graphics.getWidth());
        howTo4.setPosition(0,(int) (Gdx.graphics.getHeight() - howTo1.getHeight() * 48));
        howTo4.setAlignment(Align.center);


        stage.addActor(menuButton);
        stage.addActor(howToLabel);
        stage.addActor(howTo1);
        stage.addActor(howTo2);
        stage.addActor(howTo3);
        stage.addActor(howTo4);

    }

    public void setLabelPosition(Label label, int y){
        label.setFontScale(5f);
        label.setWidth(Gdx.graphics.getWidth());
        label.setPosition(0,y);
        label.setAlignment(Align.center);
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
