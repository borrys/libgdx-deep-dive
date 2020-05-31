package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class MyGdxGame extends ApplicationAdapter {
  SpriteBatch batch;
  TextureRegion img;
  TextureRegion box;
  TextureAtlas atlas;
  Animation<TextureRegion> apple;
  Vector2 playerPosition = new Vector2();
  float speedX = 200f;
  float speedY = 200f;
  float appleAnimTime = 0;

  @Override
  public void create() {
    batch = new SpriteBatch();
    atlas = new TextureAtlas("packed/assets.atlas");
    box = atlas.findRegion("box");
    img = atlas.findRegion("idle");
    Array<TextureAtlas.AtlasRegion> appleFrames = atlas.findRegions("apple");
    appleFrames.removeRange(0, 4);
    apple = new Animation<TextureRegion>(0.05f, appleFrames, Animation.PlayMode.LOOP);
  }

  @Override
  public void render() {
    float time = Gdx.graphics.getDeltaTime();
    Vector2 move = new Vector2();
    if (Gdx.input.isKeyPressed(Input.Keys.A)) {
      move.x = -speedX * time;
    } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
      move.x = speedX * time;
    }

    if (Gdx.input.isKeyPressed(Input.Keys.W)) {
      move.y = speedY * time;
    } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
      move.y = -speedY * time;
    }

    playerPosition.add(move);
    appleAnimTime += time;

    Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    batch.draw(img, playerPosition.x, playerPosition.y, 100f, 100f);

    batch.draw(box, 500, 450, 100f, 100f);
    batch.draw(box, 0, 250, 70f, 70f);
    batch.draw(box, 650, -30, 130f, 130f);

    batch.draw(apple.getKeyFrame(appleAnimTime), 300, 350, 75, 75);

    batch.end();
  }

  @Override
  public void dispose() {
    batch.dispose();
    atlas.dispose();
  }
}
