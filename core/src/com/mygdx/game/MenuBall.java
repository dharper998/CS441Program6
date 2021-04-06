package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class MenuBall extends Actor {
    private Sprite sprite;
    float speedX = 3f;
    float speedY;
    Rectangle bounds;

    public MenuBall(float speed){
        this.sprite = new Sprite(new Texture(Gdx.files.internal("raw/globe_1.png")));
        this.setTouchable(Touchable.enabled);
        this.setBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        bounds = new Rectangle((int)this.sprite.getX(), (int)this.sprite.getY(), (int)this.sprite.getWidth(), (int)this.sprite.getHeight());
        this.setOrigin(this.sprite.getWidth() / 2, this.sprite.getHeight() / 2);
        this.speedY = speed;
    }

    @Override
    public void draw(Batch batch, float alpha){
        sprite.draw(batch);
    }

    @Override
    public void positionChanged(){
        sprite.setPosition(getX(), getY());
        this.updateBounds();
    }

    public void updateBounds(){
        bounds.set((int)this.sprite.getX(), (int)this.sprite.getY(), (int)this.sprite.getWidth(), (int)this.sprite.getHeight());
    }

    public boolean update(){
        if(this.getX() > Gdx.graphics.getWidth() - this.getWidth() || this.getX() < 0){
            speedX = -speedX;
        }
        if(this.getY() > Gdx.graphics.getHeight() - this.getHeight() || this.getY() < 0){
            speedY = -speedY;
        }

        this.moveBy(speedX, speedY);
        this.positionChanged();
        return true;
    }
}