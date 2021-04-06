package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.Iterator;

public class BreakoutScreen implements Screen {
    private Game game;
    private Stage stage;
    private OrthographicCamera camera;
    ArrayList<Ball> balls;
    Paddle paddle;
    ArrayList<Brick> bricks;
    Rectangle intersection;
    int count;
    int score;
    String username;
    int speedScale;
    Label scoreLabel;

    public BreakoutScreen(Game gameIn, int countIn, String usernameIn, float speed, int ballCount, int speedScaleIn){
        game = gameIn;
        this.stage = new Stage(new ScreenViewport());
        bricks = new ArrayList<Brick>();
        intersection = new Rectangle();
        score = 0;
        username = usernameIn;
        speedScale = speedScaleIn;
        balls = new ArrayList<Ball>();
        for(int i = 0; i<ballCount; i++){
            balls.add(new Ball(speed));
        }

        count = countIn;
        createBricks(count);

        for(int i = 0; i<balls.size(); i++){
            Ball ball = balls.get(i);
            ball.setPosition(Gdx.graphics.getWidth()/2-ball.getWidth() * 2 * i,Gdx.graphics.getHeight()/2-ball.getHeight() * 2 * i);
            ball.updateBounds();
        }

        paddle = new Paddle();
        paddle.setPosition(Gdx.graphics.getWidth()/2-paddle.getWidth()/2,Gdx.graphics.getHeight()/8-paddle.getHeight());
        paddle.updateBounds();
        paddle.addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                paddle.moveBy(x - paddle.getWidth() / 2, 0);
                paddle.setVelocity(x - paddle.getWidth() / 2);
            }

            public void dragStop(InputEvent event, float x, float y, int pointer){
                paddle.setVelocity(0);
            }
        });

        scoreLabel = new Label(Integer.toString(score), GameActivity.skin);
        scoreLabel.setWidth(Gdx.graphics.getWidth());
        scoreLabel.setHeight(200);
        scoreLabel.setFontScale(3f);
        scoreLabel.setPosition(40, Gdx.graphics.getHeight()-200);
        scoreLabel.setAlignment(Align.left);

        for(int i = 0; i<balls.size(); i++) {
            this.stage.addActor(balls.get(i));
        }
        this.stage.addActor(scoreLabel);
        this.stage.addActor(paddle);
    }

    public void createBricks(int count){
        for(int i = 0; i < count; i++){
            Brick brick = new Brick();
            float x = (Gdx.graphics.getWidth() / 2 - (brick.getWidth() * ((int)Math.sqrt(count)/2) + ((int)Math.sqrt(count)/2) * 5)) + 5 + brick.getWidth() * (i % (int)Math.sqrt(count));
            brick.setPosition(x,Gdx.graphics.getHeight() - (brick.getHeight() * 8) - brick.getHeight() * (i/(int)Math.sqrt(count)));
            brick.setBounds();
            bricks.add(brick);
            this.stage.addActor(brick);
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for(int i = 0; i<balls.size(); i++) {
            Ball ball = balls.get(i);
            if (ball.bounds.overlaps(paddle.bounds)) {
                if (bricks.size() == 0) {
                    createBricks(count);
                    if (ball.speedY > 0) {
                        ball.speedY += speedScale;
                    } else {
                        ball.speedY -= speedScale;
                    }
                }
                if (ball.getY() > paddle.getY()) {
                    ball.speedY = -ball.speedY;
                }
                if (ball.speedX > 0 && paddle.speedX < 0 || ball.speedX < 0 && paddle.speedX > 0) {
                    ball.speedX = paddle.speedX / 3;
                }
            }
            if (!ball.update()) {
                exit();
            }
        }
        checkBricks();
        stage.act();
        stage.draw();
    }

    public void exit(){
        Preferences scores = Gdx.app.getPreferences("Scoreboard");
        int score1 = scores.getInteger("score1", 0);
        int score2 = scores.getInteger("score2", 0);
        int score3 = scores.getInteger("score3", 0);
        String user1 = scores.getString("user1", "");
        String user2 = scores.getString("user2", "");
        if(this.score > score1){
            scores.putInteger("score1", this.score);
            scores.putString("user1", this.username);
            scores.putInteger("score2", score1);
            scores.putString("user2", user1);
            scores.putInteger("score3", score2);
            scores.putString("user3", user2);
        } else if(this.score > score2){
            scores.putInteger("score2", this.score);
            scores.putString("user2", this.username);
            scores.putInteger("score3", score2);
            scores.putString("user3", user2);
        } else if(this.score > score3){
            scores.putInteger("score3", this.score);
            scores.putString("user3", this.username);
        }
        scores.flush();
        dispose();
        game.setScreen(new Scoreboard(game));
    }

    public void checkBricks(){
        for(int i = 0; i<balls.size(); i++) {
            Ball ball = balls.get(i);
            Iterator<Brick> itr = bricks.iterator();
            while(itr.hasNext()){
                Brick brick = itr.next();
                if (Intersector.intersectRectangles(brick.bounds, ball.bounds, intersection)) {
                    if (intersection.y > brick.bounds.y || intersection.y + intersection.height < brick.bounds.y + brick.bounds.height) {
                        ball.speedY = -ball.speedY;
                        brick.remove();
                        itr.remove();
                        score++;
                        scoreLabel.setText(Integer.toString(score));
                        break;
                    } else if (intersection.x > brick.bounds.x || intersection.x + intersection.width < brick.bounds.x + brick.bounds.width) {
                        ball.speedX = -ball.speedX;
                        brick.remove();
                        itr.remove();
                        score++;
                        scoreLabel.setText(Integer.toString(score));
                        break;
                    }
                }
            }
        }
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
        bricks.clear();
    }
}
