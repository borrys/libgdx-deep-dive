package com.mygdx.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.components.PlayerStateComponent
import com.mygdx.game.components.PositionComponent
import com.mygdx.game.components.position

class CameraSystem(val camera: Camera, val batch: SpriteBatch) : IteratingSystem(
    Family.all(
        PositionComponent::class.java,
        PlayerStateComponent::class.java
    ).get()
) {
  override fun processEntity(player: Entity, deltaTime: Float) {
    camera.position.set(player.position, 0f).add(0f, 200f, 0f)
    camera.update()
    batch.projectionMatrix = camera.combined
  }
}