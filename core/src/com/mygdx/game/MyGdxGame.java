package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class MyGdxGame extends ApplicationAdapter {
  private static final float PLAYER_SIZE = 100f;
  SpriteBatch batch;
  SpriteBatch hudBatch;
  TextureRegion img;
  TextureRegion box;
  TextureAtlas atlas;
  Animation<TextureRegion> appleAnimation;
  Animation<TextureRegion> runningAnimation;
  Vector2 playerPosition = new Vector2();
  float speedX = 200f;
  float speedY = 200f;
  boolean running;
  float runningTime = 0;
  OrthographicCamera camera;
  BitmapFont font;

  Engine engine;

  @Override
  public void create() {
    batch = new SpriteBatch();
    hudBatch = new SpriteBatch();
    atlas = new TextureAtlas("packed/assets.atlas");
    box = atlas.findRegion("box");
    img = atlas.findRegion("idle");
    Array<TextureAtlas.AtlasRegion> appleFrames = atlas.findRegions("apple");
    appleFrames.removeRange(0, 4);
    appleAnimation = new Animation<TextureRegion>(0.05f, appleFrames, Animation.PlayMode.LOOP);
    runningAnimation = new Animation<TextureRegion>(0.05f, atlas.findRegions("run"), Animation.PlayMode.LOOP);
    font = new BitmapFont(Gdx.files.internal("font/font.fnt"));

    camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    engine = new Engine();
    engine.addSystem(new AnimationSystem());
    engine.addSystem(new AnimationRenderingSystem(batch));

    Entity apple = new Entity();
    apple.add(new AnimationComponent(appleAnimation));
    apple.add(new PositionComponent(300f, 350f));
    engine.addEntity(apple);

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

    float cameraScale = Gdx.input.isKeyPressed(Input.Keys.SPACE) ? 2f : 1f;
    camera.viewportWidth = Gdx.graphics.getWidth() * cameraScale;
    camera.viewportHeight = Gdx.graphics.getHeight() * cameraScale;

    running = !move.isZero();
    playerPosition.add(move);
    if (running) {
      runningTime += time;
    } else {
      runningTime = 0;
    }
    camera.position.x = playerPosition.x;
    camera.position.y = playerPosition.y + 200;

    Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    batch.setProjectionMatrix(camera.combined);

    engine.update(time);

    batch.begin();
    if (running) {
      TextureRegion frame = runningAnimation.getKeyFrame(runningTime);
      batch.draw(frame, playerPosition.x, playerPosition.y,
          PLAYER_SIZE / 2, PLAYER_SIZE / 2,
          PLAYER_SIZE, PLAYER_SIZE,
          move.x >= 0 ? 1 : -1, 1,
          0
      );
    } else {
      batch.draw(img, playerPosition.x, playerPosition.y, 100f, 100f);
    }

    batch.draw(box, 500, 450, 100f, 100f);
    batch.draw(box, 0, 250, 70f, 70f);
    batch.draw(box, 650, -30, 130f, 130f);

    batch.end();

    hudBatch.begin();
    font.draw(hudBatch, "Hello, world!", 100, 100);
    hudBatch.end();
  }

  @Override
  public void dispose() {
    batch.dispose();
    atlas.dispose();
  }
}
