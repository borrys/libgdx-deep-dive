package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
  SpriteBatch batch;
  Texture img;
  float x = 0;
  float y = 0;
  float speedX = 200f;
  float speedY = 200f;

  @Override
  public void create() {
    batch = new SpriteBatch();
    img = new Texture("badlogic.jpg");
  }

  @Override
  public void render() {
    float time = Gdx.graphics.getDeltaTime();
    float newX = x + speedX * time;
    float newY = y + speedY * time;

    if (newX + img.getWidth() >= Gdx.graphics.getWidth() || newX < 0) {
      speedX *= -1;
      newX = x + speedX * time;
    }
    if (newY + img.getHeight() >= Gdx.graphics.getHeight() || newY < 0) {
      speedY *= -1;
      newY = y + speedY * time;
    }

    this.x = newX;
    this.y = newY;


    Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    batch.draw(img, this.x, this.y);
    batch.end();
  }

  @Override
  public void dispose() {
    batch.dispose();
    img.dispose();
  }
}
