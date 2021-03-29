package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Brick extends Actor {
    private Sprite sprite;
    Rectangle bounds;

    public Brick(){
        this.sprite = new Sprite(new Texture(Gdx.files.internal("raw/button.png")));
        this.sprite.setSize(150f, 40f);
        this.setTouchable(Touchable.enabled);
        this.setBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        this.setOrigin(this.sprite.getWidth() / 2, this.sprite.getHeight() / 2);
    }

    @Override
    public boolean remove(){
        return super.remove();
    }

    @Override
    public void draw(Batch batch, float alpha){
        sprite.draw(batch);
    }

    @Override
    public void positionChanged(){
        sprite.setPosition(getX(), getY());
    }

    public void setBounds(){
        bounds = new Rectangle((int)this.sprite.getX(), (int)this.sprite.getY(), (int)this.sprite.getWidth(), (int)this.sprite.getHeight());
    }
}
