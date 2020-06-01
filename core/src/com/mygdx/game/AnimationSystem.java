package com.mygdx.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class AnimationSystem extends IteratingSystem {
  public AnimationSystem() {
    super(Family.all(AnimationComponent.class).get());
  }

  @Override
  protected void processEntity(Entity entity, float deltaTime) {
    AnimationComponent anim = entity.getComponent(AnimationComponent.class);
    anim.updateTime(deltaTime);
  }
}
