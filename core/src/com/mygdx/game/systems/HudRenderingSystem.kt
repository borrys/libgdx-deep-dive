package com.mygdx.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.components.*

class HudRenderingSystem(val batch: SpriteBatch, val font: BitmapFont)
  : IteratingSystem(
    Family
        .all(
            TextComponent::class.java,
            PositionComponent::class.java
        )
        .get()) {
  override fun update(deltaTime: Float) {
    batch.begin()
    super.update(deltaTime)
    batch.end()
  }

  override fun processEntity(entity: Entity, deltaTime: Float) {
    val (x, y) = entity.position
    val text = entity.text.value
    font.draw(batch, text, x, y)
  }
}