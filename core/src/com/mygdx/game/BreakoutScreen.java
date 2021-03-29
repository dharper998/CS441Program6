package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.Iterator;

public class BreakoutScreen implements Screen {
    private Game game;
    private Stage stage;
    private OrthographicCamera camera;
    Ball ball = new Ball();
    Paddle paddle = new Paddle();
    ArrayList<Brick> bricks;
    Rectangle intersection = new Rectangle();
    int count;
    int score = 0;

    public BreakoutScreen(Game gameIn){
        game = gameIn;
        this.stage = new Stage(new ScreenViewport());
        bricks = new ArrayList<Brick>();
        createBricks(count);
        ball.setPosition(Gdx.graphics.getWidth()/2-ball.getWidth()/2,Gdx.graphics.getHeight()/2-ball.getHeight()/2);
        ball.updateBounds();
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
        Label score = new Label();
        this.stage.addActor(ball);
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
        if(ball.bounds.overlaps(paddle.bounds)){
            if(ball.getY() > paddle.getY()){
                ball.speedY = -ball.speedY;
            }
            if(ball.speedX > 0 && paddle.speedX < 0 || ball.speedX < 0 && paddle.speedX > 0){
                ball.speedX = paddle.speedX/3;
            }
        }
        checkBricks();
        if(!ball.update()){
            game.setScreen(new MainMenu(game));
        }
        stage.act();
        stage.draw();
    }

    public void checkBricks(){
        ArrayList<Brick> toRemove = new ArrayList<Brick>();
        Iterator<Brick> itr = bricks.iterator();
        while(itr.hasNext()){
            Brick brick = itr.next();
            if(Intersector.intersectRectangles(brick.bounds, ball.bounds, intersection)){
                if(intersection.y > brick.bounds.y || intersection.y + intersection.height < brick.bounds.y + brick.bounds.height){
                    ball.speedY = -ball.speedY;
                    brick.remove();
                    itr.remove();
                    score++;
                    break;
                } else if(intersection.x > brick.bounds.x || intersection.x + intersection.width < brick.bounds.x + brick.bounds.width){
                    ball.speedX = -ball.speedX;
                    brick.remove();
                    itr.remove();
                    score++;
                    break;
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

    }
}
