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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Scoreboard implements Screen {
    Preferences scores;
    Stage stage;
    Game game;

    public Scoreboard(Game gameIn){
        game = gameIn;
        this.stage = new Stage(new ScreenViewport());
        scores = Gdx.app.getPreferences("Scoreboard");
        Label scoreboardLabel = new Label("High Scores", GameActivity.skin);
        setLabelPosition(scoreboardLabel, (int) (Gdx.graphics.getHeight()/2 + scoreboardLabel.getHeight() * 10));

        int score1 = scores.getInteger("score1", 0);
        String user1 = scores.getString("user1", "");
        Label score1Label = new Label(user1 + " : " + Integer.toString(score1), GameActivity.skin);
        setLabelPosition(score1Label, Gdx.graphics.getHeight()/2);

        int score2 = scores.getInteger("score2", 0);
        String user2 = scores.getString("user2", "");
        Label score2Label = new Label(user2 + " : " + Integer.toString(score2), GameActivity.skin);
        setLabelPosition(score2Label, (int) (Gdx.graphics.getHeight()/2-score2Label.getHeight() * 6));

        int score3 = scores.getInteger("score3", 0);
        String user3 = scores.getString("user3", "");
        Label score3Label = new Label(user3 + " : " + Integer.toString(score3), GameActivity.skin);
        setLabelPosition(score3Label, (int) (Gdx.graphics.getHeight()/2-score3Label.getHeight() * 12));

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
