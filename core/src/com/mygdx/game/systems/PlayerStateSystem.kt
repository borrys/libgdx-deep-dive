package com.mygdx.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mygdx.game.components.*

class PlayerStateSystem(
    idleAnimation: Animation<out TextureRegion>,
    runAnimation: Animation<out TextureRegion>
) : IteratingSystem(
    Family.all(PlayerStateComponent::class.java).get()
) {

  private val animationMap = mapOf(
      PlayerState.IDLE to idleAnimation,
      PlayerState.RUNNING to runAnimation
  )

  override fun processEntity(player: Entity, deltaTime: Float) {
    val newState = player.newState
    if (newState != player.state.currentState) {
      player.state.currentState = newState
      player.animation.animation = animationMap[newState]!!
    }
  }

  private val Entity.newState
    get() = if (this.move.isZero) PlayerState.IDLE else PlayerState.RUNNING
}

