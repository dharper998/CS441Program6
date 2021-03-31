package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Scoreboard implements Screen {
    Preferences scores;
    Stage stage;
    Game game;
    private OrthographicCamera camera;

    public Scoreboard(Game gameIn){
        game = gameIn;
        this.stage = new Stage(new ScreenViewport());
        scores = Gdx.app.getPreferences("Scoreboard");
        int score1 = scores.getInteger("score1", 0);
        Label scoreboardLabel = new Label("High Scores", GameActivity.skin);
        scoreboardLabel.setFontScale(5f);
        scoreboardLabel.setPosition(Gdx.graphics.getWidth()/2-scoreboardLabel.getWidth()/2,Gdx.graphics.getHeight() - scoreboardLabel.getHeight() * 4);

        Label score1Label = new Label(Integer.toString(score1), GameActivity.skin);
        score1Label.setFontScale(5f);
        score1Label.setPosition(Gdx.graphics.getWidth()/2-score1Label.getWidth()/2,Gdx.graphics.getHeight()/2);

        int score2 = scores.getInteger("score2", 0);
        Label score2Label = new Label(Integer.toString(score2), GameActivity.skin);
        score2Label.setFontScale(5f);
        score2Label.setPosition(Gdx.graphics.getWidth()/2-score2Label.getWidth()/2,Gdx.graphics.getHeight()/2-score2Label.getHeight() * 10);

        int score3 = scores.getInteger("score3", 0);
        Label score3Label = new Label(Integer.toString(score3), GameActivity.skin);
        score3Label.setFontScale(5f);
        score3Label.setPosition(Gdx.graphics.getWidth()/2-score3Label.getWidth()/2,Gdx.graphics.getHeight()/2-score3Label.getHeight() * 20);

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

        stage.addActor(scoreboardLabel);
        stage.addActor(score1Label);
        stage.addActor(score2Label);
        stage.addActor(score3Label);
        stage.addActor(menuButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
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
