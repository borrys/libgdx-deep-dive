package com.mygdx.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.components.*

class RenderingSystem(var batch: SpriteBatch)
  : IteratingSystem(
    Family
        .all(
            TextureComponent::class.java,
            PositionComponent::class.java,
            SizeComponent::class.java
        )
        .get()) {

  override fun update(deltaTime: Float) {
    batch.begin()
    val sortedEntities = entities.sortedBy { -it.position.y }
    sortedEntities
        .forEach { processEntity(it, deltaTime) }
    batch.end()
  }

  override fun processEntity(entity: Entity, deltaTime: Float) {
    val img = entity.texture.image
    val (x, y) = entity.position
    val (width, height) = entity.size
    val (scaleX, scaleY) = entity.scale.vector

    batch.draw(img, x, y,
        width / 2, height / 2,
        width, height,
        scaleX, scaleY,
        0f)
  }
}