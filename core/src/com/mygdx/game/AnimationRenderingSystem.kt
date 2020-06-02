package com.mygdx.game

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class AnimationRenderingSystem(var batch: SpriteBatch)
  : IteratingSystem(
    Family.all(
        AnimationComponent::class.java,
        PositionComponent::class.java
    ).get()) {

  override fun update(deltaTime: Float) {
    batch.begin()
    super.update(deltaTime)
    batch.end()
  }

  override fun processEntity(entity: Entity, deltaTime: Float) {
    val frame = entity.getComponent(AnimationComponent::class.java).frame
    val (x, y) = entity.getComponent(PositionComponent::class.java)
    batch.draw(frame, x, y, 75f, 75f)
  }

}