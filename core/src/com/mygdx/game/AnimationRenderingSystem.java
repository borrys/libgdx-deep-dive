package com.mygdx.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationRenderingSystem extends IteratingSystem {
  SpriteBatch batch;

  public AnimationRenderingSystem(SpriteBatch batch) {
    super(Family.all(AnimationComponent.class, PositionComponent.class).get());
    this.batch = batch;
  }

  @Override
  public void update(float deltaTime) {
    batch.begin();
    super.update(deltaTime);
    batch.end();
  }

  @Override
  protected void processEntity(Entity entity, float deltaTime) {
    TextureRegion frame = entity.getComponent(AnimationComponent.class).getFrame();
    PositionComponent position = entity.getComponent(PositionComponent.class);
    batch.draw(frame, position.x, position.y, 75, 75);
  }
}
